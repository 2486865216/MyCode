//引入UI
import countUI from '../../components/Count'
//引入connect用于连接组件
import {connect} from 'react-redux'
import {createIncrementAction,createDecrementAction,createIncrementAsyncAction} from '../../redux/count_action'
//函数的返回值作为状态传递给UI组件(<countUI key={value} />)
function mapStateToProps(state){
    return {count:state}
}
//函数的返回值作为操作状态状态的方法传递给UI组件
function mapDispatchToProps(dispatch){
    return {
        jia:data => dispatch(createIncrementAction(data)),
        jian:data => dispatch(createDecrementAction(data)),
        jiaAsync:(data, time) => dispatch(createIncrementAsyncAction(data,time)),
    }
}
//创建并暴露一个count的容器组件
export default connect(mapStateToProps,mapDispatchToProps)(countUI)
// const CountContainer = connect()(countUI)
// export default CountContainer