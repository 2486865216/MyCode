import React, { Component } from 'react';
import PropTypes from 'prop-types'
import { nanoid } from 'nanoid'

export default class index extends Component {
    //限制props
    static propTypes = {
        addTodo: PropTypes.func.isRequired
    }
    //键盘事件的回调
    handleKeyUP = (event) => {
        const { keyCode, target } = event
        const todoObj = { id: nanoid(), name: target.value, done: false }
        //判断是否是回车
        if (keyCode === 13 && target.value.trim() !== '') {
            this.props.addTodo(todoObj)
            target.value = ''
        }
    }
    //按钮回调
    handlebutton = () => {
        const todoObj = { id: nanoid(), name: document.getElementById('input').value, done: false }
        if (document.getElementById('input').value.trim() !== '') {
            this.props.addTodo(todoObj)
            document.getElementById('input').value = ''
        }
    }
    render() {
        return (
            <div>
                <input id='input' onKeyUp={this.handleKeyUP} type="text" placeholder='请输入任务' />
                <button onClick={this.handlebutton}>添加任务</button>
            </div>
        );
    }
}
