let a : 10
//任意类型,可以给任意变量赋值
let b : any
//unknown未知类型,不可以给其它变量赋值
let c : unknown
let d : string | boolean
//类型断言，告诉解析器变量的实际类型
let e = d as string
e = <string> d

//void表示返回空
function fun1(): void{}
//never表示永远不会返回结果
function fun2(): never{
    throw new Error("Error")
}
