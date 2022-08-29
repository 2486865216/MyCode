import React, { Component } from 'react'
// import SetStateTest from './components/1_setStateTest'

// import Main from './components/2_LazyLoad/Main'
// import './App.css'
// import { BrowserRouter } from 'react-router-dom'

// import Hooks from './components/3_hooks'

// import Fragment from './components/4_Fragment'

// import Context from './components/5_Context'

// import Optimize from './components/6_optimize'

// import RenderProps from './components/7_Render_Props'

import Demo from './components/8_ErrorBounary/Parent'

export default class App extends Component {
  render() {
    return (
      <div id='app'>
        {/* <SetStateTest/> */}

        {/* <BrowserRouter>
          <Main />
        </BrowserRouter> */}

        {/* <Hooks/> */}

        {/* <Fragment/> */}

        {/* <Context/> */}

        {/* <Optimize/> */}

        {/* <RenderProps/> */}

        <Demo/>
      </div>
    )
  }
}
