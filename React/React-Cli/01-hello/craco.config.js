const CracoLessPlugin = require('craco-less');

module.exports = {
  plugins: [
    {
      plugin: CracoLessPlugin,
      options: {
        lessLoaderOptions: {
          lessOptions: {
            modifyVars: { '@primary-color': '#1DA57A' },
            javascriptEnabled: true,
          },
        },
      },
    },
  ],
  babel:{
    plugins: [
        // ["@babel/plugin-proposal-decorators", { legacy: true }],  //装饰器
        [   
          "import", 
          {
            "libraryName": "antd",
            "libraryDirectory": "lib",
             "style": true 
           }
       ]
      ]
  }
};