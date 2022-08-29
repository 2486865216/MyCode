import Vue from 'vue'
import App from './App.vue'
import store from './store'
import vueResource from 'vue-resource'

Vue.config.productionTip = false
Vue.use(vueResource)
new Vue({
  render: h => h(App),
  store,
}).$mount('#app')
