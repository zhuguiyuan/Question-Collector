import { request } from '../utils/request';
import {GetQuestion, Question} from '../types/questionList'

/** 错题集 API 模块 */
export const questionApi = {
  // 获取错题集列表
  getQuestionList: (data: GetQuestion) => 
    request<Question>({
      url: '/questions/getQuestionById',
      method: 'GET',
      data,
      isPublic: true,
    }),
};