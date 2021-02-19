import React from "react";
import Navbr from "../Components/Nav";
import {Col, Container, Row} from "react-bootstrap";
import OptionCard from "../Components/OptionCard";

export default function HomePage(){

    return <div>
            <Navbr/>
            <Container>
                <Row>
                    <Col>
                        <OptionCard
                            title={"Picture to Pdf"}
                            cardText={"This app provides picture (png, jpb, etc.) to pdf functionality." +
                            " It compiles given pictures into one pdf."}
                        />
                    </Col>
                    <Col>
                        <OptionCard
                            title={"Text to Ebook"}
                            cardText={"This app provides text (.txt) to ebook (.epub) functionality. It converts a text file" +
                            " to a somewhat pretty ebook file."}
                        />
                    </Col>
                </Row>
            </Container>
    </div>
}

