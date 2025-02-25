import { request } from '../utils/request';

/** 用户相关接口类型 */
export interface UserProfile {
  id: string;
  name: string;
  avatarUrl: string;
}

export interface LoginParams {
  username: string;
  password: string;
}

/** 用户 API 模块 */
export const userApi = {
  // 登录
  login: (data: LoginParams) => 
    request<{ token: string }>({
      url: '/user/login',
      method: 'POST',
      data,
      isPublic: true,
    }),

  // 获取用户资料
  getProfile: () =>
    request<UserProfile>({
      url: '/user/profile',
      method: 'GET',
    }),

  // 更新头像（上传文件示例）
  updateAvatar: (filePath: string) => {
    const formData = new FormData();
    formData.append('file', Taro.getFileSystemManager().readFileSync(filePath));

    return request({
      url: '/user/avatar',
      method: 'UPLOAD',
      filePath,
      name: 'file',
      formData,
    });
  },
};