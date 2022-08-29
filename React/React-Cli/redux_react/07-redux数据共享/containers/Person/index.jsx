import React, { Component } from 'react'
import {nanoid} from 'nanoid'
import {connect} from 'react-redux'
import {createAddPerson} from '../../redux/actions/person'

class Person extends Component {
    addPerson = () => {
        const name = this.name.value
        const age = this.age.value
        const personObj = {id:nanoid(),name,age}
        this.props.add(personObj)
        this.name.value = ''
        this.age.value = ''
    }
  render() {
    return (
      <div>
          <h1>Person组件,上方组件求和为:{this.props.count}</h1>
          <input ref = { c => this.name = c} type="text" placeholder='输入名字'/>
          <input ref = { c => this.age = c} type="text" placeholder='输入年龄'/>
          <button onClick={this.addPerson}>添加</button>
          <ul>
              {
                  this.props.persons.map((person) => {
                      return (
                          <li key={person.id}>{person.name}--{person.age}</li>
                      )
                  })
              }
          </ul>
      </div>
    )
  }
}

export default connect(
    state => ({persons: state.person,count:state.count}),
    {
        add:createAddPerson
    }
)(Person)