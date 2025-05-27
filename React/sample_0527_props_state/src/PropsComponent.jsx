import React, { Component } from 'react';

class PropsComponent extends Component {
    
    // getAttribute, setAttribute 유사
    // 속성 얻기(get), 설정(set)
    render() {

        let props_val = this.props.props_val;
        props_val +=  ' + 추가된 속성';

        return (
            <h2>{props_val}</h2>
        );
    } // render

} //

export default PropsComponent;