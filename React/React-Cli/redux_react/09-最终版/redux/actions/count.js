/* 
    专门为count组件生成action对象
*/
//同步action，函数的返回值为对象
import {INCREMENT,DECREMENT} from '../contant'

export const increment = data => ({type:INCREMENT, data})
export const decrement = data => ({type:DECREMENT, data})
//异步action，函数的返回值为函数
//yarn add react-thunk
export const incrementAsync = (data, time) => {
    return (dispatch) => {
        setTimeout(() => {
            dispatch(increment(data))
        },time)
    }
}