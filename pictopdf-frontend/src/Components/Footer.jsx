import React from "react";
import '../Style/footer.css'
import {Github} from "react-bootstrap-icons";

export default function Footer(){

    return(
        <div className={"footer"}>
            <p style={{paddingTop:"10px"}}>
                Made by <a style={{color:"white"}} href={"https://www.linkedin.com/in/coricovacma/"}>Alexander Coricovac</a> @ 2020
                <span >
                    <a href={"https://github.com/CoricovMA"} style={{color: "white"}}>
                        <Github className={"github-icon"}/>
                    </a>
                </span>
            </p>
        </div>
    )

}