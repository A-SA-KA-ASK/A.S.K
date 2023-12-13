

function Sidebar () {

    return(
        <div className=" w-1/6 h-96 float-left m-7 p-3">
            <div>
                <div className="border-2 rounded-2xl text-center p-4 mb-4">
                    로그인
                </div>
                <div className="border-2 rounded-2xl text-center p-4 mb-4">
                    글쓰기
                </div>
            </div>

            <div className="border-t-8 border-r-slate-500">
                <div className="mt-4 mb-4">
                    <div className="border-b border-slate-300 py-3 font-bold">
                        일상
                    </div>
                    <div className="py-1">
                        txt 일상
                    </div>
                    <div className="">
                        이미지 일상 
                    </div>
                </div>
                <div>
                <div className="border-b border-slate-300 py-3 font-bold">
                        공부
                    </div>
                    <div className="py-1">
                        txt 공부
                    </div>
                    <div className="">
                        이미지 공부  
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Sidebar;