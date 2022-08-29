<template>
  <div id="app">
    <Counts/>
  </div>
</template>

<script>
import Counts from './components/Counts.vue'
export default {
    name:'App',
    components: {
      Counts
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