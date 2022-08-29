"use strict";
//立即执行函数
(function () {
    class Person {
        constructor(name) {
            this.name = 'Person';
            this.age = 50;
            this.name = name;
            console.log('I am Person');
        }
        sayHello() {
            console.log('Person syaHello');
        }
    }
    class Son extends Person {
        constructor() {
            super('word');
            console.log('I am Son');
            console.log(`${this.name}`);
        }
        sayHello() {
            console.log('Son sayHello');
        }
    }
    const person = new Person('word');
    person.sayHello();
    const son = new Son();
    son.sayHello();
})();
