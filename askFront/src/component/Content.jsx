function Content () {

    return(
        <div className="bg-orange-700 h-96">
            콘텐츠 전체 부분 입니다.
            <div className="border-2 border-slate-950">
                1개의 카테고리 틀 입니다. Table로 제작을 하려고 합니다.
                <div className="bg-slate-400">
                    카테고리 일상 부분 입니다.
                </div>
                <div className="bg-slate-300">
                    카테고리 txt 부분 입니다.
                </div>
            </div>

            <div className="border-2 border-slate-950">
                1개의 카테고리 틀 입니다. Table로 제작을 하려고 합니다.
                <div className="bg-slate-400">
                    카테고리 공부 부분 입니다.
                </div>
                <div className="bg-slate-300">
                    카테고리 txt 부분 입니다.
                </div>
            </div>
        </div>
    );
}

export default Content;