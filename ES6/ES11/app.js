
const button = document.getElementById('button')

button.onclick = function (){
	import('./hello.js').then(module=>{
		console.log(module)
	})
}