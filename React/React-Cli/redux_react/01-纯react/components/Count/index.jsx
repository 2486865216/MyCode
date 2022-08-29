import React, { Component } from 'react'

export default class Count extends Component {
    state = {count:0}
    //减法
    decream = () => {
        const {value} = this.selectNumber
        const {count} = this.state
        this.setState({count: count - value * 1})
    }
    //加法
    incream = () => {
        const {value} = this.selectNumber
        const {count} = this.state
        this.setState({count: count + value * 1})
    }
    //奇数加
    increamIfodd = () => {
        const {value} = this.selectNumber
        const {count} = this.state
        if (count % 2){
            this.setState({count: count + value * 1})
        }
    }
    //异步加
    increamAsyns = () => {
        const {value} = this.selectNumber
        const {count} = this.state
        setTimeout(() => {
            this.setState({count: count + value * 1})
        }, 500);
    }
    render() {
        return (
            <div>
                <h1>当前求和为: {this.state.count}</h1>
                <select ref={c => this.selectNumber = c}>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                </select>
                <button onClick={this.decream}>-</button>
                <button onClick={this.incream}>+</button>
                <button onClick={this.increamIfodd}>为奇数再加</button>
                <button onClick={this.increamAsyns}>异步加</button>
            </div>
        )
    }
}
