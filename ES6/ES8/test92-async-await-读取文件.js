const fs = require('fs')


function test(){
	return new Promise((resolve,reject) => {
		fs.readFile("../test14-promise.md",(err,data)=>{
			if (err) reject(err)
			resolve(data)
		})
	})
}
function test1(){
	return new Promise((resolve,reject) => {
		fs.readFile("../test14-promise-1.md",(err,data)=>{
			if (err) reject(err)
			resolve(data)
		})
	})
}
function test2(){
	return new Promise((resolve,reject) => {
		fs.readFile("../test14-promise-2.md",(err,data)=>{
			if (err) reject(err)
			resolve(data)
		})
	})
}

async function main(){
	let t = await test();
	let t2 = await test1();
	let t3 = await test2();

	console.log(t.toString())
	console.log(t2.toString())
	console.log(t3.toString())
}
main()