<template>
  姓:<input v-model="person.firstName" />
  <br />
  名:<input v-model="person.lastName" />
  <br />
  <span>全名:{{ person.fullName }}</span>
  <br />
  全名:<input v-model="person.fullName" />
  <br />
  <h1>n={{ n }}</h1>
  <h1>n={{ m }}</h1>
  <button @click="n++">点我加1</button>
  <button @click="m++">点我加1</button>
  <br>
  <h2>{{user.name}}</h2>
  <button @click="user.name+='xxx'">修改</button>
  <h2>{{user.age}}</h2>
  <button @click="user.age+='xxx'">修改</button>
  <h2>{{user.job.type}}</h2>
  <button @click="user.job.type+='xxx'">修改</button>
</template>

<script>
import { reactive, computed, ref, watch } from "vue";
export default {
  name: "Demo",
  components: {},
  setup() {
    let person = reactive({
      firstName: "hello",
      lastName: "word",
      fullName: "",
    });
    //计算属性
    // 接受一个 getter 函数，并根据 getter 的返回值返回一个不可变的响应式 ref 对象。
    //     const count = ref(1)
    // const plusOne = computed(() => count.value + 1)

    // console.log(plusOne.value) // 2

    // plusOne.value++ // 错误
    // person.fullName = computed(() => {
    //   return person.firstName + "-" + person.lastName;
    // });

    person.fullName = computed({
      get() {
        return person.firstName + "-" + person.lastName;
      },
      set(value) {
        const arr = value.split("-");
        person.firstName = arr[0];
        person.lastName = arr[1];
      },
    });
    
    
    
    let n = ref(0);
    let m = ref(1)
    //监视
    // 直接侦听一个 ref
    // const count = ref(0)
    // watch(count, (count, prevCount) => {
    //   /* ... */
    // })
    // watch(n, (newvalue, oldvalue) => {
    //   console.log(n, oldvalue, newvalue);
    // });

    //     watch([fooRef, barRef], ([foo, bar], [prevFoo, prevBar]) => {
    //   /* ... */
    // })

    // watch([n,m], ([newvalue1, newvalue2],[oldvalue1, oldvalue2]) => {
    //   console.log(n, oldvalue1, newvalue1);
    //   console.log(m, oldvalue2, newvalue2);
    // });

    watch([n,m], ([newvalue1, newvalue2],[oldvalue1, oldvalue2]) => {
      console.log(n, oldvalue1, newvalue1);
      console.log(m, oldvalue2, newvalue2);
    },{deep:true,immediate:true});

    let user = reactive({
      name:'hello',
      age:18,
      job:{
        type:'java'
      }
    })
    // 监视reactive所定义的一个响应式数据中的全部属性
    //无法正确获取oldvalue
    //强制开启了深度监视，deep配置失效
    watch(user,(user, prevuser)=>{
      console.log('全部属性');
      console.log(user,prevuser);
    })
    // 监视reactive所定义的一个响应式数据中的一个属性
    watch(()=>user.name,(name, prevname)=>{
      console.log('一个属性');
      console.log(name, prevname);
    })
    // 监视reactive所定义的一个响应式数据中的某些属性
    watch([()=>user.name,()=>user.age],(value, prevvalue)=>{
      console.log('某些属性');
      console.log(value, prevvalue);
    })
    //特殊情况
    watch(()=>user.job,(job,prevjob)=>{
      console.log('特殊情况');
      console.log(job,prevjob)
    },{deep:true})
    return {
      person,
      n,
      m,
      user
    };
  },
};
</script>

<style>
</style>
