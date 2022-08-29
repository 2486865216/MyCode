<template>
  <h1>n={{ n }}</h1>
  <h1>n={{ m }}</h1>
  <button @click="n++">点我加1</button>
  <button @click="m++">点我加1</button>
</template>

<script>
import { ref, watchEffect } from "vue";
export default {
  name: "Demo",
  components: {},
  setup() {
    let n = ref(0);
    let m = ref(1)

    watchEffect(()=>{
      const x1 = n.value
      const x2 = m.value
      console.log(x1,x2)
    })
    
    return {
      n,
      m,
    };
  },
};
</script>

<style>
</style>
