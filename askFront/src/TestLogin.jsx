import axios from "axios";
import { useEffect, useState } from "react";
import { Route, Router, Routes } from "react-router-dom";

function TestBfLogin({setIsLogin}) {

    const [login, setLogin] = useState({
        email: "",
        password: ""
    });

    const {email, password} = login;

    const onChange = (e) => {
        const {name,value} = e.target;
        setLogin({
            ...login,
            [name]: value
        })
    }

    const [user, setUser] = useState([]);

        const onSubmit = (e) => {
            e.preventDefault();
            setIsLogin(true);
            axios.get("https://8141bf93-5d2c-4c21-8e3e-247f0a3aed60.mock.pstmn.io", {
                email,
                password
            }).then((res) => {
                setUser(res.data);
                alert("로그인이 되었습니다.")
                document.location.href = '/main'
            }).catch((err) => {
                console.log(err);
            })
        }


    return (
        <div>
            현재는 로그인 하는 부분 입니다.
            <div>
                <input type="email" placeholder="이메일" onChange={onChange} name="email" value={email}/>
                <input type="password" placeholder="비밀번호" onChange={onChange} name="password" value={password}/>
                <button onClick={onSubmit}>로그인</button>
            </div>
        </div>
    );
}

function TestLogin() {
    return(
        <div>
            현재는 로그인이 된 부분 입니다.

            로그인이 되었습니다.
            
        </div>
    );
}

function TestRouter() {
    
    // 로그인 화면 구현 및 api 테스트하기 위해 코드 작성
    // react-router-dom을 사용해서 로그인 화면을 구현해보려고 함.
    
    const [isLogin, setIsLogin] = useState(false);

    return(
        <div>
            안녕하세요 메인 페이지입니다.
            {
                isLogin ? <TestLogin /> : <TestBfLogin setIsLogin={setIsLogin} />
            }
            
            {/* <Routes>
                {
                    isLogin ? (
                        <>
                            <Route path="/main" element={<TestLogin />} />
                        </>
                    ) : (
                    <>
                        <Route path="/" element={<TestBfLogin setIsLogin={setIsLogin} />} />
                    </>
                    )
                } */}
                {/* <Route path="/" element={<TestBfLogin setIsLogin={setIsLogin} />} />
                <Route path="/main" element={<TestLogin />} /> */}
            {/* </Routes> */}
        </div>
    )
}

export default TestRouter;
  