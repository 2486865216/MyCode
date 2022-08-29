class Person{
    name: string
    age: number
    static gender: string = 'word'
    //readonly只读
    readonly test: string = 'test'
    constructor(name: string, age: number){
        this.name = name
        this.age = age
    }
    sayHello(){
        console.log('sayHello')
    }
}

const person = new Person('hello',18)
console.log(person)
console.log(person.name,person.age)
console.log(Person.gender)
person.sayHello()