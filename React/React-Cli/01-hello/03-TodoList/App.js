import React from 'react';
import './App.css'
import Header from './components/Header'
import List from './components/List'
import Footer from './components/Footer'

export default class App extends React.Component{
    state = {
        todos:[
            {id:'001', name:'吃饭', done:true},
            {id:'002', name:'睡觉', done:true},
            {id:'003', name:'打游戏', done:false},
        ]
    }
    //添加一个todo，接收的参数是一个todo对象
    addTodo = (todoOBj) => {
        const {todos} = this.state
        const newTodo = [todoOBj,...todos];
        this.setState({todos:newTodo})
    }
    //修改状态
    updateTodo = (id,done) => {
        const {todos} = this.state
        const newTodo = todos.map((todo) => {
            if (todo.id === id) return {...todo,done}
            else return todo
        })
        this.setState({todos:newTodo})
    }
    //删除
    deleteTodo(id){
        const {todos} = this.state
        const newTodos = todos.filter((todo) => {
            return todo.id !== id
        })
        this.setState({todos:newTodos})
    }
    //全选
    checkAllTodo = (done) => {
        const {todos} = this.state
        const newTodo = todos.map((todo) => {
            return {...todo,done}
        })
        this.setState({todos: newTodo})
    }
    //清除已完成
    clearAllDone = () => {
        const {todos} = this.state
        const newTodo = todos.filter((todo) => {
            return !todo.done
        })
        this.setState({todos: newTodo})
    }
    render(){
        const {todos} = this.state
        return(
            <div id="app">
                <Header addTodo={this.addTodo}/>
                <List todos={todos} updateTodo={this.updateTodo} deleteTodo={this.deleteTodo.bind(this)}/>
                <Footer checkAll={this.checkAllTodo} clearAllDone = {this.clearAllDone} todos={todos}/>
            </div>
        )
    }
}