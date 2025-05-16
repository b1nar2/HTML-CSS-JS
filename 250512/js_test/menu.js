/*
	문) 주어진 조건에 맞게 Javascript 코드를 완성하십시오.
*/	
// 각 메인 메뉴의 서브 메뉴 출력
window.onload = () => {
// window.onload = function() {

	// 모든 서브 메뉴 초기 상태 : 은닉(hidden)
	// 모든 서브 메뉴를 sub_menus 변수로 할당(대입) 
	// 선택자(selector) 적용 : id가 _sub로 끝나는 div 태그들
	
	// 문-1) id가 "_sub"로 끝나는 모든 div 태그를 제어하는 sub_menus 변수를 작성하십시오.
	// TODO

	let sub_menus = document.querySelectorAll("div[id$=_sub]");

	// 문-2)
	// 반복문(for)를 활용하여 모든 서브 메뉴들의 보이기 속성(visibility)를 은닉(감추기) 상태로 초기화 설정합니다.
    // Hint) div 태그에 대한 은닉(가리기)에 제어는 visibility 속성을 활용할 수 있습니다.
	// 참고) https://developer.mozilla.org/en-US/docs/Web/CSS/visibility
	// TODO

    // 방법-1)
	// for (let i=0; i<sub_menus.length; i++) {
	// 	sub_menus[i].style.visibility = "hidden";
	// } // for
	
	// 해석
	// for (let i=0; i<sub_menus.length; i++)
	// let i=0; ==> 탐색을 시작할 인덱스 지정(배열의 첫 번째 인덱스, 0)
	// i<sub_menus.length;  ==> i가 배열의 개수가 되기 전까지 (array.ength?? ==> https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Array/length)
	// i++ ==> i를 하나씩 증가해라.
	// sub_menus[i].style.visibility = "hidden"; ==> 모든 [i]에 스타일 먹이기
	

    // 방법-2)
    // for (let sub_menu of sub_menus) {
	// 	sub_menu.style.visibility = "hidden";
	// } // for

    // 방법-3) 
    sub_menus.forEach((element) => {
        element.style.visibility = "hidden";
    });

	
	// 메인 메뉴들을 main_menus 변수로 할당합니다.
	// 선택자(selector) 적용 : id가 _btn으로 끝나는 li 태그들
	// 문-3) id가 "_btn"으로 끝나는 모든 li 태그를 메인 메뉴들로 제어하는 main_menus 변수를 할당합니다.
	// TODO
	  
	let main_menus = document.querySelectorAll("li[id$=_btn]")

    main_menus.forEach((main_menu) => {

		// 각 개별 메인 메뉴에 마우스를 올렸을 때(rollover) 이벤트 처리 
		main_menu.onmouseover = (e) => {
			
			// this.id = e.currentTarget.id 여기서는 같은 의미로써
			// 마우스 롤오버한 대상(target) 태그(li)의 아이디 
			
			// 참고) Event.target / Event.currentTarget 
            // : https://developer.mozilla.org/ko/docs/Web/API/Event/target
            // : https://developer.mozilla.org/en-US/docs/Web/API/Event/currentTarget

            // 참고) 이벤트 버블링
            // : https://developer.mozilla.org/ko/docs/Learn_web_development/Core/Scripting/Event_bubbling

            // const rollover_menu = this.id.split("_")[0]; // 참고 코드-1
            const rollover_menu = e.currentTarget.id.split("_")[0];

            console.log("rollover_menu : ", rollover_menu);
			
			// 홈(home) 메뉴는 롤오버 적용 제외
			if (rollover_menu != "home") {
			
				// 메인 메뉴 버튼의 아이디 추출을 통해서 서브 메뉴 아이디를 선택
				let sub_menu_id = rollover_menu + "_sub";
				// 메인 메뉴 계열(자신)의 서브 메뉴
				let this_sub_menu = document.getElementById(sub_menu_id);
				
				// 참고 코드-2
				// 다른 서브 메뉴들을 은닉(감추기)
				// for ~ of 문을 활용합니다. 
				// 전체 서브 메뉴 변수 : sub_menus
				// 단, 반복문 내에서 조건식을 사용하여 서브 메뉴 아이디(id)가 자신의 서브 메뉴 아이디(this_sub_menu)
				// 가 아닌 것만 선택적으로 은닉(hidden)하도록 조치합니다.
				for (const sub_menu_one of sub_menus) {
					
					if (sub_menu_one.id != this_sub_menu.id) {
						sub_menu_one.style.visibility = "hidden";
					} 
					
				} // for
				
				// 자신의 서브 메뉴(this_sub_menu)를 보이도록 조치
				this_sub_menu.style.visibility = "visible";
				
			} // 홈(home) 버튼 롤오버 할 경우 모든 서브 메뉴(sub_menus) 은닉 
			else if (rollover_menu == "home") {

				// 위의 구문(참고 코드-2)과 유사하게 for ~ of 문을 활용합니다.
				// 단, 모든 서브 메뉴를 은닉 조치하기 때문에 조건식은 없습니다.  
				for (const sub_menu_one of sub_menus) {
					sub_menu_one.style.visibility = "hidden";
				} // for
				
			} //
			
		} // onmouseover
	
 	}); // forEach
	
	// 서브 메뉴 롤아웃 할 경우 이벤트 핸들러
	// for ~ of 문을 활용하여 서브 메뉴(sub_menus) 변수의 개별 서브메뉴(sub_menu_one)의 이벤트 처리
    sub_menus.forEach((sub_menu_one) => {
		
		// 개별 서브메뉴(sub_menu_one)에 마우스 롤아웃(mouseout) 하였을 때 이벤트 핸들(처리)
		sub_menu_one.onmouseout = (e) => {
			
			// 서브 메뉴 자신의 아이디에서 "_"를 제거한 첫번째 토큰을 rollover_nemu라는 변수로 할당 
			// 참고 코드-1의 방식을 참조합니다.
			const rollover_menu = e.currentTarget.id.split("_")[0];
			
			// 홈 메뉴(home)는 롤오버 적용 제외 : 조건식(if)
			if (rollover_menu != "home") {
			
				// 메인 메뉴 버튼의 아이디(this.id) 추출을 통해서 서브 메뉴 아이디를 선택
				// 참고 코드-1의 방식을 참조하며 추가적으로 "_sub" 첨가하여
				// 아이디(sub_menu_id) 변수에 할당(대입)합니다.
				let sub_menu_id = rollover_menu + "_sub";
				
				// 위에서 추출한 아이디(sub_menu_id)를 가진 객체(태그)를 this_sub_menu 변수로 할당합니다.
				let this_sub_menu = document.getElementById(sub_menu_id);
				
				// this_sub_menu 보이기(visibility) 속성을 은닉(hidden)으로 설정합니다. 
				this_sub_menu.style.visibility = "hidden";	
				
			} // if	
			
		}  // 개별 서브메뉴(sub_menu_one)에 마우스 롤아웃(mouseout) 하였을 때 이벤트 핸들(처리) "끝"
		
    }); // forEach 함수를 활용하여 서브 메뉴(sub_menus) 변수의 개별 서브메뉴(sub_menu_one)의 이벤트 처리 "끝" 
	
} //