import {Label, Input} from 'reactstrap'

import 'bootstrap/dist/css/bootstrap.min.css';

import './css/style.css'

import { MdOutlinePerson } from "react-icons/md"; /*아이콘*/
import { MdOutlineMailOutline } from "react-icons/md";
import { MdPhoneAndroid } from "react-icons/md";
import { MdOutlineDescription } from "react-icons/md";


function BootstrapForm() {

    return (
        <div className="wrap">

            {/* <!-- 이름 --> */}
            <div className="input-fld-pnl" id="id-fld">

                {/* <label for="name">
                    <span className="txt-label">이름</span>
                    </label>
                <input className="input-fld" type="text" id="name" name="name" required /> */}


                {/* https://getbootstrap.com/docs/5.3/utilities/spacing/  => 간격 */}
                <MdOutlinePerson size="1.5rem" color="#333" className="mt-2" />

                <Label for="name" className="mt-2">이름</Label>

                <Input id="name" name="name" placeholder="이름" />
            
            </div>
            {/* <!--// 이름 --> */}

            {/* 메일 */}
            <div className="input-fld-pnl">

                <MdOutlineMailOutline size="1.5rem" color="#333" className="mt-2" />
                
                <Label for="name" className="mt-2">메일</Label>

                <Input id="name" name="name" placeholder="메일주소" /> 

            </div>
            {/* //메일 */}

            {/* 전화번호 */}
            <div className="input-fld-pnl">

                <MdPhoneAndroid size="1.5rem" color="#333" className="mt-2" />

                <Label for="name" className="mt-2">전화번호</Label>

                <Input id="name" name="name" placeholder="전화번호" />   
            </div>
            {/* //전화번호 */}

            {/* 상담내용 */}
            <div className="input-fld-pnl">

                <MdOutlineDescription size="1.5rem" color="#333" className="mt-2" />

                <Label for="name" className="mt-2">상담내용</Label>

                <Input id="name" name="name" placeholder="상담내용" />

            </div>
            {/* //상담내용 */}


        </div>

    )

}

export default BootstrapForm