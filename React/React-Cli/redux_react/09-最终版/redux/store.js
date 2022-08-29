// 该文件专门用于暴露一个 store对象,整个应用只有一个 store对象
//引入 createstore,专门用于创建 redux中最为核心的 store对象
import {createStore,applyMiddleware} from 'redux'
//引入redux-thunk,用于支持异步action,applyMiddleware
import thunk from 'redux-thunk'
//引入redux-devtools-extension
import {composeWithDevTools} from 'redux-devtools-extension'


import allReducers from  './reducers/index'
//暴露 store
export default createStore(allReducers,composeWithDevTools(applyMiddleware(thunk)))