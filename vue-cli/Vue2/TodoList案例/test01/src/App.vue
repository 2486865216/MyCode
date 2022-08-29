<template>
  <div id="app">
    <!-- v-model绑定的不能是props传过来的值，因为props是不可以修改的 -->
    <img alt="Vue logo" src="./assets/logo.png" />
    <!-- <MyHeader :addTodos="addTodos"/> -->
    <MyHeader @addTodos="addTodos"/>
    <MyList :todos="todos" :toChecked="toChecked" :toDelete="toDelete"/>
    <MyFooter :todos="todos" @checkAlltodo="checkAlltodo" @clearAlltodo="clearAlltodo"/>
  </div>
</template>

<script>
import MyHeader from "./components/MyHeader.vue";
import MyList from "./components/MyList.vue";
import MyFooter from "./components/MyFooter.vue";
export default {
  name: "App",
  components: {
    MyHeader,
    MyList,
    MyFooter,
  },
  data() {
    return {
      todos: JSON.parse(localStorage.getItem('todos')) || [],
    };
  },
  methods: {
    addTodos(todoObj){
      this.todos.unshift(todoObj)
    },
    toChecked(id){
      this.todos.forEach(todo =>{
        if(todo.id==id) todo.done = ! todo.done
      })
    },
    toDelete(id){
      this.todos = this.todos.filter(todo => {return todo.id!==id});
    },
    checkAlltodo(done){
      this.todos.forEach(todo => {
        todo.done  = done
      })
    },
    clearAlltodo(){
      this.todos = this.todos.filter(todo => {
        return !todo.done
      })
    }
  },
  watch: {
    todos:{
      deep:true,
      handler(value){
        localStorage.setItem('todos',JSON.stringify(value))
      }
    }
  }
};
</script>

<style>
#app {
  margin: auto;
  text-align: left;
  width: 500px;
  height: 500px;
  border: 2px solid red;
}
</style>
