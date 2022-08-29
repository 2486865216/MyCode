import React, { Component } from 'react'
import './App.css'
import { Route, Routes } from 'react-router-dom'
import About from './pages/About'
import Home from './pages/Home'
import MyNavLink from './components/MyNavLink'

export default class App extends Component {
    render() {
        return (
            <div id='app'>
                <h1>点击按钮切换组件</h1>
                <hr />
                <div id='body'>
                    <span id='left'>
                        {/* 编写路由链接
                        <div><NavLink to='/about' className={( {isActive} ) => isActive ? "active" : ""} >About</NavLink></div>
                        <div><NavLink to='/home'  className={( {isActive} ) => isActive ? "active" : ""}>Home</NavLink></div> */}
                            <MyNavLink to="/about">About</MyNavLink>
                            <MyNavLink to="/home">Home</MyNavLink>
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