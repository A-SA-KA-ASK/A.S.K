function PasswordSet () {

    // 비밀번호를 재설정 후 로그인 페이지로 이동하게 함.
    
    return(
        <div>
            <h1>비밀번호 재설정</h1>
            <div>
                <input type="password" placeholder="비밀번호를 입력해주세요."/>
                <input type="password" placeholder="비밀번호를 다시 한 번 입력해주세요."/>
                <button>조회하기</button>
            </div>
        </div>
    );
}

export default PasswordSet;