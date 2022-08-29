import React, { Component } from 'react'
import './index.css'
import axios from 'axios'
import pubsub from 'pubsub-js'

export default class index extends Component {
    search = () => {
        const { input: { value } } = this
        // this.props.updateAppState({isFirst:false, isLoading:true})
        pubsub.publish('user', { isFirst: false, isLoading: true })
        axios.get(`https://api.github.com/search/users?q=${value}`).then(
            response => {
                // this.props.updateAppState({isLoading:false, users:response.data.items})
                pubsub.publish('user', { isLoading: false, users: response.data.items })
            },
            error => {
                // this.props.updateAppState({isLoading:false, err:error.message});
                pubsub.publish('user', { isLoading: false, err: error.message })
            }
        )
    }
    render() {
        return (
            <div id='search'>
                <h1>搜索github用户</h1>
                <input ref={currentNode => this.input = currentNode} placeholder="请输入搜索关键词" type="text" />
                <button onClick={this.search}>搜索</button>
            </div>
        )
    }
}
