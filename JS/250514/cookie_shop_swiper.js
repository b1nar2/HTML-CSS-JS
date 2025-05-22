// 문-3) 기존의 부트스트랩 슬라이드에서 사용하였던 Javascript 도입부를 
// 그대로 옮겨서 초기 코드 부분을 완성하십시오.

// TODO

// 메인(main)
window.onload = function() {

    // 쿠키 객체 배열 초기화

    // 위의 세가지 배열(cookie_image_files,
    // cookie_text, cookie_price)을 
    // cookies 라는 배열에 추가하는 
    // 구문을 작성합니다.
    for (let i = 0; i < cookie_image_files.length; i++)
    {
        cookies.push(new CookieProduct(cookie_image_files[i], 
                                       cookie_text[i], 
                                       cookie_price[i]));
    } // 

    // 화면 가용공간(viewport) 높이 설정
    let sections = document.querySelectorAll("[id$=_section]");

    for (let section of sections) {
        section.style.height = window.innerHeight;
    }

    // 슬라이더 이미지(쿠키) 로딩(추가)
    let cookie_image = "";

    ////////////////////////////////////////////////////////////////////////////////////////

    // 문-4) 쿠키 캐러셀 슬라이드 갤러리를 구성할 개별 슬라이드 패널들을 Javascript로 구성합니다.
    
    // 개별 슬라이드 패널 구성
    // 
    // 참고) https://swiperjs.com/get-started#add-swiper-html-layout
    /*
         <div class="swiper-wrapper">

            <!-- Slides -->
            <div class="swiper-slide">Slide 1</div> <!-- 개별 슬라이드 -->
            <div class="swiper-slide">Slide 2</div> <!-- 개별 슬라이드 -->
            <div class="swiper-slide">Slide 3</div> <!-- 개별 슬라이드 -->
            
            ...

        </div>
    */

    // TODO
    
    //////////////////////////////////////////////////////////////////////////

    // 문-5) 아래의 swiper 초기화 구문을 참고하여 swiper 갤러리 스크립트를 초기화합니다.
    //      단, 세로가 아닌 가로 방향으로 슬라이드가 전개될 수 있도록 설정을 변경하고 
    //      스크롤바에 대한 설정은 삭제(배제)합니다.

    // 참고) https://swiperjs.com/get-started#initialize-swiper
    //      https://swiperjs.com/swiper-api#initialize-swiper
    // 
    // ※ swiper API DOC (기술문서) : https://swiperjs.com/swiper-api

    // ※ 슬라이드 인자들(매개변수) API DOC : https://swiperjs.com/swiper-api#parameters
    // ※ 슬라이드 방향성 인자(매개변수) API DOC : https://swiperjs.com/swiper-api#param-direction
    

    // TODO


} // window.onload 