import React from "react";
import {Button, Card} from "react-bootstrap";
import '../Style/optionCard.css'

function OptionCard(props){
    return (
        <div className={"shadow-sm kon-card"}>
            <Card>
                <Card.Body>
                    <Card.Title className={"option-title"}>{props.title}</Card.Title>
                    <Card.Text>{props.cardText}</Card.Text>
                    <Button className={"option-card"} href={`/${props.type}`}>Try it out!</Button>
                </Card.Body>
            </Card>
        </div>
    )
}

export default OptionCard;