function Sidebar () {

    return(
        <div className="bg-slate-600 w-48 h-96 float-left">
            사이드 바 전체 부분 입니다.
            <div className="border-2 border-slate-950">
                로그인 틀
                <div className="bg-slate-500 ">
                    로그인 부분 입니다.
                </div>
            </div>
            <div className="border-2 border-slate-950">
                글쓰기 버튼 틀
                <div className="bg-slate-400">
                    글쓰기 부분입니다.
                </div>
            </div>

            <div className="bg-slate-300 border-t-8 border-slate-50">
                카테고리 틀 입니다.
                <div className="border-2 border-slate-950">
                    카테고리 일상 틀 입니다.
                    <div className="bg-slate-400">
                        카테고리 일상 txt부분 입니다.
                    </div>
                </div>
                <div className="border-2 border-slate-950">
                    카테고리 공부 틀 입니다.
                    <div className="bg-slate-400">
                        카테고리 공부 txt부분 입니다.
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Sidebar;