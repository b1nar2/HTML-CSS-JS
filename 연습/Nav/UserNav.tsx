import React, { useState } from 'react';
import './style.css'

// 1뎁스 메뉴 컴포넌트 props 타입 정의
type Menu1DepthProps = {
  selectedMenu: string;
  onSelectMenu: (menu: string) => void;
};

// 1뎁스 메뉴 컴포넌트
export const Menu1Depth = ({ selectedMenu, onSelectMenu }: Menu1DepthProps) => {
  const menus = ['이용안내', '상품/시설안내', '시설예약', '통합게시판', '마이페이지'];

  return (
    <nav className="menu-1depth">
      {menus.map(menu => (
        <div className="menu-item"
          key={menu}
          style={{ fontWeight: menu === selectedMenu ? 'bold' : 'normal',
           }}
          onClick={() => onSelectMenu(menu)}>
          {menu}
       </div>
      ))}
    </nav>
  );
};

// 2뎁스 메뉴 컴포넌트 props 타입 정의
type Menu2DepthProps = {
  parentMenu: string;
  subMenus: string[];
};

// 2뎁스 메뉴 컴포넌트
export const Menu2Depth = ({ parentMenu, subMenus }: Menu2DepthProps) => {
  return (
    <nav className="menu-2depth">
       <div className="parent-menu-name">{parentMenu}</div>  {/* 1뎁스 메뉴명 표시 */}
        {subMenus.map(sub => (
          <div className ="menu-item" key={sub}>{sub}</div>
      ))}
      </nav>
  );
};

// 최상위 부모 컴포넌트
export const ParentMenu = () => {
  const [selectedMenu, setSelectedMenu] = useState<string>('이용안내');

  // 1뎁스 메뉴별 하위 메뉴 데이터
  const subMenusMap: { [key: string]: string[] } = {
    '이용안내': ['하위1', '하위2'],
    '상품/시설안내': ['하위3', '하위4'],
    '시설예약': ['하위5', '하위6'],
    '통합게시판': ['하위7', '하위8'],
    '마이페이지': ['하위9', '하위10']
  };

  return (
    <div>
      {/* 1뎁스 메뉴: 선택 상태와 변경 함수 전달 */}
      <Menu1Depth selectedMenu={selectedMenu} onSelectMenu={setSelectedMenu} />
      
      {/* 2뎁스 메뉴: 1뎁스에서 선택된 메뉴에 따른 하위 메뉴 배열 전달 */}
      <Menu2Depth
        parentMenu={selectedMenu}
        subMenus={subMenusMap[selectedMenu]} />
    </div>
  );
};
