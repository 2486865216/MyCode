var a;
//任意类型,可以给任意变量赋值
var b;
//unknown未知类型,不可以给其它变量赋值
var c;
var d;
//类型断言，告诉解析器变量的实际类型
var e = d;
e = d;
//void表示返回空
function fun1() { }
//never表示永远不会返回结果
function fun2() {
    throw new Error("Error");
}
