import React, { useState } from 'react';
import { BoardSearch, BoardTable, BoardRow, BoardRegisterButton } from './CMSBoard';
import {UserBoard} from './UserBoard';

// // 더미 데이터 (백엔드 API 연동 전 임시 사용)
// const dummyBoards = [
//   {
//     id: 1,
//     title: "공지사항",
//     enabled: true,
//     regDate: "2025-09-12",
//     modDate: null,
//   },
//   {
//     id: 2,
//     title: "자유게시판",
//     enabled: false,
//     regDate: "2025-09-11",
//     modDate: "2025-09-12",
//   },
// ];

// const App = () => {
//   // 상태 및 이벤트 핸들러 정의
//   const [boards, setBoards] = useState(dummyBoards); // 더미 데이터

//   const handleSearch = (keyword: string) => {
//     // 검색 로직 구현
//   };

//   const handleEdit = (id: number) => {
//     // 편집 처리
//   };

//   const handleView = (id: number) => {
//     // 조회 처리
//   };

//   const handleRegister = () => {
//     // 등록 처리
//   };

//   return (
//     <div>
//       <BoardSearch onSearch={handleSearch} />
//       <BoardRegisterButton onRegister={handleRegister} />
//       <BoardTable boards={boards} onEdit={handleEdit} onView={handleView} />
//     </div>
    
//   );

// };

const App = () => {
  return (
    <div>
      <h1>내 게시판 앱</h1>
      <UserBoard />
    </div>
  );
};

export default App;