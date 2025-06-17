import { useState } from 'react'

import { BrowserRouter, Routes, Route } from 'react-router-dom'

import Menu from './routes/Menu'
import Home from './routes/Home'
import SubPage1 from './routes/SubPage1'
import SubPage2 from './routes/SubPage2'


export default function App() {

  return (

    <>
      <div>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Menu />}>
              <Route path="" element={<Home />}></Route>
              <Route path="/SubPage1" element={<SubPage1 />}></Route>
              <Route path="/SubPage2" element={<SubPage2 />}></Route>            
            </Route>
          </Routes>        
        </BrowserRouter>
      </div>

    </>
  )

}
