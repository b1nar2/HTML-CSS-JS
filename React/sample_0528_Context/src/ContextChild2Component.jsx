import React, { Component } from 'react';
import { Consumer } from './ContextApiComponent';

import 'bootstrap/dist/css/bootstrap.min.css'

class ContextChild2Component extends Component {

    render() {
        return (
            <Consumer>
               { contextVal => 
                        <h4 className="d-flex justify-content-center py-4"> 
                            
                            컨택스트(Context) 전달값(value) : &nbsp;
                            <span  style={{color :'red'}}>{contextVal}</span>

                        </h4>
                }
            </Consumer>
        )
    }

} //

export default ContextChild2Component;