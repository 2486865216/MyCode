/* 
    专门为count组件生成action对象
*/
import {INCREMENT,DECREMENT} from './contant'
export const createIncrementAction = data => ({type:INCREMENT, data})
export const createDecrementAction = data => ({type:DECREMENT, data})