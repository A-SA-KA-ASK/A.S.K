function Email () {

    // 알림창으로 닉네임이 있을경우 이메일을 알려줌
    
    return(
        <div>
            <h1>아이디 찾기</h1>
            <form >
                <input type="text" placeholder="닉네임을 입력해주세요." />
                <button>조회하기</button>
            </form>
        </div>
    );

}

export default Email;