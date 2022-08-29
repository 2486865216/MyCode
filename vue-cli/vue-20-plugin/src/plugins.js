export default{
    install(Vue){
        console.log("plugins is used!");
        Vue.directive('fbind', {
            // 指令成功绑定时
            bind(element,binding){
                console.log(element)
                console.log(binding)
                console.log(1);
            },
            // 指令所在的元素被插入页面时
            inserted(element,binding){
                console.log(element)
                console.log(binding)
                console.log(2)
            },
            // 指令所在的模板被重新解析时
            update(element,binding){
                console.log(element)
                console.log(binding)
                console.log(3)
            }
        })
    }
}