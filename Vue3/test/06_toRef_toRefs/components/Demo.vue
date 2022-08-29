<template>
  <h2>{{person.name}}</h2>
  <h2>{{person.age}}</h2>
  <h2>{{person.job.type}}</h2>
  <button @click="person.name+='~'">修改name</button>
  <button @click="person.age+=1">修改age</button>
  <button @click="person.job.type='python'">修改job</button>
</template>

<script>
import { reactive,toRef,toRefs } from "vue";
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
    /* 可以用来为源响应式对象上的某个 property 新创建一个 ref。
    然后，ref 可以被传递，它会保持对其源 property 的响应式连接。 
    即使源 property 不存在，toRef 也会返回一个可用的 ref。
    这使得它在使用可选 prop 时特别有用，可选 prop 并不会被 toRefs 处理。*/
    const name1 = toRef(person,'name')
    console.log(name1);

    /* 将响应式对象转换为普通对象，其中结果对象的每个 property 都是
    指向原始对象相应 property 的 ref。 */

    const person1 = toRefs(person)
    console.log(person1);
    return {
      person
    }
  },
};
</script>

<style>
</style>
