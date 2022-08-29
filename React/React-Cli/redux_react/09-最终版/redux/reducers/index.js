/* 汇总所有的reducers */
//引入为 Count组件服务的 reducer
import countReducer from './count'
//引入为 Person组件服务的 reducer
import personReducer from './person'
import {combineReducers} from 'redux'
export default combineReducers({
    count:countReducer,
    person:personReducer
})