// import { useState } from 'react'
// import reactLogo from './assets/react.svg'
// import viteLogo from '/vite.svg'
// import './App.css'
import Modal from './Modal'
import OpenButton from './OpenButton'
// import CloseButton from './CloseButton'; //모달 닫기 버튼

function App() {
  // const [count, setCount] = useState(0)

  return (

    <div>
      <div>      
        <OpenButton></OpenButton>
        {/* <CloseButton></CloseButton> */}
      </div>

      <div>
         <Modal></Modal>
      </div>
    </div>
  );
}

export default App
