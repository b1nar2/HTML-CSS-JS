import React, { Component } from 'react';

class SetStateComponent extends Component {

    constructor(props) {
        super(props);
        this.state = {
            StateString : 'react'
        }
    }

    StateChange = (flag) => {

        if (flag == 'direct') this.state.StateString = '리액트';
        if (flag == 'setState') this.setState({StateString:'리액트'});
    } //

    render() {

        return (
            <div style={{backgroundColor:'pink'}}>

                {/* 이 버튼을 누르게 되면 StateString 값은 "리액트"로 변경될 수 있지만
                    render() 함수를 호출하지 않으므로 화면에 여전히 이전 값('react')가 표시됩니다. */}
                <button onClick={(e) => this.StateChange('direct', e)}>
                    state 직접(direct) 변경
                </button>&nbsp;
                
                {/* 이 버튼의 이벤트 처리의 경우는 StateString도 "리액트"로 변경되고 
                render()를 다시 호출하기 때문에 변경된 값이 적용됩니다. */}
                <button onClick={(e) => this.StateChange('setState', e)}>
                    setState로(setState) 변경
                </button>
                <br/>

                -- StateString : {this.state.StateString}
            </div>

        )
    }
}

export default SetStateComponent;