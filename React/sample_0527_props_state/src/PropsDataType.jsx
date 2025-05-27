import React, { Component } from 'react';
import PropTypes from 'prop-types';

// 자녀 컴포넌트에서 props에 대한 데이터 타입(자료형)을 선언해 놓으면,
// 일단 부모 컴포넌트에서 넘어오는 props 변수들의 자료형과 비교하게 됩니다.
// 이 때 자료형이 일치하지 않는다면 경고 메시지를 유발합니다.
class PropsDataType extends Component {

    render() {

        // 지역 변수를 선언하여 props 로 전달된 값들을 할당
        let { String,
              Number,
              Boolean, 
              Array,
              Json,
              Function } = this.props;

        return (
            // 부모 컴포넌트에서 전달한 props 변수들을 화면에 표시.
            <div style={{backgroundColor:'orange'}}>
                <p>StringProps : {String}</p>
                <p>NumberProps : {Number}</p>
                <p>BooleanProps : {Boolean.toString()}</p>
                <p>ArrayProps : {Array.toString()}</p>
                <p>JsonProps : {JSON.stringify(Json)}</p>
                <p>FunctionProps : {Function}</p>
            </div>
        );    

    } //

}

// props 값들을 각각에 적합한 자료형으로 선언. 
// 
// - propTypes
// https://ko.legacy.reactjs.org/docs/typechecking-with-proptypes.html#proptypes
// https://ko.legacy.reactjs.org/docs/typechecking-with-proptypes.html#gatsby-focus-wrapper

// 참고) React 컴포넌트 간에 코드를 공유하기 위해 함수 props를 이용
// https://ko.legacy.reactjs.org/docs/render-props.html#gatsby-focus-wrapper
PropsDataType.propTypes = {

    String : PropTypes.string,
    // String : PropTypes.number, 
    // 문자열(string)을 number로 표현하였기 때문에 console에서 경고 유발됨
    // 경고 메시지 : react-jsx-dev-runtime.development.js:87 
    // Warning: Failed prop type: Invalid prop `String` of type `string` 
    // supplied to `PropsDataType`, expected `number`.    
    Number : PropTypes.number,
    Boolean : PropTypes.bool,
    Array : PropTypes.array,
    Json : PropTypes.object,
    Function : PropTypes.func
}

export default PropsDataType;