import { useState } from 'react';

import { Label, Input, Button, Alert, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap'
import { MdOutlinePerson } from "react-icons/md";
import { MdOutlineMailOutline } from "react-icons/md";
import { MdOutlinePhoneIphone } from "react-icons/md";
import { MdOutlineDescription } from "react-icons/md";

import 'bootstrap/dist/css/bootstrap.min.css';

import './css/style.css'

function BootstrapForm() {

    // 폼점검을 위한 상태 정보 초기화
    const [nameCheck, setNameCheck] = useState(false); // 이름 필드 상태 점검 기본값 false
    const [nameError, setNameError] = useState(""); // 이름 필드 에러 메시지

    const [emailCheck, setEmailCheck] = useState(false); // 이메일 상태 점검 기본값 false
    const [emailError, setEmailError] = useState(""); // 이메일 필드 에러 메시지

    const [phoneCheck, setPhoneCheck] = useState(false); // 연락처 상태 점검 기본값 false
    const [phoneError, setPhoneError] = useState(""); // 연락처 필드 에러 메시지
    
    const [descriptionCheck, setDescriptionCheck] = useState(false); // 상담내용 필드 상태 점검 기본값 false
    const [descriptionError, setDescriptionError] = useState(""); // 상담내용 필드 에러 메시지

    const [modal, setModal] = useState(false); // Modal 상태
    const toggle = () => setModal(!modal); // Modal 토글(toggle)

    const [saveMsg, setSaveMsg] = useState(""); // 회원정보 저장 메시지

    // 이름 폼 점검
    const formNameCheck = (e) => {

        let nameFld = document.getElementById("name");
        let nameRegex = /^[가-힣]{2,100}$/; // 한글 2 ~ 100자

         if (nameRegex.test(nameFld.value) == true) {
             console.log("적합한 이름");
             setNameCheck(true);
             setNameError("");
 
         } else {
             console.log("적합한 이름이 아님");
             setNameCheck(false);
             setNameError("이름은 한글로 작성하십시오.");
         } 
    }// 이름 폼 점검 끝


    // 이메일 폼 점검
    const formEmailCheck = (e) => {
            let emailFld = document.getElementById("email");
            let emailRegex = /^[a-zA-Z0-9_+.-]+@([a-zA-Z0-9-]+\.)+[a-zA-Z0-9]{2,4}$/;

            if (emailRegex.test(emailFld.value) == true) {
                console.log("적합한 이메일");
                setEmailCheck(true);
                setEmailError("");
            } else {
                console.log("적합한 이메일이 아님");
                setEmailCheck(false);
                setEmailError("올바른 이메일 형식이 아닙니다.");
            } 
    } //이메일 폼 점검 끝

    // 연락처 폼 점검
    const formPhoneCheck = (e) => {
            let phoneFld = document.getElementById("phone");
            let phoneRegex = /^010\d{3,4}\d{4}$/; //  ex) 01012345678

        if (phoneRegex.test(phoneFld.value) == true) {
            console.log("적합한 연락처");
            setPhoneCheck(true);
            setPhoneError("");
        } else {
            console.log("적합한 연락처가 아님");
            setPhoneCheck(false);
            setPhoneError("올바른 연락처 형식이 아닙니다.");
        } 
    }// 연락처 폼 점검 끝

    // 상담내용 폼 점검
    const formDescriptionCheck = (e) => {
        let descriptionFld = document.getElementById("description");
        let descriptionRegex = /^[0-9a-zA-Z가-힣ㄱ-ㅎ\W]{2,}$/; // 최소 2글자 이상
        console.log("description.value :" , description.value);

        if (descriptionRegex.test(descriptionFld.value) == true) {
            console.log("적합한 상담내용");
            setDescriptionCheck(true);
            setDescriptionError("");
        } else {
            console.log("적합한 상담내용이 아님");
            setDescriptionCheck(false);
            setDescriptionError("상담내용은 최소 2자 이상을 기록하십시오.");
        }
    }// 상담내용 폼 점검 끝


    

    // 폼 초기화
    const formatInit = (e) => {

        console.log("폼 초기화");
        console.log("e target : ", e.target);

        // 입력된 폼의 내용들을 등록
        document.getElementById("name").value = "";        
        document.getElementById("email").value = "";
        document.getElementById("phone").value = "";
        document.getElementById("description").value = "";
    } //


    // AJAX(Promise/fetch) 코드
    const formCheck = async(e) => {
        
        console.log("e target : ", e.target);

    }

    ///////////////////////////////////////////////////////////

    const formSubmit = async(e) => {

        console.log("----------------------------------------------")
 
        console.log("nameCheck : ", nameCheck);
        console.log("emailCheck : ", emailCheck);
        console.log("phoneCheck : ", phoneCheck);
        console.log("descriptionCheck : ", descriptionCheck);
 
        ///////////////////////////////////////////////////////////

        if ((nameCheck == true) && (emailCheck == true) && 
            (phoneCheck == true) && (descriptionCheck == true))
        {
            console.log("폼점검 완료. 전송 준비");

             // 입력된 폼의 내용들을 등록
            let name = document.getElementById("name").value;        
            let email = document.getElementById("email").value;
            let phone = document.getElementById("phone").value;
            let description = document.getElementById("description").value;

            console.log("name : ", name);
            console.log("email : ", email);
            console.log("phone : ", phone);
            console.log("description : ", description);

            // 회원정보 전송 부분

            
            // 회원정보 전송 부분

            const response     
                = await fetch(`http://localhost:3000/actionReactForm/${name}/${email}/${phone}/${description}`, 
            { 
                method : "GET",
                mode : "cors",
                cache : "no-cache",
                credentials: "same-origin"				   
            });

            await response.json()
                .then((body) => {

                    console.log("response body : " + body);
                    var json = JSON.parse(body);
                    console.log("msg : ", json.msg);

                    setModal(true); // Modal 열기
                    setSaveMsg("회원 정보 " + json.msg); // Modal에 메시지 출력

                }).catch((err) => {
                    console.error("err : ",err)

                });

        } else {

            console.log("폼점검 실패");

            console.log("descriptionCheck : ", descriptionCheck);

            e.preventDefault();
        } // 
        
    } //

    return (

        // <!-- 전체 레이아웃 wrap -->
        <div className="wrap"> 

            {/* 회원정보 저장 메시지 */}
            <Modal isOpen={modal} toggle={toggle} size="sm" centered={true} fade={false}> 
                <ModalHeader toggle={toggle}>메시지</ModalHeader>
                <ModalBody>{saveMsg}</ModalBody>
                <ModalFooter>
                    <Button color="primary" onClick={toggle}>창닫기</Button>
                </ModalFooter>
            </Modal>
            {/* 회원정보 저장 메시지 끝 */}


             {/* <!-- form 컴포넌트 박스 wrap --> */}
            <div className="member-wrap">

                {/* <!-- 이름 --> */}
                <div className="input-fld-pnl">

                    <MdOutlinePerson size="1.5rem" color="#333" className="mt-2" />

                    {/* bootstrap 간격 : https://getbootstrap.com/docs/5.3/utilities/spacing/ */}
                    <Label for="name" className="mt-2">이름</Label>

                    <Input id="name" name="name" placeholder="이름을 입력하십시오" onChange={(e) => formNameCheck(e)} />

                </div>

                {/* <!-- 이름 필드 에러 메시지 --> */}
                <div>
                    {(nameCheck == false && nameError != '') && <Alert size="sm" color="danger">{nameError}</Alert>}
                </div>
                {/* <!--// 이름 --> */}

                {/* ------------------------------------------------------------------------ */}

                {/* <!-- 이메일 -->  */}
                <div className="input-fld-pnl">
                    
                    <MdOutlineMailOutline size="1.5rem" color="#333" className="mt-2" />

                    <Label for="email" className="mt-2">이메일</Label>

                    <Input id="email" name="email" type="email"
                        placeholder="이메일을 입력하십시오" onChange={(e) => formEmailCheck(e)} />

                </div>

                {/* <!-- 이메일 필드 에러 메시지 --> */}
                <div>
                    {(emailCheck == false && emailError != '') && <Alert size="sm" color="danger">{emailError}</Alert>}
                </div>
                {/* <!--// 이메일 -->  */}

                {/* <!-- 연락처 -->  */}
                <div className="input-fld-pnl">
                    
                    <MdOutlinePhoneIphone size="1.5rem" color="#333" className="mt-2" />

                    <Label for="phone" className="mt-2">연락처</Label>

                    <Input id="phone" name="phone"
                        placeholder="연락처를 입력하십시오" onChange={(e) => formPhoneCheck(e)} />
                </div>

                {/* <!-- 연락처 필드 에러 메시지 --> */}
                <div>
                    {(phoneCheck == false && phoneError != '') && <Alert size="sm" color="danger">{phoneError}</Alert>}
                </div>
                {/* <!--// 연락처 -->  */}

                {/* <!-- 상담내용 -->  */}
                <div className="input-fld-pnl">
                    
                    <MdOutlineDescription size="1.5rem" color="#333" className="mt-2" />

                    <Label for="description" className="mt-2">상담내용</Label>

                    <Input id="description" name="description" 
                            type="textarea" style={{resize:"none", height:"150px"}} onChange={(e) => formDescriptionCheck(e)} />

                </div>    
                
                {/* <!-- 상담내용 필드 에러 메시지 --> */}
                <div>
                    {(descriptionCheck == false && descriptionError != '') && <Alert size="sm" color="danger">{descriptionError}</Alert>}
                </div>
                {/* <!--// 상담내용 -->  */}

                {/* <!-- 전송/취소 버튼 --> */}
                <div className="btn-fld-pnl mt-3">

                    <div className="mx-3">
                        {/* <!-- 폼 내용의 전송을 위해 이 버튼에 이벤트 관련 코드를 작성합니다 --> */}
                        <Button onClick={(e) => formSubmit(e)}  color="primary" outline>전송</Button>
                    </div>

                    <div>
                        {/* <!-- 폼 초기화 기능 추가 --> */}
                        <Button onClick={(e) => formatInit(e)} color="primary" outline>취소</Button>
                    </div>

                </div>
                {/* <!--// 전송/취소 버튼 --> */}

            </div>
            {/* <!--// form 컴포넌트 박스 wrap --> */}
        
        </div> 
        // <!--// 전체 레이아웃 wrap --> 
    )
}

export default BootstrapForm;