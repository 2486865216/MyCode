import React, { Component } from 'react'
import {Link,Outlet} from 'react-router-dom'

export default class Home extends Component {
  render() {
    return (
      <div>
        <h1>Home</h1>
        <Link to='/home/news'>News</Link>
        <Link to='/home/message'>Message</Link>
        <div>
          <Outlet/>
        </div>
      </div>
    )
  }
}
