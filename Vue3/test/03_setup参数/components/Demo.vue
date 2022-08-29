<template>
  <img alt="Vue logo" src="../assets/logo.png" />
  <h2>姓名:{{ name }}</h2>
  <h2>年龄:{{ age }}</h2>
  <slot name="test"></slot>
  <button @click="test">test</button>
</template>

<script>
export default {
  name: "Demo",
  components: {},
  props: ["msg", "school"],
  emits:['hello'],
  setup(props, context) {
    console.log(props);
    console.log(context);

    // Attribute (非响应式对象，等同于 $attrs)
    console.log(context.attrs)

    // 插槽 (非响应式对象，等同于 $slots)
    console.log(context.slots)

    function test(){
        //触发自定义事件
        context.emit('hello',666)
    }

    let name = "hello";
    let age = 18;
    return {
      name,
      age,
      test
    };
  },
};
</script>

<style>
</style>
