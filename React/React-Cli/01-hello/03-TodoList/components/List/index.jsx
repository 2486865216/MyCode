import React, { Component } from 'react';
import PropTypes from 'prop-types'
import Item from '../Item'
import './index.css'

export default class index extends Component {
  //限制props
  static propTypes = {
    todos: PropTypes.array.isRequired,
    updateTodo: PropTypes.func.isRequired
  }
  render() {
    const { todos, updateTodo,deleteTodo } = this.props
    return (
      <div>
        <ul className="ul">
          {
            todos.map((todo) => {
              return <Item key={todo.id} {...todo} updateTodo={updateTodo} deleteTodo={deleteTodo}/>
            })
          }
        </ul>
      </div>
    );
  }
}
