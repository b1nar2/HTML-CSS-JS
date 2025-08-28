import React, {useState} from "react";
import './style.css'


function Board() {

    const [title, setTitle] = useState(['첫번째', '두번째', '세번째']);
    const [goods, setGoods] = useState(0);
    
    // function 제목변경(){
    //   setTitle(['일번', '이번', '삼번'])
    // }

    function 제목변경(){
      let newArray = [...title];
      newArray[2] = '선택';
      setTitle (newArray);
     }

    return (
        <div className="board">

          <div className="namenav">
            <div><h2>게시판</h2></div>
          </div>

            <div className="titlechange">
            <button onClick={제목변경}>선택제목변경</button>
            </div>
            <div className="list">
              <h3>{title[0]} <span onClick={()=>{setGoods(goods+1)}}>🎁</span> {goods} </h3>
              <p>8월 28일</p>
            </div>

            <div>
              <h3>{title[1]}</h3>
              <p>8월 29일</p>
            </div>

            <div>
              <h3>{title[2]}</h3>
              <p>8월 30일</p>
            </div>

        </div>



    )

}



export default Board