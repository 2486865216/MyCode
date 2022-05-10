// 引入一个包
const path = require('path')
const HTMLWebpackPlugin = require('html-webpack-plugin')
const {CleanWebpackPlugin} = require('clean-webpack-plugin')

//webpack所有的配置信息都应该写在module.exports中
module.exports = {
    mode: 'production',
    //指定入口文件
    entry: "./src/index.ts",
    //指定打包文件所在目录
    output: {
        //指定打包文件目录
        path: path.resolve(__dirname, 'dist'),
        //指定打包文件的文件
        filename: "bundle.js",
        //不使用箭头函数
        environment: {
            arrowFunction: false,
            const:false
        }
    },
    //指定打包时要用的模块
    module: {
        //指定加载的规则
        rules: [
            {
                //test指定的是规则生效的文件
                test: /\.ts$/,
                //要使用的loader
                use: [
                    //配置babel
                    {
                        //指定加载器
                        loader: 'babel-loader',
                        //设置babel
                        options: {
                            //设置预定义的的环境
                            presets: [
                                [
                                    //指定环境的插件
                                    "@babel/preset-env",
                                    //配置信息
                                    {
                                        //要兼容的浏览器
                                        targets: {
                                            "chrome": "88",
                                            "ie": "11"
                                        },
                                        //指定core-js版本
                                        "corejs": "3",
                                        //core-js的使用方式"usage"表示按需加载
                                        "useBuiltIns": "usage"
                                    }
                                ]
                            ]
                        }
                    },
                    'ts-loader',
                ],
                //要排除的文件
                exclude: /node-modules/
            },
            //设置less文件的处理
            {
                test: /\.less$/,
                use: [
                    //loader从下往上执行
                    "style-loader",
                    "css-loader",
                    // 引入postcss
                    {
                        loader: "postcss-loader",
                        options: {
                            postcssOptions: {
                                plugins: [
                                    [
                                        "postcss-preset-env",
                                        {
                                            browsers: 'last 2 versions'
                                        }
                                    ]
                                ]
                            }
                        }
                    },
                    "less-loader"
                ]
            }
        ]
    },
    //配置webpack插件
    plugins: [
        new CleanWebpackPlugin(),
        new HTMLWebpackPlugin({
            // title: '自定义的title'
            template: './src/index.html'
        }),
    ],
    //设置哪些文件可以作为引用模块
    resolve: {
        extensions: ['.js', '.ts']
    }
}