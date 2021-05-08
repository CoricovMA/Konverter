import React, {useEffect} from "react";
import {Col, Container, Row} from "react-bootstrap";
import OptionCard from "../Components/OptionCard";
import "../Style/optionCard.css"
import AnimatedWindow from "../Components/AnimatedComponents/AnimatedWiondow";
import axios from "axios";

export default function HomePage(){

    return <div>
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
                            className={"h-auto"}
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

