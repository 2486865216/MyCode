"use strict";
(function () {
    class Animal {
    }
    class Dog extends Animal {
        constructor(name) {
            super();
            this.name = name;
        }
        sayHello() {
            console.log('Dog' + this.name);
        }
    }
    const animal = new Dog('dog');
    animal.sayHello();
})();
