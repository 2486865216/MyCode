(function(){
    class Person{
        private _name: string
        private _age: number

        constructor(name: string, age: number) {
            this._name = name;
            this._age = age;
        }

        get name(): string {
            return this._name;
        }

        set name(value: string) {
            this._name = value;
        }

        get age(): number {
            return this._age;
        }

        set age(value: number) {
            if (value > 0){
                this._age = value;
            }
        }
    }
    const person = new Person('hello',18)
    console.log(person.name,person.age)
    person.name = 'word'
    person.age = -1
    console.log(person.name,person.age)
})()