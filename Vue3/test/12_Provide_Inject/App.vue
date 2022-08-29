<template>
  <div id="app">
    <h2>App(祖):{{name}}--{{price}}</h2>
    <Child/>
  </div>
</template>

<script>
import { reactive,toRefs,provide } from 'vue';
import Child from "./components/Child.vue";
export default {
  name: "App",
  components: {
    Child,
  },
  setup(){
    let car = reactive({
      name:'奔驰',
      price:'40w'
    })
    //给自己的后代组件传递数据
    provide('car', car)
    return {
      ...toRefs(car)
    }
  }
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  background-color: aqua;
}
</style>
