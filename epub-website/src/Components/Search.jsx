import {React, useState} from 'react'
import '../Style/Search.css'
import {FormLabel, Button, Row, Col, FormControl, Form, Card, CardDeck} from "react-bootstrap";
import {searchTitle} from "../Functionality/util";
import SearchCard from "./SearchCard";


function Search(props) {
    const [input, setInput] = useState("");
    const [cards, setCards] = useState([])

    const handleClick = () => {
        if(!input.includes("https") && isNaN(input)){
            searchTitle(input).then((res) =>{
                if(cards !== undefined && cards.length !== 0){
                    setCards([])
                }
                setCards(res.map((item, index) => <SearchCard card={item} key={index+1} />))
            })
        }
    }

    return (

        <div>
            <Form className={"search-card"}>
                <Card className={"shadow"}>
                    <Card.Body>
                        <Row>
                            <Col className={"text-left"}>
                                <FormLabel column="lg" style={{
                                    paddingLeft: "0"
                                }}>
                                    Novel Title
                                </FormLabel>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <FormControl type="text" placeholder="Novel name, url, or ID . . . ."
                                             onChange={e => {
                                                 setInput(e.target.value)
                                             }}
                                />
                            </Col>
                        </Row>
                    </Card.Body>
                </Card>
                <Button className={"search-button"} onClick={handleClick}>
                    Search
                </Button>
            </Form>
            <div>
                <CardDeck className={"search-container"}>
                    {cards}
                </CardDeck>
            </div>
        </div>


    )
}

export default Search;
