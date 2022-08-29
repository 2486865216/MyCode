const express = require('express')
const app = express()

app.use((request, response, next) => {
    console.log('有人请求服务器2');
    next()
})

app.get('/cars',(request, response) => {
    const cars = [
        {id:'001',name:'奔驰'},
        {id:'002',name:'宝马'},
        {id:'003',name:'马自达'},
    ]
    response.send(cars)
})

app.listen(5001,(err) => {
    if(!err){
        console.log('服务器二启动成功,请求地址为http://localhost:5001/cars');
    }
})