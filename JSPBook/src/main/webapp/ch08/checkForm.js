function checkForm() {
		
		var id = document.member.id;
		var regid = /^[a-z|A-Z|0-9|.]*$/;
		var passwd1 = document.member.passwd1;
		var passwd2 = document.member.passwd2;
		var regpasswd = /^[a-z|A-Z|0-9|!|@|#|$]*$/
		var name = document.member.name;
		var regname = /^[가-힣]*$/
		var birth = document.member.birth;
		var regbirth = /^[0-9|.]*$/
		var tel = document.member.tel;
		var regtel = /^[0-9|-]*$/
		
		//아이디 입력 유무 확인
		if(id.value=="") {
			alert("아이디를 입력하세요!");
			id.focus();
			return false;
		}
		
		//아이디는 영문 대소문자, 숫자, '.'가능
		if(!id.value.match(regid)) {
			alert("아이디는 영문 대소문자, 숫자, '.'을 사용할 수 있습니다.'");
			id.focus();
			return false;
		}
		
		//비밀번호 입력 유무 확인
		if(passwd1.value=="") {
			alert("비밀번호를 입력하세요!");
			passwd1.value=""
			passwd1.focus();
			return false;
		}
		//비밀번호는 6자리 이상 12자리 이하 입력 가능
		if(passwd1.value.length < 6 || passwd1.value.length > 12) {
			alert("비밀번호는 6자리 이상 12자리 이하로 입력할 수 있습니다.");
			passwd1.value=""
			passwd1.focus();
			return false;
		}
		//비밀번호는 영문 대소문자, 숫자 !, @, #, $ 가능
		if(!passwd1.value.match(regpasswd)) {
			alert("비밀번호는 영문 대소문자, 숫자, '!, @, #, $'를 사용할 수 있습니다.");
			passwd1.value=""
			passwd1.focus();
			return false;
		}
		//비밀번호 확인은 비밀번호와 같아야 한다!
		if(passwd1.value!=passwd2.value)  {
			alert("비밀번호와 비밀번호 확인이 일지하지 않습니다. \n확인해보시겠어요?");
			passwd1.value=""
			passwd2.value=""
			passwd2.focus();
			return false;
		}
		//이름 입력 유무 확인
		if(name.value=="") {
			alert("이름을 입력하세요!");
			name.focus();
			return false;
		}
		//이름은 한글로 입력
		if(!name.value.match(regname)) {
			alert("이름은 한글로만 입력해주세요.");
			name.focus();
			return false;
		}
		//생일 입력 확인
		if(birth.value=="") {
			alert("생년월일을 입력하세요!");
			passwd1.focus();
			return false;
		}
		//생일은 숫자와 '.'으로 입력
		if(!birth.value.match(regbirth)) {
			alert("생년월일은 숫자와 점(.)으로만 입력해주세요.");
			birth.focus();
			return false;
		}
		//연락처 입력 확인
		if(tel.value=="") {
			alert("연락처를 입력하세요!");
			tel.focus();
			return false;
		}
		//연락처는 숫자와 하이픈(-) 입력
		if(!tel.value.match(regtel)) {
			alert("연락처는 숫자와 하이픈(-)으로만 입력해주세요.");
			tel.focus();
			return false;
		}
		id.submit();
		passwd1.submit();
		passwd2.submit();
		name.submit();
	}