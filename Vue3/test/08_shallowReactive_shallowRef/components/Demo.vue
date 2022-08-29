<template>
  <h2>{{person.name}}</h2>
  <h2>{{person.age}}</h2>
  <h2>{{person.job.type}}</h2>
  <h2>{{x}}</h2>
  <h2>{{x1.x}}</h2>
  <button @click="person.name+='~'">修改name</button>
  <button @click="person.age+=1">修改age</button>
  <button @click="person.job.type='python'">修改job</button>
  <button @click="x++">修改x</button>
  <button @click="x1.x++">修改x1</button>
</template>

<script>
import { shallowReactive,shallowRef,ref } from "vue";
export default {
  name: "Demo",
  components: {},
  setup() {
    /* 创建一个响应式代理，它跟踪其自身 property 的响应性，
    但不执行嵌套对象的深层响应式转换 (暴露原始值)。 
    只考虑第一层的响应式
    */
    let person = shallowReactive({
        name:'hello',
        age:18,
        job:{
          type:'java'
        }
    })
    // 创建一个跟踪自身 .value 变化的 ref，但不会使其值也变成响应式的。
    //只处理基本数据类型的响应式，不进行对象的响应式
    let x = ref(0)
    let x1 = shallowRef({
      x:0
    })
    return {
      person,
      x,
      x1
    }
  },
};
</script>

<style>
</style>
