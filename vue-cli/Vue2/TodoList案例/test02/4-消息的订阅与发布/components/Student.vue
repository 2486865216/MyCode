<template>
    <div id="student">
        <h1>学生姓名：{{name}}</h1>
        <h1>学生性别：{{sex}}</h1>
        <button @click="sendStudentName">向school组件发送data</button>
    </div>
</template>

<script>
import pubsub from 'pubsub-js'
export default {
    name:'Student',
    data () {
        return {
            name:'张三',
            sex:'男',
        }
    },
    methods: {
        sendStudentName(){
            // this.$bus.$emit('hello',this.name)
            pubsub.publish('hello',this.name)
        }
    }
}
</script>

<style scoped>
#student{
    background-color: gray;
    padding: 5px;
    margin: 5px;
}
</style>