export default{
    namespaced:true,
    actions: {
        // increment(context, value) {
        //     context.commit('increment', value)
        // },
        // decrement(context, value){
        //     context.commit('decrement',value)
        // },
        incrementAdd(context, value) {
            context.commit('incrementAdd', value)
        },
        incrementWait(context, value) {
            context.commit('incrementWait', value)
        }
    },
    mutations: {
        increment(state, value) {
            state.sum += value
        },
        decrement(state, value) {
            state.sum -= value
        },
        incrementAdd(state, value) {
            if (state.sum % 2) {
                state.sum += value
            }
        },
        incrementWait(state, value) {
            setTimeout(() => {
                state.sum += value
            }, 1000)
        }
    },
    state: {
        sum: 0,
        hello: 'Hello',
        word: 'Word'
    },
    getters: {
        bigSum(state) {
            return state.sum * 10
        }
    }
}