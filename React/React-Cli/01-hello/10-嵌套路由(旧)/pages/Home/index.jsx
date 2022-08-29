import React, { Component } from 'react'
import {Link,Routes,Route} from 'react-router-dom'
import News from '../News'
import Message from '../Message'

export default class Home extends Component {
  render() {
    return (
      <div>
        <h1>Home</h1>
        <Link to='/home/news'>News</Link>
        <Link to='/home/message'>Message</Link>
        <Routes>
          <Route path='/news' element={<News/>}/>
          <Route path='/message' element={<Message/>}/>
        </Routes>
      </div>
    )
  }
}
