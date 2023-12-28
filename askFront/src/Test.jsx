import axios from "axios";
import { useEffect, useState } from "react";

function TestBfLogin() {
    return <div>로그인 전 화면 입니다.</div>
}

function TestLogin({user}) {
    return(
        <div>
            {
                user.map((a) => {
                    return(
                        <div>
                            안녕하세요 {a.name}님, {a.age}세 환영합니다.
                        </div>
                    );
                })
            }
        </div>
    );
}

function TestMain() {
    
    const [isLogin, setIsLogin] = useState(false);
    const [user, setUser] = useState([]);

    useEffect(() => {
        axios.get("https://dbe21154-91df-4b7c-ba1f-e37e501adece.mock.pstmn.io")
        .then((result)=> {
            setUser(result.data);
        })
        .catch((err) => {
            console.log(err);
        })
    }, [user]);

    return(
        <div>
            <button onClick={()=>setIsLogin(true)}>클릭</button>
            {
                isLogin ? <TestLogin user={user}/> : <TestBfLogin />
            }
        </div>
    )
}

export default TestMain;
  