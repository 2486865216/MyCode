路由到件与一般组件
    1.写法不同:
        一般组件:<Demo/>
        路由组件:< Route path="/demo" element={<Demo/>}
    2.存放位置不同
        一般组件: components
        路由组件: pages
    3.接收到的 props不同
        一般组件:写组件标签时传递了什么,就能收到什么
        路由组件:接收到三个固定的属性
            history
                go:f go(n)
                goBack: f goBack()
                goForward: f goForward()
                push: f push(path,state)
                replace: f replace(path, state)
            location
                pathname: /about
                search
                state: undefined
            match
                params:
                path: "/about
                url:/about

样式丢失
<link rel="stylesheet" href="./css/index.css">                
<link rel="stylesheet" href="/css/index.css">                
<link rel="stylesheet" href="%PUBLIC_URL%/css/index.css">
<HashRouter>  

向路由组件传递params参数
{/* 声明接受params参数 */}
<Route path='detail/:id/:title' element={<Details />}/>
{/* 向路由组件传递params参数 */}
<Link to={`/home/message/detail/${msgObj.id}/${msgObj.title}`}>{msgObj.title}</Link>

函数式组件
import {useParams} from 'react-router-dom'
const params = useParams()

{/* 传递search参数 */}
<Link to={`/home/message/detail?id=${msgObj.id}&title=${msgObj.title}`}>{msgObj.title}</Link>

{/* search参数无需声明接受 */}
<Route path='detail' element={<Details />}/>

import { useSearchParams } from 'react-router-dom'
let [searchParams] = useSearchParams()
const id = searchParams.get('id')

useParams()
useSearchParams()
只能用在函数式组件


Browser router与 HashRouter的区别
    1.底层原理不一样:
        BrowserRouteri使的是H5的 history API,不兼容E9及以下版本
        HashRouter使用的是URL的哈希值。
    2.ur1表现形式不一样
        Browser router的路径中没有#,例如:1 localhost:3/demo/test
        HashRouter的路径包含#,例如:1oca1host:300/#/demo/test
    3.刷新后对路由 state参数的影响
        (1) Browser Router没有任何影响,因为 state保存在 history对象中
        (2). HashRouter刷新后会导致路由 state参数的丢失。
    4.备注: HashRouter可以用于解决一些路径错误相关的问题