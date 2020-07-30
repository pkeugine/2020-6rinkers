import React, { useState } from "react";
import "../../css/common/header.css";
import Nav from "./Nav";
import MobileNav from "./MoblieNav";

const Header = () => {
  const [slider, setSlider] = useState(false);

  const toggleSlider = () => {
    setSlider(!slider);
  };

  return (
    <div className="headerContainer">
      <Nav />
      {slider && <div className="cover" onClick={toggleSlider} />}
      <MobileNav toggleSlider={toggleSlider} slider={slider} />
    </div>
  );
};

export default Header;