import Sidebar from "../layout/Sidebar";
import Content from "./Content";
import Header from "../layout/Header";
import Footer from "../layout/Footer";
import axios from "axios";

function MainSection() {

    // useState와 useEffect를 사용해서 Content부분에 테이블 내용을 보여주게 만들 예정

    // 로그인 전 사이트 접속시 보이는 메인 사이트 부분.

    const onSubmit = () => {
        axios.post('http://13.124.168.202:7777/api/v1/users/login')
        .then((res) => {
            console.log(res);
        }).catch((err) => {
           console.log(err);
        })
    }

    console.log(onSubmit);

    return(
        <div>
            <Header />
            <div className="h-screen w-11/12 m-auto mt-6 pt-6">
                <Sidebar />
                <Content />
            </div>
            <Footer />
        </div>
    )
}

export default MainSection;
  