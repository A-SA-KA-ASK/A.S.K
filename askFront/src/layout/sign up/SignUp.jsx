import { useState } from "react";
import { useNavigate } from "react-router-dom";

function SignUp() {

    // 회원가입 부분 입니다.

    const navigate = useNavigate();
    const [agree, setAgree] = useState(false); // 약관동의 내용
    const [emailErr, setEmailErr] = useState(""); // 이메일 유효성 체크
    const [passWordErr, setPassWordErr] = useState(""); // 비밀번호 유효성 체크
    const [passWordCheck, setPassWordCheck] = useState(""); // 비밀번호 틀대로 했는지 체크 
    const [nickName, setNickName] = useState(""); // 닉네임 유효성 체크

    const emailRegex = /([\w-.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/; 
    const passwordRegex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
    const nicknameRegex = /^[가-힣a-zA-Z]+$/;

    const agreeCheck = (e) => { // 약관 동의
        setAgree(e.target.agree);
    }

    const onSubmit = (e) => { // form 전송
        e.preventDefault();
    }

    const passwordCheck = (passWordErr) => { // 이틀을 axios로 데이터를 받아와서 아래 코드처럼 사용 할 예정
        if(passWordErr.match(passwordRegex)===null) { 
          console.log('비밀번호 형식을 확인해주세요');
          return;
        }else{ // 맞을 경우 출력
          console.log('비밀번호 형식이 맞아요');
        }
      }
    
    // if(!emailRegex.test()) setEmailErr('올바른 이메일 형식이 아닙니다.');  // 이런 틀을 사용하려고 함.
    // else setEmailErr('');

    return(
        <div class="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0 bg-slate-100">
            <div href="#" class="flex items-center mb-6 text-2xl font-semibold text-gray-900 ">
                <button onClick={()=> navigate('/')}>A.S.K</button>    
            </div>
            <div class="w-full bg-white rounded-lg shadow-md md:mt-0 sm:max-w-md xl:p-0" >
                <div class="p-6 space-y-4 md:space-y-6 sm:p-8">
                    <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl ">
                        회원가입
                    </h1>
                    <form class="space-y-4 md:space-y-6" onSubmit={onSubmit}>
                        <div>
                            <label for="email" class="block mb-2 text-sm font-medium text-gray-900 ">이메일</label>
                            <div className="flex flex-row">
                                <input type="email" name="email" id="email" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-3/4 p-2.5 " placeholder="name@company.com" required="" />
                                <button className="m-auto">중복 체크</button>
                            </div>
                        </div>
                        <div>
                            <label for="text" class="block mb-2 text-sm font-medium text-gray-900 ">닉네임</label>
                            <div className="flex flex-row">
                                <input type="text" name="text" id="text" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-3/4 p-2.5 " placeholder="닉네임을 입력해주세요." required="" />
                                <button className="m-auto">중복 체크</button>
                            </div>
                        </div>
                        <div>
                            <label for="password" class="block mb-2 text-sm font-medium text-gray-900 ">비밀번호</label>
                            <input type="password" name="password" id="password" placeholder="비밀번호(숫자+영어+특수문자)포함 8자리 이상 " class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 " required="" />
                        </div>
                        <div>
                            <label for="password" class="block mb-2 text-sm font-medium text-gray-900 ">비밀번호 확인</label>
                            <input type="password" name="password" id="password" placeholder="비밀번호 재입력" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 " required="" />
                        </div>
                        <div class="flex items-center justify-between">
                            <div class="flex items-start">
                                <div class="flex items-center h-5">
                                    <input id="remember" aria-describedby="remember" type="checkbox" class="w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-primary-300 " required="" />
                                </div>
                                <div class="ml-3 text-sm">
                                    <label for="remember" class="text-gray-500 dark:text-gray-300" onChange={agreeCheck}>약관의 동의합니다.</label>
                                </div>
                            </div>
                        </div>
                        <button type="submit" class="w-full bg-blue-600 text-white focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center hover:bg-blue-500">회원가입</button>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default SignUp;