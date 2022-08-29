<template>
  <h1>n={{ n }}</h1>
  <button @click="n++">点我加1</button>
</template>

<script>
import { ref,onBeforeMount,onMounted,onBeforeUpdate,onUpdated,onBeforeUnmount,onUnmounted } from "vue";
export default {
  name: "Demo",
  components: {},
  //组合式
  setup() {
    console.log('setup');
    let n = ref(0);
    onBeforeMount(()=>{
      console.log('onBeforeMount');
    })
    onMounted(()=>{
      console.log('onMounted');
    })
    onBeforeUpdate(()=>{
      console.log('onBeforeUpdate');
    })
    onUpdated(()=>{
      console.log('onUpdated');
    })
    onBeforeUnmount(()=>{
      console.log('onBeforeUnmount');
    })
    onUnmounted(()=>{
      console.log('onUnmounted');
    })
    return {
      n,
    };
  },

  //配置项
  //#region
  /* beforeCreate(){
    console.log('beforeCreate');
  },
  created(){
    console.log('created');
  },
  beforeMount(){
    console.log('beforeMount');
  },
  mounted(){
    console.log('mounted');
  },
  beforeUpdate(){
    console.log('beforeUpdate');
  },
  updated(){
    console.log('updated');
  },
  beforeUnmount() {
    console.log('beforeUnmount');
  },
  unmounted() {
    console.log('unmounted');
  }, */
  //#endregion
};
</script>

<style>
</style>
