"use strict";
(function () {
    class Person {
        constructor(name, age) {
            this._name = name;
            this._age = age;
        }
        get name() {
            return this._name;
        }
        set name(value) {
            this._name = value;
        }
        get age() {
            return this._age;
        }
        set age(value) {
            if (value > 0) {
                this._age = value;
            }
        }
    }
    const person = new Person('hello', 18);
    console.log(person.name, person.age);
    person.name = 'word';
    person.age = -1;
    console.log(person.name, person.age);
})();
