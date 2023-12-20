import { useNavigate } from "react-router-dom";

function Password () {

    // 조회를 해서 이메일과 닉네임이 일치한다면 비밀번호 재설정을 할 수 있게 이동 시켜 줌.

    const navigate = useNavigate();
    
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