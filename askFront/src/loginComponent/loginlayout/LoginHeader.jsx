import { useNavigate } from "react-router-dom";

function Header() {

  const navigate = useNavigate();

  return (
    <div className="w-11/12 h-48 border-b border-slate-300 m-auto flex flex-col items-center"> 
      <div className="mx-0 my-auto">
        <h1 className="text-2xl font-bold"><button onClick={()=> navigate('/')}>헤더입니다.</button></h1>      
      </div>
    </div>
  );
}

export default Header;
