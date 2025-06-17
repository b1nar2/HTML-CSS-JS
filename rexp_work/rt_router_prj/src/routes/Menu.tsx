import { Link, Outlet } from "react-router-dom";
import { Nav, NavItem, NavLink, Button } from 'reactstrap'; 

import 'bootstrap/dist/css/bootstrap.min.css';

export default function Menu() {

    return (
        <div>
            <nav style={{listStyleType : "none", display : "flex", width: "500px"}}>
                <li style={{ margin : "10px"}}>
                    <Link to="/">
                        <Button color="primary">Home</Button>
                    </Link>
                </li>
                <li style={{ margin : "10px"}}>
                    <Link to="/SubPage1">
                        <Button color="primary">SubPage1</Button>
                    </Link>
                </li>
                <li style={{ margin : "10px"}}>
                    <Link to="/SubPage2">
                        <Button color="primary">SubPage2</Button>
                    </Link>
                </li>
            </nav>

            <hr />
            
            {/* 가령 예를 들어서 "/board" 경로(path) 아래 "/board/write" 라는
            하위 경로를 정의할 경우, 이러한 경우가 "중첩 라우팅" 에 해당되는데,
            
            부모 컴포넌트는 레이아웃(layout)이나 공동 메뉴(common menu) 등의 
            공통 요소들을 렌더링(rendering)하고,

            자녀 컴포넌트들은 Outlet 컴포넌트를 사용하여 OUtlet 컴포넌트가 
            위치한 곳에 렌더링하여 레이아웃 템플릿(layout template) 구조를 
            보다 편리하게 구성할 수 있습니다.             */}

            <Outlet />

        </div>

        // <Nav fill pills>

        //     <NavItem>
        //         <NavLink href="/Home">
        //             Home
        //         </NavLink>
        //     </NavItem>

        //     <NavItem>
        //         <NavLink href="/SubPage1">
        //             SubPage1
        //         </NavLink>
        //     </NavItem>

        //     <NavItem>
        //         <NavLink href="/SubPage2">
        //             SubPage2
        //         </NavLink>
        //     </NavItem>

        // </Nav>
    )
}