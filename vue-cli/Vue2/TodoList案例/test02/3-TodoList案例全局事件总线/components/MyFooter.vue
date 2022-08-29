<template>
  <div v-show="total">
      <label>
          <!-- <input type="checkbox" :checked="isAll" @change="checkAll"> -->
          <input type="checkbox" v-model="isAll">
      </label>
      <span>
          <span>已完成{{doneTotal}}/</span>全部{{total}}
      </span>
      <button @click="clearAll">清楚已完成任务</button>
  </div>
</template>

<script>
    export default {
        name:'MyFooter',
        props: ['todos'],//,'checkAlltodo','clearAlltodo'
        computed: {
            doneTotal(){
                return this.todos.reduce((pre, current) => pre + (current.done ? 1 : 0),0)
            },
            total(){
                return this.todos.length
            },
            isAll:{
                get(){
                    return this.total == this.doneTotal
                },
                set(value){
                    // this.checkAlltodo(value)
                    this.$emit('checkAlltodo',value)
                }
            }
        },
        methods: {
            // checkAll(e){
            //     this.checkAlltodo(e.target.checked)
            // },
            clearAll(){
                // this.clearAlltodo()
                this.$emit('clearAlltodo')
            }
        }
    }
</script>

<style>

</style>