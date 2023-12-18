function Password () {
    return(
        <div>
            <h1>비밀번호 찾기</h1>
            <form >
                <input type="text" placeholder="닉네임을 입력해주세요." />
                <input type="email" placeholder="이메일을 입력해주세요." />
                <button>조회하기</button>
            </form>
        </div>
    );

}

export default Password;