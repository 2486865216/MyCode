import React, { Component } from 'react'

export default class Child extends Component {
    state = {
        // user:[
        //     {id:'001',name:'Hello'},
        //     {id:'002',name:'React'},
        // ]
        user:'error'
    }
  render() {
    return (
      <div>
          <ul>
            {
                this.state.user.map((user1) => {
                    return (
                        <li key={user1.id}>
                            user1.name
                        </li>
                    )
                })
            }
          </ul>
      </div>
    )
  }
}
