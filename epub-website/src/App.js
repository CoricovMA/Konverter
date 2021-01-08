import logo from './logo.svg';
import './App.css';
import React from "react";
import {apiGetList} from "./Config/Env";

function App() {

  apiGetList(295).then((response) => {
    console.log(response)
  })

  return (
    <div>

    </div>
  );
}

export default App;
