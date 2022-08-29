import React, { Component } from 'react'

export default class Parent extends Component {
  render() {
    return (
      <div>
          <h1>Parent</h1>
          <A render={(name) => <B name={name}/>}/>
      </div>
    )
  }
}
class A extends Component {
    render() {
        const name = 'hello'
      return (
        <div>
            <h2>A</h2>
            {this.props.render(name)}
        </div>
      )
    }
  }
  class B extends Component {
    render() {
      return (
        <div>
            <h2>B,{this.props.name}</h2>
        </div>
      )
    }
  }
