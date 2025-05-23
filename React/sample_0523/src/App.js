import logo from './logo.svg';
import './App.css';

import CustomComponent from './CustomComponent';

import Modal from './Modal';
import Modal2 from './Modal2';

function App() {
  return (

    <div id ="div1">
      <h1> 리액트 개발</h1>
      <p> HTML 적용</p>

      <CustomComponent></CustomComponent>
      {/* <Modal></Modal> */}
      <Modal2/>
    </div>

  );
}

export default App;
