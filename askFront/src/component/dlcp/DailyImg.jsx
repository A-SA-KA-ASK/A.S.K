function DlImg () {
    return(
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
    );
}

function DailyImg() {

    // useState와 useEffect를 활용해서 테이블 내용을 보여주기 위해 사용함.
    return(
        <DlImg />
    );
}

export default DailyImg;
