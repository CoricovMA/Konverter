import logo from './logo.svg';
import './App.css';
import React, {useState} from "react";
import {apiGetList} from "./Config/Env";

async function getListOfChapters(bookID){
  let arr = apiGetList(bookID).then((response) =>{
    if(response.status === 200 && response.data !== undefined){
      return response.data;
    }
  })
  return arr;
}

const getListOfChaptersFrom = async(bookID, indexFrom) =>{
   return apiGetList(bookID).then((res) =>{
    if(res.data !== undefined){
      return res.data.splice(indexFrom)
    }
  }, [])

}

function getListOfChaptersUpTo(bookID, indexTo){
  apiGetList(bookID).then((res) =>{
    if(res.data !== undefined){
      return res.data.splice(0, indexTo);
    }
  })
}

function getCustomListOfChapters(bookID, indexFrom, indexTo){
  apiGetList(bookID).then((res) =>{
    if(res.data !== undefined){
      return res.data.splice(indexFrom, indexTo);
    }
  })
}


function App() {
  const [a, setA] = useState([]);

  if(a.length === 0){
    getListOfChaptersFrom(295, 20).then((r) => {
      setA(r)
      console.log(r)
    })

  }

  return (
    <div>

    </div>
  );
}

export default App;
