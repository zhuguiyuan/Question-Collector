import { Component, PropsWithChildren } from 'react'
import { View, Image } from '@tarojs/components'
import { AtGrid } from 'taro-ui'
import logo from '../../assets/sprout-power.png'
import Taro from '@tarojs/taro';
// 按需引入
import './index.scss'


const gridData = [
  {
    image: 'https://img12.360buyimg.com/jdphoto/s72x72_jfs/t6160/14/2008729947/2754/7d512a86/595c3aeeNa89ddf71.png',
    value: '用户管理'
  },
  {
    image: 'https://img20.360buyimg.com/jdphoto/s72x72_jfs/t15151/308/1012305375/2300/536ee6ef/5a411466N040a074b.png',
    value: '智能问答'
  },
  {
    image: 'https://img10.360buyimg.com/jdphoto/s72x72_jfs/t5872/209/5240187906/2872/8fa98cd/595c3b2aN4155b931.png',
    value: '错题管理',
    path: '/pages/questionList/index'
  },
  {
    image: 'https://img12.360buyimg.com/jdphoto/s72x72_jfs/t10660/330/203667368/1672/801735d7/59c85643N31e68303.png',
    value: '智能分析'
  }
]

export default class Index extends Component<PropsWithChildren> {

  componentDidMount () { }

  componentWillUnmount () { }

  componentDidShow () { }

  componentDidHide () { }

  // 点击处理函数
  handleGridClick = (item, index) => {
    if (item.path) {
      Taro.navigateTo({
        url: item.path // 跳转到配置的路径
      })
    }
  }

  render () {
    return (
      <View className='<%= pageName %>'>
        <Image src = {logo} mode='aspectFit'/>
        <AtGrid columnNum={2} data={gridData} onClick={this.handleGridClick}/>
      </View>
    )
  }
}
