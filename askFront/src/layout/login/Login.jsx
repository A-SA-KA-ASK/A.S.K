import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Cookies } from "react-cookie";
import base64 from 'base-64';


const cookies = new Cookies();

export const setCookie = (name, value, option) => { // 쿠키 저장하는 함수 key, value, option 세 가지 파라미터를 받음
  return cookies.set(name, value, { ...option });
};

export const getCookie = (name) => { // 쿠키 가져오는 함수
  return cookies.get(name);
};

export const removeCookie = (name, option) => { // 쿠키 삭제하는 함수
  return cookies.remove(name, { ...option });
};

function Login() {

    // 로그인 부분 입니다. 
    
    // 로그인 버튼 클릭시 일치 하지 않는다라는 문구 출력
    // 로그인 이메일 형식 아니면 로그인 버튼 막기

    const [ep, setEP] = useState({ // 사용자 아이디, 비밀번호 axios로 가져오기 위해 사용.
        nickname: "",
        id: "",
        password: ""
    });

    const {nickname, id, password} = ep; // 비구조화 할당을 이용함.

    const onChange = (e) => {
        const {name, value} = e.target;
        setEP({
            ...ep,
            [name]:value
        })
    }

    // 서버에서 JWT토큰을 보내고 있어서 cookie를 사용하여 받아오려고함.
    const clickLogin = async () => {
        const config = await axios({
          id,
          password,
          nickname,
          method: 'post',
          url: 'http://13.124.168.202:7777/api/v1/users/login',
          data: ep, // useState부분에서 id값과 password값을 user로 받아옴
        }).catch((err) => {
            alert('로그인이 실패했습니다. 정보가 올바른지 다시 확인해주세요');
        })
        const { data } = config; // 총 내용을 data로 받아서 연결을 시킴.
        setCookie('accessToken', data);
    }

    // jwt토큰 디코딩 하는 부분
    const testGetCK = getCookie('accessToken'); // jwt를 가져옴

    let payload = testGetCK.substring(testGetCK.indexOf('.')+1,testGetCK.lastIndexOf('.'));  
    let dec = JSON.parse(base64.decode(payload)); 

    const onClickLogin = () => {
        clickLogin();
        navigate('/loginmain', {state:{dec:dec}})
    }


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
                            <label for="id" class="block mb-2 text-sm font-medium text-gray-900 ">이메일</label>
                            <input type="id" name="id" id="id" onChange={onChange} value={id} class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 " placeholder="name@company.com" required="" />
                        </div>
                        example@example.com
                        <div>
                            <label for="password" class="block mb-2 text-sm font-medium text-gray-900 ">비밀번호</label>
                            <input type="password" name="password" id="password" onChange={onChange} value={password}  placeholder="••••••••" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 " required="" />
                        </div>
                        exPassword1
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
                            <button type="submit" onClick={onClickLogin} class="w-full bg-blue-600 text-white focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center hover:bg-blue-500">
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