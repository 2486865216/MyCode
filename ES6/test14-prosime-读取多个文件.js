const fs = require("fs");

const p = new Promise((resolve, reject) => {
	fs.readFile("test14-promise.md",(err,data) => {
		resolve(data);
	})
})
p.then(value => {
	return new Promise((resolve, reject) => {
		fs.readFile('test14-promise-1.md',(err,data) => {
			resolve([value,data]);
		})
	})
}).then(value => {
	return new Promise((resolve, reject) => {
		fs.readFile('test14-promise-2.md',(err,data) => {
			value.push(data);
			resolve(value);
		})
	})
}).then(value => {
	console.log(value.join('\e\n'))
})
