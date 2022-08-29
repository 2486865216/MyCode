<template>
  <div>
    <button @click="isshow = !isshow">点我弹窗</button>
    <teleport to="body">
        <div v-if="isshow">
      <h1>test</h1>
      <button>关闭弹窗</button>
    </div>
    </teleport>
  </div>
</template>

<script>
import { ref } from "@vue/reactivity";
export default {
  name: "Test",
  setup() {
    let isshow = ref(false);
    return { isshow };
  },
};
</script>

<style>
</style>