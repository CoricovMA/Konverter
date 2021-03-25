import React, {useLayoutEffect, useState} from "react";
import {Button, Card} from "react-bootstrap";
import '../Style/optionCard.css'

function WindowSize(){
    const [size, setSize] = useState([0, 0])

   useLayoutEffect( () => {
        function updateSize() {
            setSize([window.innerWidth, window.innerHeight]);
        }

        window.addEventListener('resize', updateSize);
        updateSize();

        return () => window.removeEventListener('resize', updateSize)
    }, []);

    return size;
}

function OptionCard(props){
    const [width, height] = WindowSize();

    return (
        <div className={"shadow-sm kon-card"}>
            <Card className={"my-card"} sm={12} xs={12} md={12}>
                <Card.Body>
                    <Card.Title className={"option-title"}>{props.title}</Card.Title>

                    { (width > 650) ?
                            <Card.Text>{props.cardText}</Card.Text>
                         : null
                    }

                    <Button className={"option-card"} href={`/${props.type}`}>Try it out!</Button>
                </Card.Body>
            </Card>
        </div>
    )
}

export default OptionCard;