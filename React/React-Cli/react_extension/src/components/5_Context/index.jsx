import React, { Component } from 'react'

const MyContext = React.createContext()
const {Provider,Consumer} = MyContext
export default class A extends Component {
    render() {
        const userName = 'hello word'
        const age = 18
        return (
            <div>
                A
                <Provider value={{userName,age}}>
                    <B />
                </Provider>
            </div>
        )
    }
}
class B extends Component {
    render() {
        return (
            <div>
                B
                <C />
                <D />
            </div>
        )
    }
}
class C extends Component {
    //声明接受
    static contextType = MyContext
    render() {
        return (
            <div>
                C
                {this.context.userName}
                {this.context.age}
            </div>
        )
    }
}
function D (){
    return(
        <div>
            <Consumer>
                {
                    value => `${value.userName},${value.age}`
                }
            </Consumer>
        </div>
    )
}