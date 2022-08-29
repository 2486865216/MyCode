<template>
  <div id="search">
      <a-input-search
      placeholder="input search text"
      enter-button="Search"
      size="large"
      @search="onSearch"
      v-model="value"
    />
  </div>
</template>

<script>
import axios from 'axios'
export default {
    name:'Search',
    data () {
        return {
            value:''
        }
    },
    methods: {
        onSearch(){
            this.$bus.$emit('updataListData',{isFirst:false,isLoad:true,errMsg:'',users:[]})
            this.$http.get(`https://api.github.com/search/users?q=${this.value}`).then(
                response => {
                    console.log(response.data.items);
                    this.$bus.$emit('updataListData',{isLoad:false,errMsg:'',users: response.data.items})
                },
                error => {
                    console.log(error.message)
                    this.$bus.$emit('updataListData',{isLoad:false,errMsg:error.message,users:[]})
                }
            )
        }
    }
}
</script>

<style scoped>
#search{
    width: 300px;
    margin: auto;
}
</style>