<template>
  <button @click="isshow = !isshow">显示/隐藏</button>
  <Demo v-if="isshow"/>
</template>

<script>
import Demo from "./components/Demo.vue";
import {ref} from 'vue'
export default {
  name: "App",
  components: {
    Demo,
  },
  setup(){
    let isshow = ref(true)
    return {
      isshow
    }
  }
};
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
