//object表示一个js对象
let a: object
a = {}
a = function () {

}
//{}用来指定对象中可以包含哪些属性
//在属性名后加上？，表示属性是可选的
let b: { name: string, age?: number }
b = {
    name: 'hello'
}

//[propName : string] : any 表示任意类型的属性
let c: { name: string, [propName: string]: any }
c = {
    name: 'hello',
    a: '1'
}
//设置函数的类型声明
let d: (a: number, b: number) => number
d = function (a, b) {
    return a + b
}
//字符串数组
let e: string[]
let f: Array<string>

//元组；元组就是固定长度的数组
let g: [string, number]
g = ['hello', 123]
/**
 * enum枚举
 */
enum Gender{
    Male = 0,
    Female = 1
}
let h: { name: string, gender: 0 | 1 }
h = {
    name: 'hello',
    gender: Gender.Male
}
let i: {name: string} & {age: number}
i = {
    name: 'hello',
    age: 18
}
//类型别名
type myType = 1 | 2 | 3
let j: myType
j = 1