import React from 'react'
// import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css'

import ContextApi from './ContextApiComponent'

function App() {

    //  컨택스트(Context)
    // : https://ko.legacy.reactjs.org/docs/context.html
    
    return (
      <div id="div1" style={{height : 200, width : 600, backgroundColor : 'cyan'}}>
        
        <h2 className="d-flex justify-content-center py-4">
          리액트(react) Context(컨택스트)
        </h2>
        
        <ContextApi />

      </div>
    );

}

export default App;