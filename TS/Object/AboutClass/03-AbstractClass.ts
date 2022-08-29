(function () {
    abstract class Animal {
        abstract sayHello(): void
    }
    class Dog extends Animal{
        name: string
        constructor(name: string) {
            super();
            this.name = name
        }

        sayHello() {
            console.log('Dog'+this.name)
        }
    }
    const animal = new Dog('dog')
    animal.sayHello()
})()