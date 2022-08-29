<template>
  <h2>{{person1.name}}</h2>
  <h2>{{person1.age}}</h2>
  <h2>{{person1.job.type}}</h2>
  <button @click="person1.name+='~'">修改name</button>
  <button @click="person1.age+=1">修改age</button>
  <button @click="person1.job.type='python'">修改job</button>
  <h2>{{person2.name}}</h2>
  <h2>{{person2.age}}</h2>
  <h2>{{person2.job.type}}</h2>
  <button @click="person2.name+='~'">修改name</button>
  <button @click="person2.age+=1">修改age</button>
  <button @click="person2.job.type='python'">修改job</button>
</template>

<script>
import { reactive,readonly,shallowReadonly } from "vue";
export default {
  name: "Demo",
  components: {},
  setup() {
    let person = reactive({
        name:'hello',
        age:18,
        job:{
          type:'java'
        }
    })
   /* 接受一个对象 (响应式或纯对象) 或 ref 并返回原始对象的只读代理。
   只读代理是深层的：任何被访问的嵌套 property 也是只读的。 */
    let person1 = readonly(person)
    /* 创建一个 proxy，使其自身的 property 为只读，
    但不执行嵌套对象的深度只读转换 (暴露原始值)。 
    只考虑第一层的响应式
    */
    let person2 = shallowReadonly(person)
    return {
      person,
      person1,
      person2
    }
  },
};
</script>

<style>
</style>
