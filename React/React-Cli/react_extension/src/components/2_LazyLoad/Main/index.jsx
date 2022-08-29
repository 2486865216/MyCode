import React, { Component, lazy, Suspense } from 'react'
import { Menu, Layout } from 'antd'
import { Link, Routes, Route, } from 'react-router-dom'
// import About from '../About'
// import Home from '../Home'
import Loading from '../Loading'
import All from '../All'
const About = lazy(() => import('../About'))
const Home = lazy(() => import('../Home'))


export default class Main extends Component {
    render() {
        const { Sider, Content } = Layout
        return (
            <div>
                <Layout>
                    <Sider>
                        <Menu
                            style={{ width: 200 }}
                            mode="inline"
                            theme='dark'
                            title='lllll'
                        >
                            <Menu.ItemGroup key="g1">
                                <Menu.Item key="1">
                                    <Link to={`/about`}>About</Link>
                                </Menu.Item>
                                <Menu.Item key="2">
                                    <Link to={`/home`}>Home</Link>
                                </Menu.Item>
                            </Menu.ItemGroup>
                        </Menu>
                    </Sider>
                    <Content>
                        <div style={{ width: 300, textAlign: 'center', fontSize: 50 }}>
                            <Suspense fallback={<Loading />}>
                                <Routes>
                                    <Route path='about' element={<About />} />
                                    <Route path='home' element={<Home />} />
                                    <Route path='*' element={<All/>} />
                                </Routes>
                            </Suspense>
                        </div>
                    </Content>
                </Layout>
            </div>
        )
    }
}
