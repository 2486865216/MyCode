<template>
  <div id="school">
      <h1>学校名称：{{name}}</h1>
      <h1>学校地址：{{address}}</h1>
      <button @click="sendSchoolName">点我向父组件App传递name</button>
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
    props: ['getSchoolName'],
    methods: {
        sendSchoolName(){
            this.getSchoolName(this.name)
        }
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