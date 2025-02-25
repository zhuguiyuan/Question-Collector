// 1. 新增导入
import { Component, PropsWithChildren } from 'react'
import { View, Text, RichText } from '@tarojs/components'
import { AtButton, AtCard, AtMessage } from 'taro-ui'
import "taro-ui/dist/style/components/button.scss"
import "taro-ui/dist/style/components/message.scss"
import './index.scss'
import { questionApi } from '../../api/questionList'
import { Question } from '../../types/questionList'

// 2. 定义组件状态类型
interface IndexState {
  loading: boolean
  question?: Question
  errorMsg?: string
}

export default class Index extends Component<PropsWithChildren, IndexState> {
  // 3. 初始化状态
  state: IndexState = {
    loading: false,
  }

  componentDidMount () {
    this.loadQuestion()
  }

  // 4. 封装数据加载方法
  loadQuestion = async () => {
    this.setState({ loading: true, errorMsg: undefined })
    
    try {
      const res = await questionApi.getQuestionList({ id: 123 }) // 根据实际需求传递参数
      if (res.success) {
        this.setState({ question: res.data })
      } else {
        this.setState({ errorMsg: res.message || '数据加载失败' })
      }
    } catch (error) {
      this.setState({ errorMsg: '网络请求异常，请稍后重试' })
    } finally {
      this.setState({ loading: false })
    }
  }

  // 5. 渲染错误提示
  renderError() {
    const { errorMsg } = this.state
    return errorMsg && (
      <View className="error-message">
        <Text>{errorMsg}</Text>
        <AtButton 
          type='secondary' 
          size='small' 
          onClick={this.loadQuestion}
        >
          重试
        </AtButton>
      </View>
    )
  }

  // 6. 渲染加载状态
  renderLoading() {
    return (
      <View className="loading">
        <Text>加载中...</Text>
      </View>
    )
  }

  // 7. 更新渲染方法
  render () {
    const { loading, question } = this.state

    return (
      <View className='question-page'>
        {/* 全局消息提示 */}
        <AtMessage />

        <AtCard title="技术问题">
          {loading && this.renderLoading()}
          {this.renderError()}

          {question && (
            <View className="question-content">
              <RichText nodes={question.content} />
              <View className="answer-section">
                <Text className="answer-label">参考答案：</Text>
                <Text className="answer-text">{question.answer}</Text>
              </View>
            </View>
          )}
        </AtCard>

        <AtButton 
          type='secondary' 
          circle={true}
          className="next-btn"
          // onClick={this.handleNext}  // 可在此绑定下一题方法
        >
          下一题
        </AtButton>
      </View>
    )
  }
}