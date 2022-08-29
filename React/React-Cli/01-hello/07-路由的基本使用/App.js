import React, { Component } from 'react'
import './App.css'
import { Link, Route, Routes } from 'react-router-dom'
import About from './components/About'
import Home from './components/Home'

export default class App extends Component {
    render() {
        return (
            <div id='app'>
                <h1>点击按钮切换组件</h1>
                <hr />
                <div id='body'>
                    <span id='left'>
                        {/* 编写路由链接 */}
                        <div><Link to='/about'>About</Link></div>
                        <div><Link to='/home'>Home</Link></div>
                    </span>
                    <span id='right'>
                        {/* 注册路由 */}
                        <Routes>
                            <Route path="/about" element={<About />} />
                            <Route path="/home" element={<Home />} />
                        </Routes>
                    </span>
                </div>
            </div>
        )
    }
}