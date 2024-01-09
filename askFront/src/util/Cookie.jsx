// jwt 토큰을 쿠키에 저장하기 위해 생성한 파일.
// 로그인 구현시에 jwt 인증을 하기 위해 토큰을 저장해야함

import { Cookies } from "react-cookie";

// Cookie파일에 set,get,remove 함수를 모아둠.

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

// setCookie는 option이 따로 있는데 path, expires, httpyOnly등이 있다. 