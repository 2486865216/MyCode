<template>
  <div>
      <input type="text" placeholder="请输入!" v-model="title" @keyup.enter="add">
  </div>
</template>

<script>
    import {nanoid} from 'nanoid'
    export default {
        name:'MyHeader',
        // props: ['addTodos'],
        data () {
            return {
                title:''      
            }
        },
        methods: {
            add(){
                if(!this.title.trim()) return alert('输入不能为空！')
                const todoObj = {id:nanoid(),title:this.title,done:false}
                //将数据传到父组件App
                // this.addTodos(todoObj);
                this.$emit('addTodos',todoObj)
                this.title=''
            }
        }
    }
</script>

<style>

</style>