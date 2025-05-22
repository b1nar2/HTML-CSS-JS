window.onload = () => {

    // js 외장화 => 파일 경로 변경 확인!!

     // 단가 천단위 "," 포맷 처리
     function numberWithCommas(x) {
        x = x.toString();
        var pattern = /(-?\d+)(\d{3})/;
        while (pattern.test(x))
            x = x.replace(pattern, "$1,$2");
        return x;
    }// 단가

   async function fetchCSV(url) {
       try {
           const response = await fetch(url);
           const data = await response.text();

           // CSV -> JSON
           let json = Papa.parse(data.trim()); // 공백 제거 trim
            console.log("json :", json.data);        
           let products = json.data; // 첫줄(컬럼), 마지막(공백 => 삭제처리함)
           products.shift(); // 배열의 첫 줄(첫 요소:컬럼) 제거
           
           console.log("상품 정보들 :", products);
           console.log("상품 수 :", products.length); // 상품수

           for(let product of products) {

               /*
               console.log("상품 이미지 :%s", product[0]+"_LM1.jpg");
               console.log("상품명 :%s", product[1]);
               console.log("상품 브랜드 :%s", product[3]);
               console.log("상품 단가 :%s", product[4]);
               console.log("상품 신상품 여부 :%s", (product[5] == 'O'? "신상품" : "기존상품"));
               console.log("상품 추천평 :%d", product[6]);

               console.log("-------------------------------");
               */

               let product_content =
                   `<!-- 의류 단품 패널 -->
                    <div class="wear-pnl">

                       <!-- 의류 관심 상품(좋아요) 등록 -->
                       <div class="wear-preferred-item">
                           <span class="material-symbols-outlined preferred-item-icon">
                               favorite
                           </span>              
                       </div>
                       <!--// 의류 관심 상품(좋아요) 등록 -->

                       <!-- 의류 썸네일 사진 패널 -->              
                       <div class="wear-gallery-pic" title="${product[0]}">
                           <img class="img1" src="./pic/${product[0]}_LM1.jpg" />
                           <img class="img2" src="./pic/${product[0]}_LM2.jpg" />
                       </div>
                       <!--// 의류 썸네일 사진 패널 -->

                       <!-- 원단 스타일 -->
                       <div class="wear-fabric">
                           <div class="wear-fabric-icon">             
                               <img src="./pic/${product[0]}_LM1.jpg" />
                           </div>
                       </div>
                       <!--// 원단 스타일 -->

                       <!-- 브랜드 -->
                       <div class="brand-name">
                           ${product[3]}
                       </div>
                       <!--// 브랜드 -->

                       <!-- 상품명-->
                       <div class="wear-name">
                           ${product[1]}
                       </div>
                       <!--// 상품명-->

                       <!-- 상품 단가 -->
                       <div class="wear-price">
                           ${numberWithCommas(product[4])} 원
                       </div>
                       <!--// 상품 단가 -->

                       <!-- 상품 추천 별점 및 상품평 -->
                       <div class="wear-recomm-review">
                       
                           ${product[5] == 'O'? '' :
                           `<div class="wear-recomm-review-icon-wrap">
                               <span class="material-symbols-outlined">
                                   star
                               </span>
                               <span class="wear-recomm-review-num">${product[6]}</span>    

                               <span class="material-symbols-outlined">
                                   reviews
                               </span>
                               <span class="wear-recomm-review-num">${product[7]}</span>
                           </div>`}

                       </div>
                       <!--// 상품 추천 별점 및 상품평 -->

                       <!-- 신상품 여부 -->
                       <div class="new-wear">
                           ${product[5] == 'O' ?
                           `<span class="new-wear-icon">신상품</span>` : ''}
                       </div> 
                       <!--// 신상품 여부 -->

                   </div>
                   <!--// 의류 단품 패널 -->`;
              
                   let wrap = document.querySelector(".wrap");
                   wrap.innerHTML += product_content;

           } // for

       } catch (error) {
           console.error('Error fetching CSV:', error);
       }


    //////////////////////////////////////////////////////////////////



     // 갤러리 이미지 이벤트 핸들러: 클릭시 => 확대 이미지 팝업 출력

     let wear_gallery_pics = document.querySelectorAll(".wear-gallery-pic")

     wear_gallery_pics.forEach((element) => {

        element.addEventListener('click', (event) => {
            
            // 이벤트 버블링 : https://developer.mozilla.org/ko/docs/Learn_web_development/Core/Scripting/Event_bubbling
            // 이벤트 버블링 방지 : 이벤트가 하위 태그로 전파(propagation)
            // preventDefault : https://developer.mozilla.org/ko/docs/Web/API/Event/preventDefault => 이벤트 동작 금지
            // event.stopPropagation();
            // event.preventDefault();

            // console.log("event.target :", event.currentTarget);
            // 이벤트 버블링 => div(target), img(target) => 타켓이 일관되지 않음!

            console.log("event.currentTarget :", event.currentTarget);
            // 이벤트 버블링 적용(X) => div(currentTarget)

            let product_id = event.currentTarget.title;

            //팝업창에 확대 이미지 삽입
            let img_popup_body = document.getElementById("img_popup_body");
                console.log("img_popup_body : ", img_popup_body);
             
            img_popup_body.innerHTML = `<img class="img1" src="./pic/${product_id}_LM1.jpg" />`;
                console.log("img_popup_body : ", img_popup_body);

            let img_popup = document.getElementById("img_popup");     

            img_popup.style.visibility = "visible"; 

        });

        }); // forEach




            //창닫기 버튼 이벤트 핸들러
        
        let close_btn=document.getElementById("close_btn");
        close_btn.addEventListener('click', () => {

            console.log("창닫기");

            // 팝업창 은닉(가리기)
            // img_popup.style.width=0;
            // img_popup.style.height=0;
            img_popup_body.innerHTML=''; // 확대 이미지 삭제(초기화)
            img_popup.style.visibility="hidden";

            });
        


    } // async funtion


   // fetchCSV('https://example.com/data.csv');
   // fetchCSV('../재료/상품정보-추가상품.csv');
   fetchCSV('./재료/상품정보-추가상품.csv');

}// window