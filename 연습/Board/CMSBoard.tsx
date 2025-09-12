import React, { useState } from 'react';

// 타입 정의
type Board = {
  id: number;
  title: string;
  enabled: boolean;
  regDate: string;
  modDate?: string | null;
};



type BoardTableProps = {
  boards: Board[];
  onEdit: (id: number) => void;
  onView: (id: number) => void;
};

type BoardRowProps = {
  board: Board;
  onEdit: (id: number) => void;
  onView: (id: number) => void;
};

// BoardSearch 컴포넌트
export const BoardSearch = ({ onSearch }: { onSearch: (kw: string) => void }) => {
  const [keyword, setKeyword] = useState('');
  return (
    <div>
      <input value={keyword} onChange={e => setKeyword(e.target.value)} />
      <button onClick={() => onSearch(keyword)}>검색</button>
    </div>
  );
};

// BoardTable 컴포넌트
export const BoardTable = ({ boards, onEdit, onView }: BoardTableProps) => (
  <table>
    <thead>
      <tr>
        <th>번호</th>
        <th>게시판 제목</th>
        <th>게시판 편집</th>
        <th>게시글조회</th>
        <th>이용 가능 여부</th>
        <th>등록일</th>
        <th>수정일</th>
      </tr>
    </thead>
    <tbody>
      {boards.map(board => (
        <BoardRow key={board.id} board={board} onEdit={onEdit} onView={onView} />
      ))}
    </tbody>
  </table>
);

// BoardRow 컴포넌트
export const BoardRow = ({ board, onEdit, onView }: BoardRowProps) => (
  <tr>
    <td>{board.id}</td>
    <td>
      <a onClick={() => onView(board.id)}>{board.title}</a>
    </td>
    <td>
      <button onClick={() => onEdit(board.id)}>편집</button>
    </td>
    <td>
      <button onClick={() => onView(board.id)}>조회</button>
    </td>
    <td>{board.enabled ? 'Y' : 'N'}</td>
    <td>{board.regDate}</td>
    <td>{board.modDate ?? '-'}</td>
  </tr>
);

// BoardRegisterButton 컴포넌트
export const BoardRegisterButton = ({ onRegister }: { onRegister: () => void }) => (
  <button onClick={onRegister}>등록</button>
);