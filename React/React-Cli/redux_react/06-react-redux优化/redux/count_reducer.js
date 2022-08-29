//用于创建一个为Couont组件服务的reducer，recuder本质就是一个函数
//reducer会接受两个参数 previousState, action
//初始化状态
const initState = 0
function countReducer(previousState = initState, action) {
    //从action获取type,data
    const { type, data } = action
    //根据type决定如何加工数据
    switch (type) {
        case 'increment':
            return previousState + data
        case 'decrement':
            return previousState - data
        default:
            return previousState;
    }
}
export default countReducer