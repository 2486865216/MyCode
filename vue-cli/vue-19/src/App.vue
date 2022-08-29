<template>
    <div>
        <img src="./assets/logo.png" alt="logo">
        <!-- <h1 ref="title">Hello word</h1>
        <button @click="showdome" ref="btu">Button</button>
        <School ref="school"></School>
        <Student name2='hello' name3='vue'></Student> -->
        <mixin/>
    </div>
</template>

<script>
    // import School from './components/School.vue'
    // import Student from './components/Student.vue'
    import mixin from './components/mixin.vue'
    export default {
        name:'App',
        components:{
            // School,
            // Student
            mixin
        },
        // methods:{
        //     showdome(){
        //         console.log(this.$refs)
        //         console.log(this.$refs.btu)
        //         console.log(this.$refs.title)
        //         console.log(this.$refs.school)
        //     }
        // }
    }
</script>

<style>

</style>