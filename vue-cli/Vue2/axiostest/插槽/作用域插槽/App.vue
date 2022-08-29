<template>
  <div id="app">
    <Categroy title="游戏">
      <template scope="xxx">
        <ul>
          <li v-for="(item, index) in xxx.games" :key="index">
            {{item}}
          </li>
        </ul>
      </template>
    </Categroy>
    <Categroy title="游戏">
      <template slot-scope="xxx">
        <ol>
          <li v-for="(item, index) in xxx.games" :key="index">
            {{item}}
          </li>
        </ol>
      </template>
    </Categroy>
    <Categroy title="游戏">
      <template slot-scope="xxx">
          <h4 v-for="(item, index) in xxx.games" :key="index">
            {{item}}
          </h4>
      </template>
    </Categroy>
  </div>
</template>

<script>
import Categroy from './components/Category.vue'
export default {
    name:'App',
    components: {
      Categroy
    },
}
</script>

<style>
#app{
  display: flex;
  justify-content: space-around;
}
img{
  width: 100%;
}
</style>