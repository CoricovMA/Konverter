import {React, useState} from 'react'
import '../Style/Search.css'
import {FormLabel, Button, Row, Col, FormControl, Form, Card} from "react-bootstrap";

function Search(props) {
    const [input, setInput] = useState("");

    const handleClick = () => {
        console.log(input)
    }

    return (
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
                                    console.log(e.target.value)
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
    )
}

export default Search;
