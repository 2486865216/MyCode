import React, { Component } from 'react'
import List from './components/List'
import Search from './components/Search'
import './App.css'

export default class App extends Component {
    state = {
        users: [],
        isFirst: true,//第一次
        isLoading: false,//加载
        err: ''//错误信息
    }

    updateAppState = (state) => {
        this.setState(state)
    }
    render() {
        return (
            <div id='app'>
                <Search updateAppState={this.updateAppState} />
                <List {...this.state} />
            </div>
        )
    }
}