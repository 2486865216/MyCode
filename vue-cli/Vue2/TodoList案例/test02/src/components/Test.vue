<template>
  <div>
      <button @click="isshow = !isshow">显示/隐藏</button>
      <transition :appear="true">
          <h1 v-show="isshow">Hello Word!</h1>
      </transition>
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
    background-color: antiquewhite  ;
}
.v-enter-active{
    animation: test 1s 
}
.v-leave-active{
    animation: test 1s reverse
}
@keyframes test{
    from{
        transform: translateX(-100%)
    }
    to{
        transform: translateX(0);
    }
}
</style>