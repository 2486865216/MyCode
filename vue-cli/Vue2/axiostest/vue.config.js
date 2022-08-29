module.exports = {
    // // 开启代理服务器(方式一)
    // devServer: {
    //   proxy: 'http://localhost:5000'
    // },
    // 开启代理服务器(方式一)
    devServer: {
        proxy: {
          '/student': {
            target: 'http://localhost:5000',
            pathRewrite:{'^.student':''},
            ws: true,//支持webSocket
            changeOrigin: true//用于控制请求头中的host值
          },
        //   '/foo': {
        //     target: '<other_url>'
        //   }
        }
      }
  }