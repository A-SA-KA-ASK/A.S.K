import { Route, Routes } from "react-router-dom";
import Footer from "./layout/Footer";
import Header from "./layout/Header";
import MainSection from "./component/MainSection";
import DailyT from "./component/dlcp/DailyT";
import Sidebar from "./layout/Sidebar";
import DailyImg from "./component/dlcp/DailyImg";
import StudyT from "./component/studycp/StudyT";
import StudyImg from "./component/studycp/StudyImg";
import Login from "./layout/login/Login";
import Write from "./component/Write";

function App() {

  // Tailwind CSS / react-icons / react-router-dom 으로 초기 진행 예정
  // 초기 틀인 헤더, 사이드바, 푸터는 고정으로 보여야 하기 때문에 이런 틀을 짰습니다.
  // 페이지 이동시에도 초기 틀은 고정으로 보여지게 됩니다.

  return (
    <>
      <Header />
        <div className="h-screen w-11/12 m-auto mt-6 pt-6">
          <Sidebar />
          <Routes>
            <Route path="/" element={<MainSection />} />
            <Route path="/dailyT" element={<DailyT/>} />
            <Route path="/dailyImg" element={<DailyImg />} />
            <Route path="/studyT" element={<StudyT />} />
            <Route path="/studyImg" element={<StudyImg />} />
            <Route path="/login" element={<Login />} exact />
            <Route path="/write" element={<Write />}/>
          </Routes>
        </div>
      <Footer />
    </>
  );
}

export default App;
