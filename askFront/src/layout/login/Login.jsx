import axios from "axios";
import { useEffect, useState } from "react";
import { Route, Routes, useNavigate } from "react-router-dom";

function Login() {

    // 로그인 부분 입니다. 
    
    // 로그인 버튼 클릭시 일치 하지 않는다라는 문구 출력
    // 로그인 이메일 형식 아니면 로그인 버튼 막기

    // const navigate = useNavigate();

    const [user, setUser] = useState([]); // data를 받아와서 보여줌.
    const [err, setErr] = useState(""); // 에러메세지 나타나게 함.
    const [ep, setEP] = useState({ // 사용자 아이디, 비밀번호 axios로 가져오기 위해 사용.
        nickname: "",
        email: "",
        password: ""
    });

    const {nickname, email, password} = ep; // 비구조화 할당을 이용함.

    const onChange = (e) => {
        const {name, value} = e.target;
        setEP({
            ...ep,
            [name]:value
        })
    }

    const onSubmit = () => {
        axios.get("/dummy/testLogin.json", {
            nickname,
            email,
            password
        }).then((res) => {
            setUser(res.data.user);
        }).catch((err) => {
            setErr(err.message);
        })
    }

    useEffect(() => {
        onSubmit();
    }, []);

    // 일반적인 페이지 이동
    const navigate = useNavigate();

    // <button onClick={()=> navigate('/')}>이동</button>

    const [emailErr, setEmailErr] = useState(); // 이메일 에러문구 나타내기
    const [pwErr, setPwErr] = useState(); // 비밀번호 에러문구 나타내기
 
    return(
        <div class="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0 bg-slate-100">
            <div href="#" class="flex items-center mb-6 text-2xl font-semibold text-gray-900 ">
                <button onClick={()=> navigate('/')}>A.S.K</button>    
            </div>
            <div class="w-full bg-white rounded-lg shadow-md md:mt-0 sm:max-w-md xl:p-0 ">
                <div class="p-6 space-y-4 md:space-y-6 sm:p-8">
                    <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl ">
                        로그인
                    </h1>
                        <div>
                            <label for="email" class="block mb-2 text-sm font-medium text-gray-900 ">이메일</label>
                            <input type="email" name="email" id="email" onChange={onChange} value={email} class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 " placeholder="name@company.com" required="" />
                        </div>
                        {err}
                        <div>
                            <label for="password" class="block mb-2 text-sm font-medium text-gray-900 ">비밀번호</label>
                            <input type="password" name="password" id="password" onChange={onChange} value={password}  placeholder="••••••••" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 " required="" />
                        </div>
                        {err}
                        <div class="flex items-center justify-between">
                            <div class="flex items-start">
                                <div class="flex items-center h-5">
                                    <input id="remember" aria-describedby="remember" type="checkbox" class="w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-primary-300 " required="" />
                                </div>
                                <div class="ml-3 text-sm">
                                    <label for="remember" class="text-gray-500 dark:text-gray-300">아이디 저장</label>
                                </div>
                            </div>
                            <button class="text-sm font-medium text-primary-600 hover:underline" onClick={()=> navigate('/forgotE')}>아이디 찾기</button>
                            <button class="text-sm font-medium text-primary-600 hover:underline" onClick={()=> navigate('/forgotP')}>비밀번호 찾기</button>
                        </div>
                            <button type="submit" onClick={() => navigate('/loginmain', {state:{user:user}})} class="w-full bg-blue-600 text-white focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center hover:bg-blue-500">
                                로그인
                            </button>
                        <p class="text-sm font-light text-gray-500 "> 회원이 아니시라고요? 
                            <button onClick={()=> navigate('/signup')} class="font-medium text-primary-600 hover:underline hover:text-orange-700 ml-2">회원가입하기</button>
                        </p>
                </div>
            </div>
        </div>
    );
}

export default Login;