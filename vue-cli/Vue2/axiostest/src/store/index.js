import Vue from 'vue'
import vuex from 'vuex'

Vue.use(vuex)
//求和相关的组件
import countOption from './count'
//person相关的配置
import personOption from './person'
export default new vuex.Store({
    modules:{
        count:countOption,
        person:personOption
    }
})