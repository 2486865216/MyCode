const express = require('express')
const app = express()

app.use((request, response, next) => {
    console.log('有人请求服务器1');
    console.log(request.url);
    next()
})

app.get('/students',(request, response) => {
    const student = [
        {id:'001',name:'Hello'},
        {id:'002',name:'React'},
        {id:'003',name:'Vue'},
    ]
    response.send(student)
})

app.listen(5000,(err) => {
    if(!err){
        console.log('服务器一启动成功,请求地址为http://localhost:5000/students');
    }
})