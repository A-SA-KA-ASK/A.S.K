import Footer from "../Footer";
import Header from "../Header";

function Write() {

    // 글쓰기 부분 입니다.

    return(
        <div>
            <Header />
            <div className="h-screen w-10/12 m-auto mt-6 pt-6">
                <div className="flex flex-row border-b border-slate-800 ">
                    <h1 className="font-bold text-4xl mb-6">글 작성 페이지 입니다.</h1>
                    <div className="ml-auto">
                        <button>등록</button>
                    </div>
                </div>
                <div className="w-10/12 h-5/6 m-auto  my-6">
                    <div className="flex flex-col  ">
                        <input type="text" placeholder="제목을 입력해 주세요." maxLength={100} className="w-10/12 m-auto my-8 py-4 px-6 text-xl border-2 rounded-2xl" />
                        <textarea className="w-10/12 h-96 m-auto border-2 resize-none p-6 rounded-2xl" maxLength={700} placeholder="내용을 입력해 주세요"></textarea>
                    </div>
                </div>
            </div>
            <Footer />
        </div>

    );
}

export default Write;