import React from "react";
import Drop from "../Components/Drop"
import {Row} from "react-bootstrap";
import AnimatedMan from "../Components/AnimatedComponents/AnimatedMan";

export default function EpubPage(){

    return (
        <div>
            <Drop req="epub" extra={{}}/>
            <Row>
                <AnimatedMan/>
            </Row>
        </div>
    )
}