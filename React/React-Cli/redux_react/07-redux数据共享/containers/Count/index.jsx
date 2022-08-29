import React, { Component } from 'react'

import { connect } from 'react-redux'
import { createIncrementAction, createDecrementAction, createIncrementAsyncAction } from '../../redux/actions/count'

class Count extends Component {
    //减法
    decrement = () => {
        const {value} = this.selectNumber
        this.props.jian(value * 1)
    }
    //加法
    incrememnt = () => {
        const {value} = this.selectNumber
        this.props.jia(value * 1)
    }
    //奇数加
    incrementIfodd = () => {
        const {value} = this.selectNumber
        if (this.props.count % 2){
            this.props.jia(value * 1)
        }
    }
    //异步加
    incrementAsync = () => {
        const {value} = this.selectNumber
        this.props.jiaAsync(value * 1, 500)
    }
    render() {
        return (
            <div>
                <h1>Count组件,下方组件总人数为:{this.props.persons}</h1>
                <h2>当前求和为: {this.props.count}</h2>
                <select ref={c => this.selectNumber = c}>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                </select>
                <button onClick={this.decrement}>-</button>
                <button onClick={this.incrememnt}>+</button>
                <button onClick={this.incrementIfodd}>为奇数再加</button>
                <button onClick={this.incrementAsync}>异步加</button>
            </div>
        )
    }
}
//创建并暴露一个count的容器组件
export default connect(
    state => ({ count: state.count,persons:state.person.length }),
    // dispatch => ({
    //     jia: data => dispatch(createIncrementAction(data)),
    //     jian: data => dispatch(createDecrementAction(data)),
    //     jiaAsync: (data, time) => dispatch(createIncrementAsyncAction(data, time)),
    // })
    {
        jia:createIncrementAction,
        jian:createDecrementAction,
        jiaAsync:createIncrementAsyncAction,
    }
)(Count)