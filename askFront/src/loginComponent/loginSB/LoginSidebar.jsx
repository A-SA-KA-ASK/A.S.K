import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

function LoginSidebar ({users}) {

    const navigator = useNavigate();

    return(
        <div className=" w-1/6 h-96 float-left m-7 p-3">
            <div>
                <div className="border-2 rounded-2xl text-center p-4 mb-4">
                    안녕하세요. {users[0].nickname}님 
                </div>
                <div className="border-2 rounded-2xl text-center p-4 mb-4">
                    <button onClick={()=> navigator('/write')}>글쓰기</button>
                </div>
            </div>

            <div className="border-t-8 border-r-slate-500">
                <div className="mt-4 mb-4">
                    <div className="border-b border-slate-300 py-3 font-bold">
                        일상
                    </div>
                    <div className="py-1">
                        <button onClick={()=> navigator('/dailyT')}>txt 일상</button>
                    </div>
                    <div className="">
                        <button onClick={()=> navigator('/dailyImg')}>Img 일상</button>
                    </div>
                </div>
                <div>
                <div className="border-b border-slate-300 py-3 font-bold">
                        공부
                    </div>
                    <div className="py-1">
                        <button onClick={()=> navigator('/studyT')}>txt 공부</button>
                    </div>
                    <div className="">
                        <button onClick={()=> navigator('/studyImg')}>Img 공부</button>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default LoginSidebar;