(function(){
    interface Animal {
        name: string
        age: number
        syaHello(): void
    }
    class Dog implements Animal{
        name: string  = 'dog';
        age: number = 18;

        syaHello(): void {
            console.log(this.name)
        }
    }
})()