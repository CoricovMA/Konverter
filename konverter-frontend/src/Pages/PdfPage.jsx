import React from "react";
import AnimatedMan from "../Components/AnimatedComponents/AnimatedMan";
import Drop from "../Components/Drop";
import {Row} from "react-bootstrap";

export default function PdfPage(){

    return (
        <div>
            <Drop req="pdf"/>
            <Row>
                <AnimatedMan/>
            </Row>
        </div>

        )
}

