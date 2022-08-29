import React, { Component } from 'react'
import Child from './Child'

export default class Parent extends Component {
    state = {
        hasError:''
    }
    //当 Parent的子组件出现报错时候,会触发 getDerivedStateFromError调用,并携带错误信息
    static getDerivedStateFromError(error){
        return {hasError:error}
    }
    componentDidCatch(){
        console.log('hello error');
        //此处统计错误,反馈给服务器于通知编码人员进行bug的检测
    }
  render() {
    return (
      <div>
          <h1>
              Parent
              {
                  this.state.hasError ? <h2>网络延迟,请稍后再试</h2> : <Child/>
              }
          </h1>
      </div>
    )
  }
}
