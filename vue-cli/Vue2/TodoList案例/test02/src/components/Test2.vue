<template>
  <div>
      <button @click="isshow = !isshow">显示/隐藏</button>
      <transition-group :appear="true">
          <h1 v-show="isshow" key="1">Hello Word!</h1>
          <h1 v-show="isshow" key="2">Hello Vue!</h1>
      </transition-group>
  </div>
</template>

<script>
export default {
    name:'Test',
    data () {
        return {
            isshow:'true'
        }
    }

}
</script>

<style scoped>
h1{
    background-color: antiquewhite;
}
/* 进入的起点,离开的终点 */
.v-enter,.v-leave-to{
    transform: translateX(-100%);
}
.v-enter-active,.v-leave-active{
    transition: 0.5s linear;
}
/* 进入的终点,离开的起点 */
.v-enter-to,.v-leave{
    transform: translateX(0);
}
</style>