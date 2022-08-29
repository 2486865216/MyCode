import React, { Component } from 'react'
import './App.css'
import { NavLink, Route, Routes } from 'react-router-dom'
import About from './pages/About'
import Home from './pages/Home'
import News from './pages/News'
import Message from './pages/Message'
import Details from './pages/Details'

export default class App extends Component {
    render() {
        return (
            <div id='app'>
                <h1>点击按钮切换组件</h1>
                <hr />
                <div id='body'>
                    <span id='left'>
                        {/* 编写路由链接 */}
                        <div><NavLink to='/about' className={({ isActive }) => isActive ? "active" : ""} >About</NavLink></div>
                        <div><NavLink to='/home' className={({ isActive }) => isActive ? "active" : ""}>Home</NavLink></div>
                    </span>
                    <span id='right'>
                        {/* 注册路由 */}
                        <Routes>
                            <Route path="about" element={<About />} />
                            <Route path="home" element={<Home />} >
                                <Route path='news' element={<News />} />
                                <Route path='message' element={<Message />}>
                                    {/* 声明接受params参数 */}
                                    {/* <Route path='detail/:id/:title' element={<Details />}/> */}
                                    {/* search参数无需声明接受 */}
                                    {/* state参数无需声明接受 */}
                                    <Route path='detail' element={<Details />}/>
                                </Route>
                            </Route>
                            <Route path="*" element={<Home />} />
                        </Routes>
                    </span>
                </div>
            </div>
        )
    }
}