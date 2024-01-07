import { useNavigate } from "react-router-dom";

function Sidebar () {

    const navigator = useNavigate();

    const onClick = () => {
        alert("권한이 없습니다 로그인을 해주세요.");
        navigator('/login');
    }

    return(
        <div className=" w-1/6 h-96 float-left m-7 p-3">
            <div>
                <div className="border-2 rounded-2xl text-center p-4 mb-4">
                    <button onClick={()=> navigator('/login')}>로그인</button>
                </div>
                <div className="border-2 rounded-2xl text-center p-4 mb-4">
                    <button onClick={onClick}>글쓰기</button>
                </div>
            </div>

            <div className="border-t-8 border-r-slate-500">
                <div className="mt-4 mb-4">
                    <div className="border-b border-slate-300 py-3 font-bold">
                        일상
                    </div>
                    <div className="py-1">
                        <button onClick={onClick}>txt 일상</button>
                    </div>
                    <div className="">
                        <button onClick={onClick}>Img 일상</button>
                    </div>
                </div>
                <div>
                <div className="border-b border-slate-300 py-3 font-bold">
                        공부
                    </div>
                    <div className="py-1">
                        <button onClick={onClick}>txt 공부</button>
                    </div>
                    <div className="">
                        <button onClick={onClick}>Img 공부</button>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Sidebar;