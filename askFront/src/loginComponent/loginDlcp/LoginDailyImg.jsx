import Footer from "../../layout/Footer";
import Header from "../../layout/Header";
import Sidebar from "../../layout/Sidebar";

function LoginDlImg () {

    // 카테고리 일상 Img일상 부분 입니다.

    return(
        <div>
            <Header />
            <div className="h-screen w-11/12 m-auto mt-6 pt-6">
            <Sidebar />
                <div className=" h-96 float-left w-4/6 m-10 p-3 ">
                    <div>
                        <h1 className="font-bold text-4xl">일상 이미지</h1>
                    </div>
                    <div className="mt-8">
                        <div>제목</div>
                        <div>
                            <p>작성자</p>
                            <p>작성일</p>
                        </div>
                    </div>
                </div>
            </div>
            <Footer />
        </div>
    );
}

function LoginDailyImg() {

    // useState와 useEffect를 활용해서 테이블 내용을 보여주기 위해 사용함.
    return(
        <LoginDlImg />
    );
}

export default LoginDailyImg;
