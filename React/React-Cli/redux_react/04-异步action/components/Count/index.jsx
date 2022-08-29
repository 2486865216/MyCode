import React, { Component } from 'react'
//引入store，用于保存状态
import store from '../../redux/store'
//引入action
import { 
    createIncrementAction,
    createDecrementAction,
    createIncrementAsyncAction 
} from '../../redux/count_action'

export default class Count extends Component {
    // componentDidMount(){
    //     store.subscribe(() => {
    //         this.setState({})
    //     })
    // }
    //减法
    decrement = () => {
        const {value} = this.selectNumber
        store.dispatch(createDecrementAction(value * 1))
    }
    //加法
    incrememnt = () => {
        const {value} = this.selectNumber
        store.dispatch(createIncrementAction(value * 1))
    }
    //奇数加
    incrementIfodd = () => {
        const {value} = this.selectNumber
        if (store.getState() % 2){
            store.dispatch(createIncrementAction(value * 1))
        }
    }
    //异步加
    incrementAsync = () => {
        const {value} = this.selectNumber
        store.dispatch(createIncrementAsyncAction(value * 1,500))
    }
    render() {
        return (
            <div>
                <h1>当前求和为: {store.getState()}</h1>
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
