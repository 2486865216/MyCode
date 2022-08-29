<template>
  <div id="box">
    <h1>n = {{ sum }}</h1>
    <select v-model.number="n">
      <option value="1">1</option>
      <option value="2">2</option>
      <option value="3">3</option>
    </select>
    <button @click="decrement">-</button>
    <button @click="increment">+</button>
    <button @click="incrementAdd">和为奇数在加</button>
    <button @click="incrementWait">等一等在加</button>
  </div>
</template>

<script>
export default {
  name: "Counts",
  data() {
    return {
      n: 1,
      sum: 0,
    };
  },
  methods: {
    increment() {
      this.sum += this.n
    },
    decrement() {
      this.sum -= this.n
    },
    incrementAdd() {
      if(this.sum % 2){
        this.sum += this.n
      }
    },
    incrementWait() {
      setTimeout(()=>{
        this.sum += this.n
      },1000)
    },
  },
};
</script>

<style>
button {
  margin: 20px;
}
</style>