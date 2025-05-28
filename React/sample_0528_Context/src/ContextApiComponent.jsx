import React, { Component }  from 'react';
import Child from './ContextChildComponent'

// createContext()
// : https://ko.legacy.reactjs.org/docs/context.html#reactcreatecontext

const { Provider, Consumer } = React.createContext();
export { Consumer }

class ContextApiComponent extends Component {

    render() {
        return (
            <Provider value="리액트">
                <Child />
            </Provider>    
        );
    }

} //

export default ContextApiComponent;