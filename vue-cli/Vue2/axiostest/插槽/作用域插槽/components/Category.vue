<template>
  <div id="box">
      <h1>{{title}}</h1>
      <slot :games="games">默认内容</slot>
  </div>
</template>

<script>
export default {
    name:'Category',
    props:['title'],
    data () {
        return {
            games:['xxx','xxx','xxx']
        }
    }
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