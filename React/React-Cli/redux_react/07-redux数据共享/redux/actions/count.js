/* 
    专门为count组件生成action对象
*/
//同步action，函数的返回值为对象
import {INCREMENT,DECREMENT} from '../contant'

export const createIncrementAction = data => ({type:INCREMENT, data})
export const createDecrementAction = data => ({type:DECREMENT, data})
//异步action，函数的返回值为函数
//yarn add react-thunk
export const createIncrementAsyncAction = (data, time) => {
    return (dispatch) => {
        setTimeout(() => {
            dispatch(createIncrementAction(data))
        },time)
    }
}