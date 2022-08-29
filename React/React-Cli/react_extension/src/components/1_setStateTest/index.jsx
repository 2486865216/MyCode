import React, { Component } from 'react'

export default class Demo extends Component {
    state = {count: 0}
    increment = () => {
        //const {count} = this.state
        //对象式setState
        // this.setState({count: count + 1},() => {
        //     console.log('currentCount:',count);
        // })
        //函数式setState
        this.setState((state, props) => {
            return {count: state.count + 1}
        })
    }
  render() {
    return (
      <div>
          <h1>当前求和为: {this.state.count}</h1>
          <button onClick={this.increment}>点我加一</button>
      </div>
    )
  }
}
