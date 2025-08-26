import React, { useEffect, useState } from 'react';
import './App.css'; // 또는 App.css


function getPrecipitationStatus(pty) {
  switch (parseInt(pty)) {
    case 0:
      return { label: '맑음', icon: '☀️' };
    case 1:
      return { label: '비', icon: '🌧️' };
    case 2:
      return { label: '진눈깨비', icon: '🌨️' };
    case 3:
      return { label: '눈', icon: '❄️' };
    case 4:
      return { label: '소나기', icon: '🌦️' };
    case 5:
      return { label: '이슬비', icon: '🌫️' };
    case 6:
      return { label: '눈날림', icon: '🌬️' };
    default:
      return { label: '알 수 없음', icon: '❓' };
  }
}

// 항목별 값 포맷 변경
function formatValue(category, value) {
  switch (category) {
    case 'T1H': // 기온
      return `${value}°C`;
    case 'REH': // 습도
      return `${value}%`;
    case 'WSD': // 풍속
      return `${value} m/s`;
    case 'RN1': // 강수량
      return `${value} mm`;
    case 'PTY': // 강수형태는 이미 텍스트로 처리됨
      return ''; // 또는 생략
    default:
      return value;
  }
}


function Weather() {
  const [weatherItems, setWeatherItems] = useState([]);

  const selectedCategories = ['PTY', 'T1H', 'REH']; // 보여줄 항목

const filteredItems = weatherItems.filter(item =>
  selectedCategories.includes(item.category)
);
  const categoryMap = {
  PTY: { label: '현재 날씨', icon: '🌦️' },
  REH: { label: '습도', icon: '💧' },
  RN1: { label: '1시간 강수량', icon: '🌧️' },
  T1H: { label: '기온', icon: '🌡️' },
  UUU: { label: '동서 바람', icon: '↔️' },
  VEC: { label: '풍향', icon: '🧭' },
  VVV: { label: '남북 바람', icon: '↕️' },
  WSD: { label: '풍속', icon: '🌬️' },
};

  useEffect(() => {
    const fetchWeather = async () => {
      try {
        const res = await fetch(
          'http://localhost:8082/api/weather?date=20250826&time=1600&nx=55&ny=127'
        );
        const data = await res.json();
        console.log('백엔드 응답:', data);

        // ✅ 더미 데이터 삽입
        if (!data.items || data.items.length === 0) {
          setWeatherItems([
            { category: 'TMP', obsrValue: '27.5' },
            { category: 'REH', obsrValue: '60' },
            { category: 'WSD', obsrValue: '2.3' },
          ]);
        } else {
          setWeatherItems(data.items);
        }
      } catch (err) {
        console.error('API 호출 실패:', err);
      }
    };

    fetchWeather();
  }, []);

  return (
<div className="weather-grid">
  {filteredItems.map((item, idx) => {
    let info;

    if (item.category === 'PTY') {
      info = getPrecipitationStatus(item.obsrValue);
    } else {
      info = categoryMap[item.category] || { label: item.category, icon: '❓' };
    }

    return (
      <div key={idx} className="weather-card">
        <div className="icon">{info.icon}</div>
        <div className="label">{info.label}</div>
        <div className="value">{formatValue(item.category, item.obsrValue)}</div>
      </div>
    );
  })}

</div>


  );
}

export default Weather;
