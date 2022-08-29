<template>
    <div id="student">
        <h1>学生姓名：{{name}}</h1>
        <h1>学生性别：{{sex}}</h1>
        <h1>n:{{n}}</h1>
        <button @click="add">点我n+1</button>
        <button @click="sendStudentName">点我向父组件App传递name</button>
        <button @click="unbind">解除自定义事件的绑定</button>
        <button @click="death">销毁当前Student组件实例（vc）</button>
    </div>
</template>

<script>
export default {
    name:'Student',
    data () {
        return {
            name:'张三',
            sex:'男',
            n:0
        }
    },
    methods: {
        add(){
            console.log('add被调用了!')
            this.n++
        },
        sendStudentName(){
            this.$emit('test',this.name)
        },
        unbind(){
            this.$off('test')//解绑一个自定义事件
            //this.$off(['test','demo'])//解绑多个自定义事件
            //this.$off()//解绑所有自定义事件
        },
        death(){
            this.$destroy()//销毁当前Student组件实例,销毁后组件的所有自定义事件全部都不奏效
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