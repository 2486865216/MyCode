const fs = require('fs')
//普通方法读取
// fs.readFile("test14-promise.md",(err,data)=>{
// 	if (err) throw err;
// 	console.log(data.toString())
// })
//使用promise封装
const p = new Promise(function(resolve, reject){
	fs.readFile("test14-promise.md",(err,data)=>{
		if (err) reject(err);
		resolve(data);
	})
})
p.then(function (value){
	console.log(value.toString())
},function (reason){
	console.log(reason)
})