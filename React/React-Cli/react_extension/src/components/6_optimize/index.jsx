import React, { PureComponent } from 'react'

export default class Parent extends PureComponent {
    state = { carName: '奔驰' }
    changeCar = () => {
        this.setState({ carName: '宝马' })
    }
    // shouldComponentUpdate(nextProps, nextState) {
    //     if (this.state.carName === nextState.carName) {
    //         return false
    //     }
    //     else {
    //         return true
    //     }
    // }
    render() {
        console.log('parent');
        const { carName } = this.state
        return (
            <div>
                <h1>Parent</h1>
                <h2>车名为:{carName}</h2>
                <button onClick={this.changeCar}>点我换车</button>
                <Child />
            </div>
        )
    }
}
class Child extends PureComponent {
    render() {
        return (
            <div>
                <h1>Child</h1>
            </div>
        )
    }
}
