import Content from "../component/Content";
import Sidebar from "../component/Sidebar";

function Section() {
    return(
        <div className="h-screen w-11/12  m-auto">
            <h1>섹션입니다.</h1>
            <Sidebar />
            <Content />
        </div>
    )
}

export default Section;
  