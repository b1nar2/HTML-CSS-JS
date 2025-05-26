import React, { Component } from 'react';
import "./custom.css";

class CustomComponent extends Component {

    // 컴포넌트 생성주기(life cycle)
    // 컴포넌트의 생성/변경/소멸 과정
    // https://ko.legacy.reactjs.org/docs/react-component.html#the-component-lifecycle

    // 생명주기(life cycle) 함수 : constructor 생성자 (생성(mounting) 단계)
    // 생명주기 함수들 중에서 가장 먼저 호출되며 처음 한번만 호출됨. 
    // 컴포넌트 내부에서 사용되는 변수들(state 등)을 부모 객체에서 전달받은(상속된) 변수 
    // (props)를 초기화할 때 사용. super()(부모 생성자) 함수를 사용하게 된다면 가장 먼저 언급해야 됨.
    // https://ko.legacy.reactjs.org/docs/react-component.html#render

    constructor(props) {
        super(props);
        this.state = {};
        console.log('생성자 호출');
    } //

    // static 키워드 : https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Classes/static
    // 생명주기(life cycle) 함수 : getDerivedStateFromProps (생성/변경 단계)
    // constructor(생성자) 다음으로 실행.
    // 컴포넌트가 새로운 props(속성)을 받게 되었을 때 state(상태 변수)를 변경해줌.
    // App.js에 전달한 props_value 라는 변수를 props.props_value로 접근하여 값을 가져올 수 있음.
    // https://ko.legacy.reactjs.org/docs/react-component.html#static-getderivedstatefromprops

    static getDerivedStateFromProps(props, state) {

        console.log('getDerivedStateFromProps 호출 : ', props.props_value);
        return {
            temp_state:props.props_value
        };
    } //

    //  : 생명주기(life cycle) 함수 : componentDidMount (생성 단계)
    // 작성된 생명주기 함수들 중 가장 마지막 단계에 실행
    // render 함수가 return 되는 html 형식의 코드를 화면에 표현한 후 실행됨.
    // 화면에 모두 출력된 후 실행되어야 하는 이벤트 핸들링(처리), 초기화 등의 역할을 하는,
    // 가장 많이 활용되는 함수.
    // https://ko.legacy.reactjs.org/docs/react-component.html#componentdidmount
    
    componentDidMount() {
        console.log("componentDidMount 호출");
        console.log("temp_state : ", this.state.temp_state);
        this.setState({temp_state2 : true}); // 임시 상태 변수 추가
        // 변수의 선언과 초기화를 동시에 실행
    }

    //  : 생명주기(life cycle) 함수 : shouldComponentUpdate (변경 단계)
    // 생명주기에서의 "변경" => props(속성) 및 state(상태)의 변경
    // https://ko.legacy.reactjs.org/docs/react-component.html#shouldcomponentupdate

    shouldComponentUpdate(props, state) {
        console.log("shouldComponentUpdate 호출");
        console.log("temp_state2 : ", state.temp_state2);
        return state.temp_state2;
    } //

    //  : 생명주기(life cycle) 함수 : render (생성/변경 단계)
    // return 되는 html 형식의 코드를 웹 브라우저 화면에 표현(클라이언트 렌더링) 해주는 
    // 함수. 화면 내용이 변경되면 그 시점에서 자동 호출됨.
    // https://ko.legacy.reactjs.org/docs/react-component.html#render
    
    render() {

        // 
        console.log('render() 메서드 호출');    

        return (
            <div id="custom_div">
                사용자 작성 컴포넌트
            </div>
        )
    } //
}

export default CustomComponent;