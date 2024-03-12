// 로그인 전 내용 불러오기.
import { Route, Routes } from "react-router-dom";
import MainSection from "./component/MainSection";
import DailyT from "./component/dlcp/DailyT";
import DailyImg from "./component/dlcp/DailyImg";
import StudyT from "./component/studycp/StudyT";
import StudyImg from "./component/studycp/StudyImg";
import Login from "./layout/login/Login";
import Write from "./layout/write/Write";
import SignUp from "./layout/sign up/SignUp";
import Email from "./layout/forgot/Email";
import Password from "./layout/forgot/Password";
import PasswordSet from "./layout/forgot/PasswordSet";
import LoginMainSection from "./loginComponent/LoginMainSection";
import TestMain from "./Test";
import Cookie from "./util/Cookie";

// import TestRouter from "./TestLogin";

// 로그인 후 내용 불러오기


function App() {

  // Tailwind CSS / react-icons / react-router-dom 으로 초기 진행 예정
  // 초기 틀인 헤더, 사이드바, 푸터는 고정으로 보여야 하기 때문에 이런 틀을 짰습니다.
  // 페이지 이동시에도 초기 틀은 고정으로 보여지게 됩니다.

  // 수정 초기 틀인 헤더, 사이드바, 푸터는 고정으로 했지만 로그인 화면은 새로운 페이지로 나타내기 위해
  // 컴포넌트 마다 사이드 헤더 푸터를 넣어서 고정 틀을 생성해 줌.

  // 로그인 전에는 글을 확인 할 수 없게 설정을 하기로 했으므로 
  // react-router-dom를 사용한 PrivateRouter를 이용하기로 했다.

  return (
    <>
      <Routes>
        {/* 로그인 전 화면 */}
        <Route path="/" element={<MainSection />} />
        <Route path="/dailyT" element={<DailyT/>} />
        <Route path="/dailyImg" element={<DailyImg />} />
        <Route path="/studyT" element={<StudyT />} />
        <Route path="/studyImg" element={<StudyImg />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/write" element={<Write />}/>
        <Route path="/forgotE" element={<Email />} />
        <Route path="/forgotP" element={<Password />} />
        <Route path="/forgotPS" element={<PasswordSet />} />
        {/* 로그인 후 화면 */}
        <Route path="/loginMain" element={<LoginMainSection />} />
      </Routes>
      {/* <TestRouter /> */}
      {/* <TestMain /> */}
      {/* <Cookie /> */}
    </>
  );
}

export default App;
