function test(a: number): any{

}

function test01<T>(a: T): T{
    return a
}
let number = test01(1);

let hello = test01<string>('hello');

interface Inter{
    length: number
}
function test02<T extends Inter>(a: T): number{
    return a.length
}
test02({length: 10})