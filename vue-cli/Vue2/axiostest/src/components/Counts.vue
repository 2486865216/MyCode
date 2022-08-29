<template>
  <div id="box">
    <h1>n = {{sum}}</h1>
    <h3>当前数值放大十倍:{{bigSum}}</h3>
    <h3>{{hello}} {{word}}</h3>
    <h3>下方组件的总人数：{{personlist.length}}</h3>
    <select v-model.number="n">
      <option value="1">1</option>
      <option value="2">2</option>
      <option value="3">3</option>
    </select>
    <button @click="decrement(n)">-</button>
    <button @click="increment(n)">+</button>
    <button @click="incrementAdd(n)">和为奇数在加</button>
    <button @click="incrementWait(n)">等一等在加</button>
  </div>
</template>

<script>
import {mapState,mapGetters,mapMutations,mapActions} from 'vuex'
export default {
  name: "Counts",
  data() {
    return {
      n: 1,
    };
  },
  computed:{
    ...mapState('count',['sum', 'hello','word']),
    ...mapState('person',['personlist']),
    // bigSum(){
    //   return this.$store.getters['count/bigSum']
    // },
    ...mapGetters('count',{bigSum:'bigSum'}),
  },
  methods: {
    ...mapMutations('count',['increment', 'decrement']),
    ...mapActions('count',['incrementWait', 'incrementAdd']),
  },
};
</script>

<style>
button {
  margin: 20px;
}
</style>