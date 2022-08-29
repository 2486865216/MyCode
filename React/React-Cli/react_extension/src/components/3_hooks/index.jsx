// import React from 'react'

// export default function Hook() {
//   const [count, setCount] = React.useState(0)  
//   const [name, setName] = React.useState('tom')  
//   function add(){
//       setCount(count+1)
//   }
//   function changeName(){
//     setName( name => name='jack')
//   }
//   return (
//     <div>
//         <h1>当前求和为:{count}</h1>
//         <h1>当前名字为:{name}</h1>
//         <button onClick={add}>点我加一</button>
//         <button onClick={changeName}>点我改名</button>
//     </div> 
//   )
// }
// import React from 'react'
// import ReactDOM from 'react-dom'

// export default function Hooks() {
//     const [count, setCount] = React.useState(0)
//     /*  componentDidMount()
//         componentDidUpdate()
//         componentWillUnmount() */
//     React.useEffect(() => {
//         console.log('kkk');
//         return () => {
//             console.log('lll');
//         }
//     },[count])/* 不写：谁都检测,空数组：谁都不检测，具体值：检测具体值 */
//     function add(){
//         setCount( count => count + 1)
//     }
//     function unmounted(){
//         ReactDOM.unmountComponentAtNode(document.getElementById('root'))
//     }
//   return (
//     <div>
//         <h1>当前求和为:{count}</h1>
//         <button onClick={add}>点我加一</button>
//         <button onClick={unmounted}>卸载组件</button>
//     </div>
//   )
// }

import React from 'react'

export default function Hooks() {
    const myref = React.useRef()
    function show(){
        alert(myref.current.value)
    }
  return (
    <div>
        <input ref = {myref} type="text" placeholder='点击按钮提示数据'/>
        <button onClick={show}>点击按钮提示数据</button>
    </div>
  )
}
