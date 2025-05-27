// import { useState } from 'react'
// import reactLogo from './assets/react.svg'
// import viteLogo from '/vite.svg'
// import './App.css'

import HookSample from "./HookSample"

function App() {
  // const [count, setCount] = useState(0)

  return (
    <>
     <div id="div1">
      <h1>리액트 개발</h1>
      <p>HTML 적용</p>
      
       {/* - React Hook 
       : https://ko.legacy.reactjs.org/docs/hooks-intro.html
       : https://ko.legacy.reactjs.org/docs/hooks-overview.html */}

      <HookSample />
    </div>
    </>
  )
}

export default App
