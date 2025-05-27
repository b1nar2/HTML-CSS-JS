import React, { Component } from 'react';

class PropsDefault extends Component {

    render() {

        let {
            ReactString,
            ReactNumber
        } = this.props;

        return (
            <div style={{backgroundColor:'cyan'}}>
                {ReactString} {ReactNumber}
            </div>
        );
    } //
}

// 각각의 변수에 기본값(default value)을 할당함
// defaultProps 속성
// https://ko.legacy.reactjs.org/docs/react-component.html#defaultprops
PropsDefault.defaultProps = {
    ReactString : "리액트",
    ReactNumber : 19.1
}

export default PropsDefault;