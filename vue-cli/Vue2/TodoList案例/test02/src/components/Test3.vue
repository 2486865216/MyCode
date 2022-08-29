<template>
  <div>
    <button @click="isshow = !isshow">显示/隐藏</button>
    <transition-group
      :appear="true"
      name="animate__animated animate__bounce"
      enter-active-class="animate__swing"
      leave-active-class="animate__backOutUp"
    >
      <h1 v-show="isshow" key="1">Hello Word!</h1>
      <h1 v-show="isshow" key="2">Hello Vue!</h1>
    </transition-group>
  </div>
</template>

<script>
import "animate.css";
export default {
  name: "Test",
  data() {
    return {
      isshow: "true",
    };
  },
};
</script>

<style scoped>
h1 {
  background-color: antiquewhite;
}
</style>