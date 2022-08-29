<template>
  <div style="backgroundColor:green">
    <h2>I am Son {{car.name}}--{{car.price}}</h2>
  </div>
</template>

<script>
import { inject } from 'vue'
export default {
  name: "Son",
  setup(){
      let car = inject('car')
      return {car}
  }
};
</script>

<style>
</style>