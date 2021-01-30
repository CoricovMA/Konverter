import React, {useState} from "react";
import {Col} from "react-bootstrap";
import '../Style/display.css'

export default function FileDisplay(props){

    return (
        <Col
            className={"col-display"}

            lg={2}
            md={2}
            sm={2}
            xs={2}
        >
            <img
                data-aos={"fade-in"}
                className={"shadow pic-display"}
                style={{
                    marginTop:"20px"
                }}
                src={URL.createObjectURL(props.file)}
                alt={props.file.name}
            />
        </Col>
    )
}
