import React, { Component } from 'react'
import './index.css'

export default class index extends Component {
    render() {
        const {users,isFirst,isLoading,err} = this.props
        return (
            <div id='box'>
                {
                    isFirst ? <h1>欢迎使用,请输入关键字,然后点击搜索按钮</h1> :
                    isLoading ? <h1>Loading</h1> : 
                    err ? <h1 style={{color:'red'}}>{err}</h1> :
                    users.map((user) => {
                        return (
                            <div key={user.id} className='item'>
                                <a target='_blank' href="https://github.com" rel='noreferrer'>
                                    <img src={user.avatar_url} alt="logo" style={{ width: '150px' }} />
                                </a>
                                <div style={{width:'200px'}}>
                                    {user.login}
                                </div>
                            </div>
                        )
                    })
                }
            </div>
        )
    }
}
