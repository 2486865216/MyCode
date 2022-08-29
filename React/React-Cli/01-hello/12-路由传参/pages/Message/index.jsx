import React, { Component } from 'react'
import {Outlet,Link} from 'react-router-dom'

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
                      {/* 向路由组件传递params参数 */}
                      {/* <Link to={`/home/message/detail/${msgObj.id}/${msgObj.title}`}>{msgObj.title}</Link> */}
                      {/* 传递search参数 */}
                      <Link replace to={`/home/message/detail?id=${msgObj.id}&title=${msgObj.title}`}>{msgObj.title}</Link>
                      {/* 传递state参数 ，暂时传递不了参数，原因未知*/}
                      {/* <NavLink to={{pathname:'/home/message/detail', state:{id:'mdklanslk'}}}>{msgObj.title}</NavLink> */}
                      {/* <Link to={`/home/message/detail`} state={{id:'dnkasndkjadnj'}}>{msgObj.title}</Link> */}
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
