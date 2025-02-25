import Taro from '@tarojs/taro';
import { BaseResponse, RequestConfig, ErrorCode } from '../types/api'

/** 基础配置 */
const BASE_URL = 'http://api.yourdomain.com';
const TIMEOUT = 15000;

/** 请求核心方法 */
export const request = async <T = any>(
  config: RequestConfig
): Promise<BaseResponse<T>> => {
  const { url, isPublic, retry = 0, ...restConfig } = config;

  // 1. 拼接完整 URL
  const fullUrl = url!.startsWith('http') ? url! : `${BASE_URL}${url}`;

  // 2. 处理 Token
  const header = {
    'Content-Type': 'application/json',
    ...(isPublic ? {} : { Authorization: `Bearer ${Taro.getStorageSync('token')}` }),
    ...restConfig.header,
  };

  try {
    // 3. 发起请求
    const response = await Taro.request({
      url: fullUrl,
      header,
      timeout: TIMEOUT,
      ...restConfig,
    });

    // 4. 处理 HTTP 状态码
    if (response.statusCode !== 200) {
      throw handleHttpError(response.statusCode);
    }

    // 5. 处理业务状态码
    const data = response.data as BaseResponse<T>;
    if (!data.success) {
      throw handleBusinessError(data.code);
    }

    return data;
  } catch (error: any) {
    // 6. 重试逻辑
    if (retry > 0) {
      return request<T>({ ...config, retry: retry - 1 });
    }
    throw error;
  }
};

/** 处理 HTTP 错误 */
const handleHttpError = (statusCode: number) => {
  const error = new Error(`HTTP Error: ${statusCode}`);
  error.name = 'HttpError';
  Taro.showToast({ title: `网络异常（${statusCode}）`, icon: 'none' });
  return error;
};

/** 处理业务错误 */
const handleBusinessError = (code: ErrorCode) => {
  const error = new Error(`Business Error: ${code}`);
  error.name = 'BusinessError';

  switch (code) {
    case ErrorCode.AUTH_EXPIRED:
      Taro.navigateTo({ url: '/pages/login/index' });
      break;
    default:
      Taro.showToast({ title: '请求失败，请重试', icon: 'none' });
  }

  return error;
};