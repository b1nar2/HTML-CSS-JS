import React, { useState } from 'react';
import {Menu1Depth, Menu2Depth, ParentMenu} from './UserNav';


// 1. 함수형 컴포넌트 - 화면에 보일 UI를 정의
const App = (): React.JSX.Element => {
  
  // 2. useState 훅으로 상태 정의 (selectedMenu 변수와 상태 변경 함수)
  const [selectedMenu, setSelectedMenu] = useState<string>('이용안내');
  
  // 3. 키-값 쌍으로 1뎁스 메뉴별 하위 메뉴 데이터 정의
  const subMenusMap: { [key: string]: string[] } = {
    '이용안내': ['하위1', '하위2'],
    '상품/시설안내': ['하위3', '하위4'],
    '시설예약': ['하위5', '하위6'],
    '통합게시판': ['하위7', '하위8'],
    '마이페이지': ['하위9', '하위10'],
  };

  // 4. 화면에 렌더링할 JSX 반환 (최종 UI 요소)
  return (
    <>
      {/* 5. 1뎁스 메뉴 컴포넌트 호출 */}
      <Menu1Depth 
        selectedMenu={selectedMenu}            // 현재 상태값 전달
        onSelectMenu={(menu) => setSelectedMenu(menu)}  // 메뉴 선택 시 상태 변경 콜백 전달
      />
      {/* 6. 2뎁스 메뉴 컴포넌트 호출 */}
      <Menu2Depth
        parentMenu={selectedMenu}
        subMenus={subMenusMap[selectedMenu]} // 현재 선택 메뉴에 따른 하위 메뉴 배열 전달
      />
    </>
  );
};

// Menu1Depth, Menu2Depth 컴포넌트는 별도로 정의되어 있다고 가정
// 생략 가능하거나 필요시 위에 같이 추가

export default App;
