import React, { useState, useEffect } from 'react'

// 함수형 컴포넌트
// 함수형 컴포넌트에서 클래스 컴포넌트를 사용하지 않아도 
// 클래스형 컴포넌트와 같이 state와 생명주기(life cycle) 함수와
// 같은 기능을 사용하기 위해 Hook을 사용합니다.
// React 16.8 에서 추가됨.

// 대표적인 Hook 함수에는 useState(), userEffect가 있습니다.
function HookSample(props) {

    // useState 함수 : 
    // https://ko.legacy.reactjs.org/docs/hooks-state.html
    // 상태 유지 값(state)과 그 값을 갱신하는 함수(setState)를 반환
    // setSate 함수 : 새로운 state 값을 받아서 컴포넌트 리렌더링(re-rendering)을 큐(Queue)에 등록

    // 참고) 함수적 갱신 : 
    // https://ko.legacy.reactjs.org/docs/hooks-reference.html#functional-updates
    const [contents, setContents] = useState('[We use the React !]');
    const [count, setCount] = useState(0);

    // useEffect 함수 : 
    //  https://ko.legacy.reactjs.org/docs/hooks-effect.html
    useEffect(() => {
        // 웹 브라우저 API를 이용하여 <h2 id="title"> 내용을 갱신
        console.log("useEffect");        
        let titleCom = document.getElementById("title");
        titleCom.innerHTML = `여러분은 ${count} 번 클릭하셨습니다.`;
    });

    return (
        <div style={{padding: 0}}>

            <h2>{contents}</h2>
            <h2 id="title">{count}</h2>

            <button onClick={() => {
                    setContents('[우리는 리액트 Hook을 쓰고 있습니다.]');
                    setCount(count + 1);
                 }}>
                버튼(Hook)
            </button>
        </div>      
    );

} //

export default HookSample;