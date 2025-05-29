import { Label, Input, Button } from 'reactstrap'
import { MdOutlinePerson } from "react-icons/md";
import { MdOutlineMailOutline } from "react-icons/md";
import { MdOutlinePhoneIphone } from "react-icons/md";
import { MdOutlineDescription } from "react-icons/md";

import 'bootstrap/dist/css/bootstrap.min.css';

import './css/style.css'

function BootstrapForm() {

    // AJAX(Promise/fetch) 코드
    const formSubmit = async(e) => {
        
        console.log("e target : ", e.target);

        // 입력된 폼의 내용들을 등록
        let name = document.getElementById("name").value;        
        let email = document.getElementById("email").value;
        let phone = document.getElementById("phone").value;
        let description = document.getElementById("description").value;

        console.log("name : ", name);
        console.log("email : ", email);
        console.log("phone : ", phone);
        console.log("description : ", description);

        const response     
            = await fetch(`http://localhost:3000/actionReactForm/${name}/${email}/${phone}/${description}`, 
		{ 
			method : "GET",
			mode : "cors",
			cache : "no-cache",
			credentials: "same-origin"				   
		});

        const body = await response.json();
        console.log("json body : " + body);
        
    } //

    return (

        // <!-- 전체 레이아웃 wrap -->
        <div className="wrap"> 
        
            {/* <!-- form 컴포넌트 박스 wrap --> */}
            <div className="member-wrap">

                {/* <!-- 이름 --> */}
                <div className="input-fld-pnl">

                    <MdOutlinePerson size="1.5rem" color="#333" className="mt-2" />

                    {/* bootstrap 간격 : https://getbootstrap.com/docs/5.3/utilities/spacing/ */}
                    <Label for="name" className="mt-2">이름</Label>

                    <Input id="name" name="name" placeholder="이름을 입력하십시오"/>

                </div>
                {/* <!--// 이름 --> */}

                {/* <!-- 이메일 -->  */}
                <div className="input-fld-pnl">
                    
                    <MdOutlineMailOutline size="1.5rem" color="#333" className="mt-2" />

                    <Label for="email" className="mt-2">이메일</Label>

                    <Input id="email" name="email" type="email"
                        placeholder="이메일을 입력하십시오" />

                </div>
                {/* <!--// 이메일 -->  */}

                {/* <!-- 연락처 -->  */}
                <div className="input-fld-pnl">
                    
                    <MdOutlinePhoneIphone size="1.5rem" color="#333" className="mt-2" />

                    <Label for="phone" className="mt-2">연락처</Label>

                    <Input id="phone" name="phone"
                        placeholder="연락처를 입력하십시오" />
                </div>
                {/* <!--// 연락처 -->  */}

                {/* <!-- 상담내용 -->  */}
                <div className="input-fld-pnl">
                    
                    <MdOutlineDescription size="1.5rem" color="#333" className="mt-2" />

                    <Label for="description" className="mt-2">상담내용</Label>

                    <Input id="description" name="description" 
                            type="textarea" style={{resize:"none", height:"150px"}} />

                </div>    
                {/* <!--// 상담내용 -->  */}

                {/* <!-- 전송/취소 버튼 --> */}
                <div className="btn-fld-pnl mt-3">

                    <div className="mx-3">
                        {/* <!-- 폼 내용의 전송을 위해 이 버튼에 이벤트 관련 코드를 작성합니다 --> */}
                        <Button onClick={(e) => formSubmit(e)}  color="primary" outline>전송</Button>
                    </div>

                    <div>
                        <Button color="primary" outline>취소</Button>
                    </div>

                </div>
                {/* <!--// 전송/취소 버튼 --> */}

            </div>
            {/* <!--// form 컴포넌트 박스 wrap --> */}
        
        </div> 
        // <!--// 전체 레이아웃 wrap --> 
    )
}

export default BootstrapForm