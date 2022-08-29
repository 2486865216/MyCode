import React, { Component } from 'react'
import {Link,Outlet} from 'react-router-dom'

export default class Message extends Component {
  state = {
    message:[
      {id:'001',title:'message1'},
      {id:'002',title:'message2'},
      {id:'003',title:'message3'},
    ]
  }
  render() {
    return (
      <div>
          <ul>
              {
                this.state.message.map((msgObj) => {
                  return (
                    <li key={msgObj.id}>
                      <Link to={`/home/message/detail`}>{msgObj.title}</Link>
                    </li>
                  )
                })
              }
          </ul>
          <div>
            <Outlet/>
          </div>
      </div>
    )
  }
}
