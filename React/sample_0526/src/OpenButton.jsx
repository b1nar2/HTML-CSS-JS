import React from "react";

function OpenButton() {

    const handleClick = (e) => {

        console.log("열기 버튼 클릭: ", e.target.id);

        const modal
            = document.getElementsByClassName("modal")[0];
        console.log("modal :" , modal);
        modal.style.visibility="visible";
    }

    // onClick={handleClick}
    // onClick={(e)=>handleClick(e)}

    return (
        <button id="open_btn"
        onClick={(e) => handleClick(e)}
        style={{width:'200px', heigth:'50px'}}>
        모달 열기
        </button>
    )

}

export default OpenButton;