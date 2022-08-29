//vuex最核心的store
import Vue from 'vue'
import vuex from 'vuex'
Vue.use(vuex)
// 准备actions，响应组件中的动作
const actios = {
    increment(context, value){
        context.commit('increment',value)
    },
    // decrement(context, value){
    //     context.commit('decrement',value)
    // },
    incrementAdd(context, value){
        context.commit('incrementAdd',value)
    },
    incrementWait(context, value){
        context.commit('incrementWait',value)
    },
    add(context, value){
        context.commit('add', value)
    }
}
// 准备mutations，操作数据
const mutations = {
    increment(state, value){
        state.sum += value
    },
    decrement(state, value){
        state.sum -= value
    },
    incrementAdd(state, value){
        if(state.sum % 2){
            state.sum += value
        }
    },
    incrementWait(state, value){
        setTimeout(()=>{
            state.sum += value
        },1000)
    },
    add(state, value){
        state.personlist.unshift(value)
    }
}
// 准备state,存储数据
const state = {
    sum:0,
    hello:'Hello',
    word:'Word',
    personlist:[
        {id:'01', name:'hello'}
    ]
}
//准备getters-用于将state中的数据进行加工
const getters = {
    bigSum(state){
        return state.sum*10
    }
}
export default new vuex.Store({
    actions:actios,
    mutations:mutations,
    state,
    getters
})