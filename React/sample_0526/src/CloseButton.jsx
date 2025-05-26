import React from "react";

function CloseButton() {

    const handleClick = (e) => {

        console.log("닫기 버튼 클릭: ", e.target.id)

        const modal
            = document.getElementsByClassName("modal")[0];
        console.log("modal :" , modal);
        modal.style.visibility="hidden";
    }

    return(
        <button id="close_btn"
        onClick={(e) => handleClick(e)}
        style={{width:'200px', heigth:'50px'}}>
        모달 닫기
        </button>

    )


}

export default CloseButton;