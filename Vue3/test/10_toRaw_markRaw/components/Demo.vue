<template>
  <h2>{{ person.name }}</h2>
  <h2>{{ person.age }}</h2>
  <h2>{{ person.job.type }}</h2>
  <button @click="person.name += '~'">修改name</button>
  <button @click="person.age += 1">修改age</button>
  <button @click="person.job.type = 'python'">修改job</button>
</template>

<script>
import { reactive, toRaw, markRaw ,isReactive } from "vue";
export default {
  name: "Demo",
  components: {},
  setup() {
    let person = reactive({
      name: "hello",
      age: 18,
      job: {
        type: "java",
      },
    });
    /* 返回 reactive 或 readonly 代理的原始对象。
    这是一个“逃生舱”，可用于临时读取数据而无需承担代理访问/跟踪的开销，
    也可用于写入数据而避免触发更改。不建议保留对原始对象的持久引用。请谨慎使用。 */
    const foo = {};
    const reactiveFoo = reactive(foo);
    console.log(toRaw(reactiveFoo) === foo); // true

    //标记一个对象，使其永远不会转换为 proxy。返回对象本身。
    const foo1 = markRaw({});
    console.log(isReactive(reactive(foo1))); // false

    // 嵌套在其他响应式对象中时也可以使用
    const bar = reactive({ foo1 });
    console.log(isReactive(bar.foo1)); // false

    return {
      person,
    };
  },
};
</script>

<style>
</style>
