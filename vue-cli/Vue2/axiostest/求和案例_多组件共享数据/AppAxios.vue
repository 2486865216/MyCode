<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <button @click="getmsg">发送请求</button>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'App',
  methods:{
    getmsg(){
      axios.get('http://localhost:8080/student/student').then(
        response => {console.log(response.data);},
        error => {console.log(error.message)}
      )
    }
  },
  components: {
  }
}
</script>

<style>
</style>
