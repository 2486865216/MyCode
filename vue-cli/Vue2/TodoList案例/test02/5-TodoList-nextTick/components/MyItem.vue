<template>
  <div>
    <li>
      <label>
        <input
          type="checkbox"
          :checked="todo.done"
          @change="handleChecked(todo.id)"
        />

        <span v-show="!todo.isEdit">{{ todo.title }}</span>
        <input
          v-show="todo.isEdit"
          type="text"
          :value="todo.title"
          @blur="handleBlur(todo,$event)"
          ref="inputTitle"
        />
      </label>
      <button @click="handleDelect(todo.id)">删除</button>
      <button v-show="!todo.isEdit" @click="handleEdit(todo)">编辑</button>
    </li>
  </div>
</template>

<script>
export default {
  name: "MyItem",
  props: ["todo"],
  mounted() {},
  methods: {
    handleChecked(id) {
      // this.toChecked(id)
      this.$bus.$emit("cheackTodo", id);
    },
    handleDelect(id) {
      if (confirm("Are you sure delete?")) {
        // this.toDelete(id)
        this.$bus.$emit("deleteTodo", id);
      }
    },
    handleEdit(todo) {
      if(todo.hasOwnProperty.call('isEdit')){
        todo.isEdit = true
      }else{
        this.$set(todo, "isEdit", true);
      }
      this.$nextTick(function(){
        this.$refs.inputTitle.focus()
      })
    },
    handleBlur(todo,e){
      todo.isEdit = false
      if (!e.target.value.trim()) return alert('输入不能为空!')
      this.$bus.$emit('updateTodo',todo.id,e.target.value)
    }
  },
};
</script>

<style>
li button {
  position: relative;
  left: 50px;
  display: none;
}
li:hover button {
  display: inline-block;
}
</style>