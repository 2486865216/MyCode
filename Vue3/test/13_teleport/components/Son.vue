<template>
  <div style="backgroundColor:green">
    <h2>I am Son</h2>
    <Test/>
  </div>
</template>

<script>
import Test from './Test.vue'
export default {
  name: "Son",
  components:{Test},
  setup(){
  }
};
</script>

<style>
</style>