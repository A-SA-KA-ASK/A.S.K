// // jwt 토큰을 쿠키에 저장하기 위해 생성한 파일.
// // 로그인 구현시에 jwt 인증을 하기 위해 토큰을 저장해야함

import { useEffect, useState } from "react";
import { Cookies } from "react-cookie";
import TestAxios from "./TestAxios";

// // Cookie파일에 set,get,remove 함수를 모아둠.

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

// // setCookie는 option이 따로 있는데 path, expires, httpyOnly등이 있다. 

function Cookie () {

  // // 쿠키 테스트 
  // const checkCookie = () => {
  //   // 방법 1
  //   const expires = new Date()
  //   setCookie('testCookie', true, {expires}) // set(name, value, { ...option }); 이 부분을 작성한 내용

  //   // 방법 2
  //   const time = 3600; //1시간
  //   const expires1 = new Date(Date.now() + time * 1000);
  //   setCookie('testCookie', true, {expires})
  //   setTimeout(() => {
  //     // 시간을 걸어서 사용이 가능하다.
  //   })
  // }
  
  // useEffect(() => {
  //   if(cookies.testCookie == 'true'){
  //     console.log("쿠키값을 이행함.");
  //   }
  // }, []);

  // console.log(cookies);

  const [user, setUser] = useState({ 
    id: "",
    password: "" 
  })

  /** Get User Login Info */
  const onChange = async (event) => {
    const { name, value } = event.target
    setUser({
      ...user,
      [name]: value,
    })
  }

  /** Get Login Response from Server */
  const clickBtnLogin = async () => {
    const config = {
      method: 'post',
      url: 'http://13.124.168.202:7777/api/v1/users/login',
      data: user,
    }
    // const gCk = await getCookie('accessToken')
    const { data } = await TestAxios(config)
    setCookie('accessToken', data.token)
  }

  /** Logout */
  const clickBtnLogout = async () => {
    removeCookie('accessToken')
  }

  return(
    <div>
      {/* <button onClick={checkCookie}>(버튼입니다)cookie저장하기</button> */}
      <input
        onChange={onChange}
        name="email"
        type="text"
        placeholder="Enter Ur Email"
      />
      <input
        onChange={onChange}
        name="pw"
        type="password"
        placeholder="Enter Ur pw"
      />
      <button onClick={clickBtnLogin}>LOGIN</button>
      <button onClick={clickBtnLogout}>LOGOUT</button>
    </div>
  );
}

export default Cookie;