import React from "react";
import {Card} from "react-bootstrap";
import '../Style/optionCard.css'

function OptionCard(props){
    return (
        <div className={"shadow-sm option-card"}>
            <Card>
                <Card.Body>
                    <Card.Title className={"option-title"}>{props.title}</Card.Title>
                    <Card.Text>{props.cardText}</Card.Text>
                </Card.Body>
            </Card>
        </div>
    )
}

export default OptionCard;