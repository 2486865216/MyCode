<template>
  <img alt="Vue logo" src="./assets/logo.png">
  <h1>一个人的信息</h1>
  <h2>姓名:{{name}}</h2>
  <h2>年龄:{{age}}</h2>
  <h2>job:{{job.type}}</h2>
  <h2>job:{{job.salary}}</h2>
  <button @click="changeInfo">changeInfo</button>
</template>

<script>
import {ref} from 'vue'
export default {
  name: 'App',
  components: {
  },
  setup(){
    let name = ref('hello')
    let age = ref(18)
    let job = ref({
      type:'java',
      salary:5000
    })

    function changeInfo(){
      name.value = 'word',
      age.value = 20,
      job.value.type = 'python'
      job.value.salary = 6000
    }
    return {
      name,
      age,
      changeInfo,
      job
    }
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
