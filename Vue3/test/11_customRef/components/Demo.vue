<template>
  <input type="text" v-model="keyword" />
  <h1>{{ keyword }}</h1>
</template>

<script>
import { customRef } from "vue";
export default {
  name: "Demo",
  components: {},
  setup() {
    /* 创建一个自定义的 ref，并对其依赖项跟踪和更新触发进行显式控制。
    它需要一个工厂函数，该函数接收 track 和 trigger 函数作为参数，
    并且应该返回一个带有 get 和 set 的对象。 */
    function myRef(value) {
      let time;
      return customRef((track, trigger) => {
        return {
          get() {
            //track:追踪,      追踪value的变化
            track();
            return value;
          },
          set(newvalue) {
            clearTimeout(time);
            time = setTimeout(() => {
              value = newvalue;
              //trigger:触发,    重新解析模板
              trigger();
            }, 500);
          },
        };
      });
    }

    let keyword = myRef("keyword");
    return {
      keyword,
    };
  },
};
</script>

<style>
</style>
