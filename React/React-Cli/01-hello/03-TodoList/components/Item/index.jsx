import React, { Component } from 'react';
import './index.css'

export default class index extends Component {
  state = {mouse:false}

  //鼠标移入移除回调
  handleMouse = (flag) => {
    return () => {
      this.setState({mouse:flag})
    }
  }

  //勾选回调
  handleCheck(id){ 
    return (event) => {
      this.props.updateTodo(id, event.target.checked)
    }
  }
  //删除回调
  handleDelete(id){
    if (window.confirm('确定删除吗?')){
      this.props.deleteTodo(id)
    }
  }
  render() {
    const {id,name,done} = this.props
    return (
        <li onMouseLeave={this.handleMouse(false)} onMouseOver={this.handleMouse(true)} className='li' id={id}>
          <span>
            <input onChange={this.handleCheck(id)} type="checkbox" checked={done}/>
            {name}
          </span>
          <button onClick={() => {this.handleDelete(id)}} className='button' style={{display:this.state.mouse ? 'block' : 'none'}}>删除</button>
        </li>
    );
  }
}
