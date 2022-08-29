// 该文件专门用于暴露一个 store对象,整个应用只有一个 store对象
//引入 createstore,专门用于创建 redux中最为核心的 store对象
import {createStore,applyMiddleware,combineReducers} from 'redux'
//引入为 Count组件服务的 reducer
import countReducer from './reducers/count'
//引入为 Person组件服务的 reducer
import personReducer from './reducers/person'
//引入redux-thunk,用于支持异步action,applyMiddleware
import thunk from 'redux-thunk'
//引入redux-devtools-extension
import {composeWithDevTools} from 'redux-devtools-extension'


const allReducers = combineReducers({
    count:countReducer,
    person:personReducer
})
//暴露 store
export default createStore(allReducers,composeWithDevTools(applyMiddleware(thunk)))