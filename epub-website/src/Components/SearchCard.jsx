import {React, useState} from 'react'
import {Card, Col, CardImg, Button} from 'react-bootstrap'

function SearchCard(props){

    const [downBtn, setDownBtn] = useState("");

    const handleCardClick = () =>{
        
    }

    console.log(props.card)


    return (
        <Col sm={4}
             md={4}
             lg={3}
             xs={6}
             style={{
                 marginBottom: "2%",
             }}

        >
            <Card className={"result-card"}

            >
                <CardImg src={props.card.cover}
                        className={"card-image"}
                >
                </CardImg>
                <Card.Body>
                    {props.card.title}
                </Card.Body>
                <Button onClick={handleCardClick}>
                    Get ebook
                </Button>
                {downBtn}
            </Card>
        </Col>

    )


}

export default SearchCard;
