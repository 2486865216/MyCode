import React, { Component } from 'react';
import './index.css'

export default class index extends Component {
  //全选
  handleCheckAll = (event) => {
    this.props.checkAll(event.target.checked)
  }

  //清除已完成
  handleClearDone = () => {
      this.props.clearAllDone()
  }
  render() {
    //已完成
    const doneCount = this.props.todos.reduce((pre, todo) => pre + (todo.done ? 1 : 0), 0)
    //总数
    const tatol = this.props.todos.length
    return (
        <div id='footer'>
            <div>
              <input type="checkbox" onChange={this.handleCheckAll} checked={doneCount ===tatol && tatol !== 0 ? true : false}/>已完成{doneCount}/全部{tatol}
            </div>
            <button onClick={this.handleClearDone} className='delete'>删除已完成</button>
        </div>
    );
  }
}
