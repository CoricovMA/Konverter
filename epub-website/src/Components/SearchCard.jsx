import {React} from 'react'
import {Card, Col, CardImg} from 'react-bootstrap'

function SearchCard(props){

    console.log(`inside card ${props.card.cover}`)
    return (
        <Col>
            <Card>
                <CardImg >

                </CardImg>
            </Card>
        </Col>

    )


}

export default SearchCard;
