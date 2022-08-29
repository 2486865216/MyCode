<template>
  <div>
      <!-- 展示用户列表 -->
      <a-row v-show="info.users.length">
        <a-col :span="6" v-for="user in info.users" :key="user.logo">
            <a-card style="width : 200px">
                <a :href="user.html_url">
                    <img style="width : 200px" :src="user.avatar_url" alt="logo">
                </a>
            </a-card>
        </a-col>
      </a-row>
      <h1 v-show="info.isFirst">Welcome Use</h1>
      <h1 v-show="info.isLoad">Loading</h1>
      <h1 v-show="info.errMsg">{{info.errMsg}}</h1>
  </div>
</template>

<script>
export default {
    name:'List',
    data () {
        return {
            info:{
                isFirst:true,
                isLoad:false,
                errMsg:'',
                users:[]
            }
        }
    },
    mounted () {
        this.$bus.$on('updataListData',(dataObj)=>{
            this.info = {...this.info, ...dataObj}
        })
    }
}
</script>

<style>

</style>