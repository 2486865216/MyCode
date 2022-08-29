<template>
  <div>
    <li>
      <label>
        <input type="checkbox" :checked="todo.done" @change="handleChecked(todo.id)"/>
        
        <!-- vue只会进行浅层次的监视，只监视对象的地址变化，不监视对象具体值的变化 -->
        
        <!-- 以下代码也能实现功能，但是不太推荐，因为有点违反原则，修改了props -->
        <!-- <input type="checkbox" v-model="todo.done"/> -->
        
        <span>{{todo.title}}</span>
      </label>
      <button @click="handleDelect(todo.id)">删除</button>
    </li>
  </div>
</template>

<script>
export default {
  name: "MyItem",
  props: ['todo'],
  mounted () {
  },
  methods: {
    handleChecked(id){
      // this.toChecked(id)
      this.$bus.$emit('cheackTodo',id)
    },
    handleDelect(id){
      if(confirm("Are you sure delete?")){
        // this.toDelete(id)
        this.$bus.$emit('deleteTodo',id)
      }
    }
  }
};
</script>

<style>
li button{
  position: relative;
  left: 50px;
  display: none;
}
li:hover button{
  display: inline-block;
}
</style>