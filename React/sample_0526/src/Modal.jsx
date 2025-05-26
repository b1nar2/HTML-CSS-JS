import React, { Component } from 'react';

import './css/popup_style.css';
import { MdOutlineWebAsset } from "react-icons/md";
import { MdClose } from "react-icons/md";

class Modal extends Component {

    closePopup = (e, clazz) => {

        console.log("창닫기 :", e.currentTarget);
        console.log("창닫기-2 :", e.target);
        console.log("clazz :", clazz);    

        let modal = document.querySelector("." + clazz);
        modal.style.visibility="hidden";

    } //

    render() {

        return(
            <div className="modal">
                
                <div className="modal_header">
                
                    <div className="modal_header_icon">
                        <MdOutlineWebAsset style={{fontSize:'25px'}} />        
                    </div>
                    
                    <div className="modal_header_title">
                        제목
                    </div>
                    
                    <div className="modal_header_close">

                        <a href="#" id="modal_close_btn"
                            onClick={(e) => this.closePopup(e, "modal")}>
                            <MdClose style={{fontSize:'25px'}} />
                        </a>
                    </div>

                </div>
                
                <div className="modal_body">
                    글 내용입니다.
                </div>
                
            </div>
        )
    }
}

export default Modal;