<template>
  <div id="app">
    <h1>{{msg}}</h1>
    <School/>
    <Student/>
  </div>
</template>

<script>
import Student from './components/Student.vue'
import School from './components/School.vue'

export default {
  name: 'App',
  data () {
    return {
      msg:'Hello Word!'
    }
  },
  components: {
    School,
    Student
  },
}
</script>

<style scoped>
#app {
  background-color: antiquewhite;
  padding: 5px;
  margin: 5px;
}
</style>
