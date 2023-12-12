import { Route, Routes } from "react-router-dom";
import Footer from "./layout/Footer";
import Header from "./layout/Header";
import Section from "./layout/Section";

function App() {

  // Tailwind CSS / react-icons / react-router-dom 으로 초기 진행 예정

  return (
    <>
      <Header />
        <Routes>
          <Route path="/" element={<Section />} />
        </Routes>
      <Footer />
    </>
  );
}

export default App;
