// import React, {Component} from 'react';

import './css/Modal.css';

import { MdOutlineWebAsset } from "react-icons/md";
import { MdClose } from "react-icons/md";


//class Modal extends Component {

function Modal2() {
    // render() {

        return (

            <div className="modal">
                
                <div className="modal_header">
                
                    <div className="modal_header_icon">
                        <MdOutlineWebAsset style={{fontSize:"25px"}} />      
                    </div>

                    <div className="modal_header_title">
                        제목123
                    </div>
                    
                    <div className="modal_header_close">

                        <a href="#" id="modal_close_btn">
                            <MdClose style={{fontSize:"25px"}} />
                        </a>
                    </div>

                </div>
                    
                <div className="modal_body">
                            글 내용입니다.
                </div>
                
            </div>
        )

    //}
 }

 export default Modal2;