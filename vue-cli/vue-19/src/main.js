import Vue from 'vue'
import App from './App.vue'

Vue.config.productionTip = false;

new Vue({
    render:h => h(App)
    // render(createElement){
    //     return createElement('h1','hello')
    // }
    // render:(createElement) => createElement('h1','hello')
}).$mount('#root')