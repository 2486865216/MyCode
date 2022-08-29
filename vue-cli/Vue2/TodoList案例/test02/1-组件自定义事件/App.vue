<template>
  <div id="app">
    <h1>Hello word!,学生姓名是：{{studentName}}</h1>
    <!-- 通过父组件向子组件传递函数类型的props实现：子给父传递数据 -->
    <School :getSchoolName="getSchoolName"/>

    <!-- 通过父组件向子组件绑定一个自定义事件实现：子给父传递数据 (第一种写法：使用@或v-on)-->
    <!-- <Student v-on:test="getStudentName"/> -->
    <!-- <Student v-on:test.once="getStudentName"/> -->

    <!-- 通过父组件向子组件绑定一个自定义事件实现：子给父传递数据 (第二种写法：使用ref)-->
    <Student ref="student" @click.native="show"/><!--native生命事件是原生事件-->
  </div>
</template>

<script>
import Student from './components/Student.vue'
import School from './components/School.vue'

export default {
  name: 'App',
  data () {
    return {
      studentName:''
    }
  },
  components: {
    School,
    Student
  },
  methods: {
    getSchoolName(name){
      console.log('App收到了学校名:',name);
    },
    getStudentName(name){
      console.log('App收到了学生名:',name);
      this.studentName = name
    },
    show(){
      alert('123')
    }
  },
  mounted () {
    this.$refs.student.$on('test',this.getStudentName)//绑定一个自定义事件
    // this.$refs.student.$once('test',this.getStudentName) //绑定一个自定义事件(一次性)
  }
}
</script>

<style scoped>
#app {
  background-color: antiquewhite;
  padding: 5px;
  margin: 5px;
}
</style>
