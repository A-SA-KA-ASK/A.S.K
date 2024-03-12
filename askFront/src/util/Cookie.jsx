// // jwt 토큰을 쿠키에 저장하기 위해 생성한 파일.
// // 로그인 구현시에 jwt 인증을 하기 위해 토큰을 저장해야함

import axios from "axios";
import { useEffect, useState } from "react";
import { Cookies } from "react-cookie";
import base64 from 'base-64';

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

function Cookie () {

  const [user, setUser] = useState({ // 이부분서 id와 pw부분을 받음
    id: "",
    password: "",
    nickname: ""
  })

  const {nickname, id, password} = user;

  const onChange = async (e) => { // 입력받는 부분
    const { name, value } = e.target
    setUser({
      ...user,
      [name]: value,
    })
  }

  // let testGetCK = '';

  // 로그인
  const clickLogin = async () => {
    const config = await axios({
      id,
      password,
      nickname,
      method: 'post',
      url: 'http://13.124.168.202:7777/api/v1/users/login',
      data: user, // useState부분에서 id값과 password값을 user로 받아옴
    }).catch((err) => {
    })
    const { data } = config; // 총 내용을 data로 받아서 연결을 시킴.
    setCookie('accessToken', data);
    // testGetCK = getCookie('accessToken');
    // console.log(testGetCK);

  }

  let dec;

  // jwt토큰 디코딩 하는 부분
  if(getCookie('accessToken')){
      const testGetCK = getCookie('accessToken'); // jwt를 가져옴
      let payload = testGetCK.substring(testGetCK.indexOf('.')+1,testGetCK.lastIndexOf('.'));  
      dec = JSON.parse(base64.decode(payload)); 
  } 

  // 회원가입
  const clickLogout = async () => {
    removeCookie('accessToken')
  }

  // jwt 디코딩
  // let testGetCK = getCookie('accessToken');
  // let payload = testGetCK.substring(testGetCK.indexOf('.')+1,testGetCK.lastIndexOf('.'));  
  // let dec = JSON.parse(base64.decode(payload)); 
  // console.log(dec.id);
  // console.log(dec.nickname);

  // 토큰은 새로고침 및 사이트를 나갔다가 들어오면 사라진다. 
  // 토큰은 accessToken과  refreshToken이 존재 refresh를 사용하면 로그인을 지속적으로 유지 가능.

  return(
    <div>
      {/* {dec.nickname} */}
      <input
        onChange={onChange}
        value={id}
        name="id"
        type="text"
        placeholder="Email"
      />
      <input
        onChange={onChange}
        value={password}
        name="password"
        type="text"
        placeholder="Pw"
      />
      <button onClick={clickLogin}>LOGIN
      </button>
      <button onClick={clickLogout}>LOGOUT</button>
    </div>
  );
}

export default Cookie;