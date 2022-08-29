<template>
  <div id="box">
      <h1>{{title}}</h1>
      <!-- <ul>
          <li v-for="(item, index) in listdata" :key="index">{{item}}</li>
      </ul> -->
      <!-- 定义一个插槽，等组件的使用者填充 -->
      <slot name="center">当使用者没有传递具体结构时，我会出现</slot>
      <slot name="footer">当使用者没有传递具体结构时，我会出现</slot>
  </div>
</template>

<script>
export default {
    name:'Category',
    props:['title','listdata']
}
</script>

<style>
#box{
    width: 300px;
    background-color: aqua;
    text-align: center;
}
h1{
    background-color: orange;
}
</style>