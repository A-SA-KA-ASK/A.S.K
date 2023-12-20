function PasswordSet () {
    return(
        <div>
            <h1>비밀번호 재설정</h1>
            <form >
                <input type="password" placeholder="비밀번호를 입력해주세요." />
                <input type="password" placeholder="비밀번호를 한 번 더 입력해주세요." />
                <button>조회하기</button>
            </form>
        </div>
    );

}

export default PasswordSet;