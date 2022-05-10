//object表示一个js对象
var a;
a = {};
a = function () {
};
//{}用来指定对象中可以包含哪些属性
//在属性名后加上？，表示属性是可选的
var b;
b = {
    name: 'hello'
};
//[propName : string] : any 表示任意类型的属性
var c;
c = {
    name: 'hello',
    a: '1'
};
//设置函数的类型声明
var d;
d = function (a, b) {
    return a + b;
};
//字符串数组
var e;
var f;
//元组；元组就是固定长度的数组
var g;
g = ['hello', 123];
/**
 * enum枚举
 */
var Gender;
(function (Gender) {
    Gender[Gender["Male"] = 0] = "Male";
    Gender[Gender["Female"] = 1] = "Female";
})(Gender || (Gender = {}));
var h;
h = {
    name: 'hello',
    gender: Gender.Male
};
var i;
i = {
    name: 'hello',
    age: 18
};
var j;
j = 1;
