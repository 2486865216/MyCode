<template>
  <img alt="Vue logo" src="./assets/logo.png">
  <h1>一个人的信息</h1>
  <h2>姓名:{{name}}</h2>
  <h2>年龄:{{age}}</h2>
  <button @click="sayHi">Hello</button>
</template>

<script>
// import {h} from 'vue'
export default {
  name: 'App',
  components: {
  },
  setup(){
    //数据
    let name = 'hello'
    let age = 18

    // 方法
    function sayHi(){
      alert(`${name},======${age}`)
    }

    //返回一个对象（常用）
    return {
      name,
      age,
      sayHi
    }
    //返回一个函数（渲染函数）
    // return ()=>{ return h('h1','word')}
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
