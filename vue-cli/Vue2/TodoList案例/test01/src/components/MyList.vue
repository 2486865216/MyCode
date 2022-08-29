<template>
  <div>
    <ul>
      <MyItem
        v-for="todoObj in todos"
        :key="todoObj.id"
        :todo="todoObj"
        :toChecked="toChecked"
        :toDelete="toDelete"
      />
    </ul>
  </div>
</template>

<script>
import MyItem from "./MyItem.vue";
export default {
  name: "MyList",
  components: {
    MyItem,
  },
  props: ["todos", "toChecked","toDelete"],
};
</script>

<style>
ul {
  list-style-type: none;
  text-align: left;
}
</style>