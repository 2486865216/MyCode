<template>
  <div id="app">
    <h2>App(祖)</h2>
    <Suspense>
      <template #default>
      <Child/>
    </template>
    <template #fallback>
      <div>
        loading
      </div>
    </template>
    </Suspense>
  </div>
</template>

<script>
//import Child from "./components/Child.vue";//静态引入
import { defineAsyncComponent } from 'vue';
const Child = defineAsyncComponent(()=>import('./components/Child.vue'))//异步引入
export default {
  name: "App",
  components: {
    Child,
  },
  setup(){
    
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
