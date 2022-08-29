<template>
  <div id="school">
      <h1>学校名称：{{name}}</h1>
      <h1>学校地址：{{address}}</h1>
  </div>
</template>

<script>
export default {
    name:'School',
    data () {
        return {
            name:'School',
            address:'268'
        }
    },
    mounted () {
        this.$bus.$on('hello',(data)=>{
            console.log('School收到data:',data)
        })
    },
    beforeDestroy () {
        this.$bus.$off('hello')
    }
}
</script>

<style scoped>
#school{
    background-color: rgb(0, 255, 136);
    padding: 5px;
    margin: 5px;
}
</style>