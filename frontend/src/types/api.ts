/** 基础响应结构 */
  export interface BaseResponse<T = any> {
    code: number;
    data: T;
    message?: string;
    success: boolean;  // 业务成功标识
  }
  
  /** 业务错误类型 */
  export enum ErrorCode {
    TIMEOUT = 10001, // 请求超时
    AUTH_EXPIRED = 20001, // Token 过期
  }
  
  /** 扩展请求配置 */
  export interface RequestConfig extends Partial<Taro.request.Option> {
    isPublic?: boolean; // 是否无需 Token 的公开接口
    retry?: number; // 自动重试次数
  }