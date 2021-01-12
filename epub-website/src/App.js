import './Style/App.css';
import React, {useState} from "react";
import Search from "./Components/Search";


function App() {
  return (
      <div>
        <Search className={"search-card"}/>
      </div>
  );
}

export default App;
