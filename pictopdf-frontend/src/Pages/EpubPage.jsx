import React from "react";
import Navbr from "../Components/Nav";
import Drop from "../Components/Drop"

export default function EpubPage(){

    return (
        <div>
            <Navbr/>
            <Drop req="epub" extra={{}}/>
        </div>
    )
}
