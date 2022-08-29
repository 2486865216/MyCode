<template>
  <div>
    <h1>人员列表</h1>
    <h2>Count组件求和：{{sum}}</h2>
    <input type="text" placeholder="请输入名字" v-model="name">
    <button @click="add">添加</button>
    <button @click="addPersonServer">添加一个人名字随机</button>
    <ul>
      <li v-for="p in personlist" :key="p.id">{{ p.name }}</li>
    </ul>
  </div>
</template>

<script>
import {nanoid} from 'nanoid'
import {mapState} from "vuex";

export default {
  name: 'Person',
  data() {
    return {
      name: ''
    }
  },
  computed: {
    // personlist() {
    //   return this.$store.state.person.personlist
    // },
    // sum(){
    //   return this.$store.state.count.sum
    // },
    ...mapState('person',{personlist:'personlist'}),
    ...mapState('count',{sum:'sum'}),
  },
  methods: {
    add() {
      const personId = {id: nanoid(), name: this.name}
      this.$store.dispatch('person/add', personId)
      this.name = ''
    },
    addPersonServer(){
      this.$store.dispatch('person/addPersonServer')
    }
  }
}
</script>

<style>

</style>