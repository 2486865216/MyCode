import React,{Component} from 'react'
import axios from 'axios'

export default class Demo extends Component{
    getStudentData = () => {
        //"proxy":"http://localhost:5000" 在package.json里添加，解决跨域
        axios.get('http://localhost:3000/api1/students').then((response) => {
            console.log(response.data);
        },
        (error) => {
            console.log(error.message);
        }
        )
    }
    getCarData = () => {
        axios.get('http://localhost:3000/api2/cars').then((response) => {
            console.log(response.data);
        },
        (error) => {
            console.log(error.message);
        }
        )
    }
    render(){
        return(
            <div>
                <button onClick={this.getStudentData}>点击获取学生信息</button>
                <button onClick={this.getCarData}>点击获取汽车信息</button>
            </div>
        )
    }
}