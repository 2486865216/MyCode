const proxy = require('http-proxy-middleware')
module.exports = function (app) {
    app.use(
        proxy.createProxyMiddleware('/api1', {//遇见/api1 就会触发该代理配置
            target: 'http://localhost:5000',//请求转发给谁
            changeOrigin: true,//控制服务器收到的响应头中Host的值
            /*  changeorigin设置为true时,服务器收到的请求头中的host为: localhost:5000
                changeorigin设置为 false时,服务器收到的请求头中的host为: localhost:3000
                changeorigin默认值为 false,但我们一般将 changeorigin值设为true
                pathRewrite:{"^/api1:"'}//去除请求前缀,保证交给后台服务器的是正常请求地址(必须配置) */
            pathRewrite: { '^/api1': '' }//重写请求路径
        }),
        proxy.createProxyMiddleware('/api2',{
            target: 'http://localhost:5001',
            changeOrigin: true,
            pathRewrite: { '^/api2':'' }
        })
    )
}