<template>
  <div>
      <h1 @click="showdemo">name:{{name}}</h1>
      <h2>age:{{age}}</h2>
      <h2>{{x}}:::{{y}}</h2>
  </div>
</template>

<script>
import {mixin,mixin2} from '../mixin'
export default {
    name:'mixin',
    data(){
        return {
            name:'zhangsan',
            age:18
        }
    },
    mixins:[mixin,mixin2]
}
</script>

<style>

</style>