import {Label, Input, Button} from 'reactstrap'

import 'bootstrap/dist/css/bootstrap.min.css';

import './css/style.css'

import { MdOutlinePerson } from "react-icons/md"; /*아이콘*/
import { MdOutlineMailOutline } from "react-icons/md";
import { MdPhoneAndroid } from "react-icons/md";
import { MdOutlineDescription } from "react-icons/md";


function BootstrapForm() {

    return (
        <div className="wrap">

            <div className="member-wrap">

                {/* <!-- 이름 --> */}
                <div className="input-fld-pnl">

                    {/* <label for="name">
                        <span className="txt-label">이름</span>
                        </label>
                    <input className="input-fld" type="text" id="name" name="name" required /> */}


                    {/* https://getbootstrap.com/docs/5.3/utilities/spacing/  => 간격 */}
                    <MdOutlinePerson size="1.5rem" color="FF7F27" className="mt-2" />

                    <Label for="name" className="mt-2" style={{color:"orange", fontWeight:"bold"}}>이름</Label>

                    <Input id="name" name="name" placeholder="이름" />
                
                </div>
                {/* <!--// 이름 --> */}

                {/* 메일 */}
                <div className="input-fld-pnl">

                    <MdOutlineMailOutline size="1.5rem" color="FF7F27" className="mt-2" />
                    
                    <Label for="email" className="mt-2" style={{color:"orange", fontWeight:"bold"}}>이메일</Label>

                    <Input id="email" name="email" type="email" placeholder="메일주소" /> 

                </div>
                {/* //메일 */}

                {/* 전화번호 */}
                <div className="input-fld-pnl">

                    <MdPhoneAndroid size="1.5rem" color="FF7F27" className="mt-2" />

                    <Label for="phone" className="mt-2" style={{color:"orange", fontWeight:"bold"}}>전화번호</Label>

                    <Input id="phone" name="phone" placeholder="전화번호" />   
                </div>
                {/* //전화번호 */}

                {/* 상담내용 */}
                <div className="input-fld-pnl">

                    <MdOutlineDescription size="1.5rem" color="FF7F27" className="mt-2" />

                    <Label for="description" className="mt-2" style={{color:"orange", fontWeight:"bold"}}>상담내용</Label>

                    <Input id="description" name="description" type="textarea" placeholder="상담내용" style={{resize:"none", height: "150px"}} />

                </div>
                {/* //상담내용 */}
                
                {/* 전송,취소버튼 */}
                <div className="btn-fld-pnl mt-3">
                    <div className="mx-3">
                        <Button color="primary" outline>전송</Button>
                    </div>
                    <div>
                        <Button color="info" outline>취소</Button>
                    </div>

                </div>
                {/* //전송,취소버튼 */}
            </div>
        </div>

    )

}

export default BootstrapForm