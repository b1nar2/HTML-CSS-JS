import React, { Component } from 'react';

// 상태(state) 변수
class StateComponent extends Component {

    constructor(props) {
        super(props);
        // state는 하나의 컴포넌트 안에서 전역(광역) 변수처럼 사용될 수 있습니다.        
        this.state = {
            // App.js에서 전송한, props로 전달된 reactString 속성값("react")    
            // 을 저장하고 StateNumber에 "19.1" 이라는 값을 대입
            StateString : this.props.reactString,
            StateNumber : "19.1"
        }
    }

    render() {

        return(
            // this.state 키워드를 활용하여 state 변수에 접근 => 화면에 표시
            <div style={{backgroundColor:'lightgray'}}>
                {this.state.StateString} {this.state.StateNumber}
            </div>
        );
    } //

} //

export default StateComponent;