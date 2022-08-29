<template>
  <h1>鼠标坐标:x:{{ point.x }} y:{{ point.y }}</h1>
</template>

<script>
import {  } from "vue";
import usePoint from '../hooks/usePoint'
export default {
  name: "Demo",
  components: {},
  setup() {
    let point = usePoint()
    return {
      point
    }
  },
};
</script>

<style>
</style>
