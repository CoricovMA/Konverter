import React from "react";
import Navbr from "../Components/Nav";
import {Col, Container, Row} from "react-bootstrap";
import OptionCard from "../Components/OptionCard";
import "../Style/optionCard.css"
import AnimatedMan from "../Components/AnimatedComponents/AnimatedMan.jsx";
import AnimatedWoman from "../Components/AnimatedComponents/AnimatedWoman";
import SunAnimation from "../Components/AnimatedComponents/SunAnimation";
import AnimatedWindow from "../Components/AnimatedComponents/AnimatedWiondow";

export default function HomePage(){
    return <div>
            <Navbr/>
            <Container className={"d-flex-row"}>
                <Row>
                    <AnimatedWindow/>
                </Row>
                <Row>
                    <Col>
                        <OptionCard
                            title={"Picture to Pdf"}
                            cardText={"This app provides picture (png, jpb, etc.) to pdf functionality." +
                            " It compiles given pictures into one pdf."}
                            type={"pdf"}
                        />
                    </Col>
                    <Col>
                        <OptionCard
                            title={"Text to Ebook"}
                            cardText={"This app provides text (.txt) to ebook (.epub) functionality. It converts a text file" +
                            " to a somewhat pretty ebook file."}
                            type={"epub"}
                        />
                    </Col>
                </Row>
            </Container>
    </div>
}

