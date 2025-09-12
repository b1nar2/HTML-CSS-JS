import React, { useState, useEffect } from 'react';

type BoardItem = {
  id: number;
  title: string;
  author: string;
  views: number;
  date: string;
  isNotice?: boolean;
};

const PAGE_SIZE = 5;

export const UserBoard: React.FC = () => {
  const [boards, setBoards] = useState<BoardItem[]>([]);
  const [page, setPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const fetchBoards = async (pageNum: number) => {
    setLoading(true);
    setError(null);
    try {
      const response = await fetch(`/api/boards?page=${pageNum}&size=${PAGE_SIZE}`);
      if (!response.ok) throw new Error('네트워크 응답 오류');
      const data = await response.json();
      // 백엔드에서 { boards: BoardItem[], totalCount: number } 형태로 응답 온다고 가정
      setBoards(data.boards);
      setTotalPages(Math.ceil(data.totalCount / PAGE_SIZE));
    } catch (e) {
      setError('게시판 데이터를 불러오는 데 실패했습니다.');
      console.error(e);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchBoards(page);
  }, [page]);

  if (loading) return <div>로딩 중...</div>;
  if (error) return <div>{error}</div>;

  return (
    <div>
      <h2>게시판</h2>
      <table className="board-table">
        <thead>
          <tr>
            <th>번호</th>
            <th>시설명</th>
            <th>작성자</th>
            <th>조회수</th>
            <th>등록일</th>
          </tr>
        </thead>
        <tbody>
          {boards.map(item => (
            <tr key={item.isNotice ? 'notice' : item.id}>
              <td>{item.isNotice ? '공지' : item.id}</td>
              <td><a href="#">{item.title}</a></td>
              <td>{item.author}</td>
              <td>{item.views}</td>
              <td>{item.date}</td>
            </tr>
          ))}
        </tbody>
      </table>

      <div className="paging">
        <button onClick={() => setPage(1)} disabled={page === 1}>{'<<'}</button>
        <button onClick={() => setPage(p => Math.max(p - 1, 1))} disabled={page === 1}>{'<'}</button>
        <span> {page} / {totalPages} </span>
        <button onClick={() => setPage(p => Math.min(p + 1, totalPages))} disabled={page === totalPages}>{'>'}</button>
        <button onClick={() => setPage(totalPages)} disabled={page === totalPages}>{'>>'}</button>
      </div>
    </div>
  );
};
