<template>
  <div style="backgroundColor:orange">
      <h2>I am Child</h2>
      <Son/>
  </div>
</template>

<script>
import Son from '../components/Son.vue'
export default {
    name:'Child',
    components:{
        Son
    }
}
</script>

<style>

</style>