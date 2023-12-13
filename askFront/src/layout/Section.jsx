import Content from "../component/Content";
import Sidebar from "../component/Sidebar";

function Section() {

    // useState와 useEffect를 사용해서 Content부분에 테이블 내용을 보여주게 만들 예정

    // Section부분에서 component폴더에 있는 컴포넌트들을 보여줌.

    return(
        <div className="h-screen w-11/12 m-auto mt-6 pt-6">
            <Sidebar />
            <Content />
        </div>
    )
}

export default Section;
  