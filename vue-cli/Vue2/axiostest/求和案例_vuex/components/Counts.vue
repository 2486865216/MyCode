<template>
  <div id="box">
    <h1>n = {{sum}}</h1>
    <h3>当前数值放大十倍:{{bigSum}}</h3>
    <h3>{{hello}} {{word}}</h3>
    <select v-model.number="n">
      <option value="1">1</option>
      <option value="2">2</option>
      <option value="3">3</option>
    </select>
    <button @click="decrement(n)">-</button>
    <button @click="increment">+</button>
    <button @click="incrementAdd(n)">和为奇数在加</button>
    <button @click="incrementWait">等一等在加</button>
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
    // 借助mapState生成计算属性，从state中读取数据（对象写法）
    // ...mapState({sum:'sum',hello:'hello',word:'word'}),
    // 借助mapState生成计算属性，从state中读取数据（数组写法）计算属性名与state属性名一致
    ...mapState(['sum','hello','word']),
    // bigSum(){
    //   return this.$store.getters.bigSum 
    // }
    //借助mapGetters生成计算属性，从getters中读取数据（对象写法）
    ...mapGetters({bigSum:'bigSum'}),
    // 借助mapGetters生成计算属性，从getters中读取数据（数组写法）计算属性名与state属性名一致
    ...mapGetters(['bigSum']),
  },
  methods: {
    increment() {
      // this.$store.dispatch('increment', this.n)
      this.$store.commit('count.increment',this.n)
    },
    decrement() {
      // this.$store.dispatch('decrement', this.n)
      // this.$store.commit('decrement',this.n)
    },
    // <button @click="decrement(n)">-</button>
    // 借助mapMutation生成对应的方法，方法中会调用commit去联系mutations(对象写法)
    // ...mapMutations({decrement:'decrement'}),
    // 借助mapMutation生成对应的方法，方法中会调用commit去联系mutations(数组写法)
    ...mapMutations(['decrement']),
    // incrementAdd() {
    //   this.$store.dispatch('incrementAdd', this.n)
    // },

    // <button @click="incrementAdd(n)">和为奇数在加</button>
    // 借助mapActions生成对应的方法，方法中会调用dispatch去联系actions(对象写法)
    // ...mapActions({incrementAdd:'incrementAdd'}),
    // 借助mapActions生成对应的方法，方法中会调用dispatch去联系actions(数组写法)
    ...mapActions(['incrementAdd']),
    incrementWait() {
        this.$store.dispatch('incrementWait', this.n)
    },
  },
};
</script>

<style>
button {
  margin: 20px;
}
</style>