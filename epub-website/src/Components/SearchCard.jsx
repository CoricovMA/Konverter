import {React} from 'react'
import {Card, Col, CardImg} from 'react-bootstrap'

function SearchCard(props){

    console.log(props.card.title)
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
                  data-aos="fade-in"
                  data-aos-duration={props.time}

            >
                <CardImg src={props.card.cover}
                        className={"card-image"}
                >
                </CardImg>
                <Card.Body>
                    {props.card.title}
                </Card.Body>
            </Card>
        </Col>

    )


}

export default SearchCard;
