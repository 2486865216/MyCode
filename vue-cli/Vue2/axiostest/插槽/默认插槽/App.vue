<template>
  <div id="app">
    <Categroy title="foods" :listdata="foods">
      <img src="./assets/logo.png" alt="logo">
    </Categroy>
    <Categroy title="fimls" :listdata="fimls">
      <ul>
          <li v-for="(item, index) in fimls" :key="index">{{item}}</li>
      </ul>
    </Categroy>
    <Categroy title="games" :listdata="games">

    </Categroy>
  </div>
</template>

<script>
import Categroy from './components/Category.vue'
export default {
    name:'App',
    components: {
      Categroy
    },
    data () {
      return {
        foods:['xxx','xxx','xxx'],
        fimls:['xxx','xxx','xxx'],
        games:['xxx','xxx','xxx'],
      }
    }
}
</script>

<style>
#app{
  display: flex;
  justify-content: space-around;
}
img{
  width: 100%;
}
</style>