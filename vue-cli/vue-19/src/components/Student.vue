<template>
    <div>
        <h2>{{name}}</h2>
        <h2>{{name1}}</h2>
        <h3>{{name2}}</h3>
        <h3>{{name3}}</h3>
    </div>
</template>
<script>
    export default {
        name:'Student',
        data(){
            return{
                name:'Vue',
                name1:'Good!'
            }
        },
        //只接受
        // props:['name2','name3']
        //限制类型
        // props:{
        //     name2:String,
        //     name3:String
        // }
        props:{
            name2:{
                type:String,//类型
                required:true,//是否必要
                default:'hello'//默认值
            },
            name3:{
                type:String,//类型
                required:true,//是否必要
                default:'vue'//默认值
            }
        }
    }
</script>
<style>
</style>