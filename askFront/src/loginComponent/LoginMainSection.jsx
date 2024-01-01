import Header from "../layout/Header";
import Footer from "../layout/Footer";
import LoginSidebar from "./loginSB/LoginSidebar";
import LoginContent from "./LoginContent";
import { useLocation } from "react-router-dom";

function LoginMainSection() {

    // useState와 useEffect를 사용해서 Content부분에 테이블 내용을 보여주게 만들 예정

    // 로그인시 보이는 메인 사이트 부분. 
    // App부분에서 Login인 부분 따로 설정해놈.

    const location = useLocation();
    const propsUser = location.state.user;

    return(
        <div>
            <Header />
            <div className="h-screen w-11/12 m-auto mt-6 pt-6">
                <LoginSidebar propsUser={propsUser}/>
                <LoginContent />
            </div>
            <Footer />
        </div>
    )
}

export default LoginMainSection;
  