<template>
  <div id="app">
    <Counts/>
    <br>
    <hr>
    <Person/>
  </div>
</template>

<script>
import Counts from './components/Counts.vue'
import Person from './components/Person.vue'
export default {
    name:'App',
    components: {
      Counts,
      Person
    },
}
</script>

<style>
#app{
  display: flex;
  justify-content: space-around;
}
img{
  width: 100%;
}
</style>