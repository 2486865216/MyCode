import axios from 'axios'
import {nanoid} from 'nanoid'
export default {
    namespaced: true,
    actions: {
        add(context, value){
            context.commit('add', value)
        },
        addPersonServer(context){
            axios.get('https://api.uixsj.cn/hitokoto/get?type=social').then((response) => {
                context.commit('ADD_SERVER',{id:nanoid(),name:response.data})
            })
        }
    },
    mutations: {
        add(state, value) {
            state.personlist.unshift(value)
        },
        ADD_SERVER(state,value){
            state.personlist.unshift(value)
        }
    },
    state: {
        personlist: [
            {id: '01', name: 'hello'}
        ]
    }
}