<template>
    <div class="demo">
        <h1>{{name}}</h1>
        <h1>{{name1}}</h1>
    </div>
</template>
<script>
    export default {
        name:'School',
        data(){
            return{
                name:'hello',
                name1:'word!'
            }
        }
    }
</script>
<style>
    .demo{
        background-color: blue;
    }
</style>