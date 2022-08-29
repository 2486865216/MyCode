"use strict";
class Person {
    constructor(name, age) {
        //readonly只读
        this.test = 'test';
        this.name = name;
        this.age = age;
    }
    sayHello() {
        console.log('sayHello');
    }
}
Person.gender = 'word';
const person = new Person('hello', 18);
console.log(person);
console.log(person.name, person.age);
console.log(Person.gender);
person.sayHello();
