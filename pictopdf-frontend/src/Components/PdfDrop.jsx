import React, {useCallback, useState} from 'react'
import Dropzone from 'react-dropzone'
import  {Container, Row, Col, Button} from 'react-bootstrap'
import "../Style/drop.css"
import 'bootstrap/dist/css/bootstrap.min.css';
import {postFile, postPicture} from '../Config/Api'

function PdfDrop(props) {

    const [btn, setBtn] = useState();

    const onDrop = useCallback((acceptedFiles) =>{
        acceptedFiles.forEach((file) =>{
            const reader = new FileReader()

            reader.onabort = () => console.log("Aborting file reading.")
            reader.onerror = () => console.log("Error while reading file")
            reader.onload = () => {
                const binaryStr = reader.result;
                postFile(file)
                    .then((res) =>{
                        const url = window.URL.createObjectURL(new Blob([res.data]));
                        if(btn !== undefined){
                            setBtn(undefined);
                        }
                        setBtn(
                            <a
                                style={{
                                    backgroundColor: "#c6fc03",
                                    marginTop: "20px"
                                }}
                                className={"btn my-btn shadow"}
                                href={url}
                                download={`${file.name.split(".")[0]}.epub`}
                                data-aos={"zoom-in-up"}
                                data-aos-duration={1000}
                            >
                                <strong>Download</strong>
                            </a>
                        )
                        // link.click();
                })

            }

            reader.readAsArrayBuffer(file);
        })
    }, [btn])

    return (
        <div style={{
            paddingTop: "25%"
        }}>
            <Container style={{
                display: "flex",
                justifyContent: "center"
            }} >
                <Row>
                    <Col className={"shadow"} style={{
                        padding: "0",
                        borderRadius: "5px"
                    }}>
                        <Dropzone onDrop={onDrop} >
                            {({getRootProps, getInputProps}) => (
                                <section>
                                    <div {...getRootProps()} className="drop-box" >
                                        <input {...getInputProps()} />
                                        <Row>
                                            <Col>
                                                <Button variant="warning" className="input-button" >
                                                    Click here
                                                </Button>
                                            </Col>

                                        </Row>
                                        <Row>
                                            <Col>
                                                <p className="drop-text">
                                                    Or drag and drop a file
                                                </p>
                                            </Col>
                                        </Row>
                                    </div>
                                </section>
                            )}
                        </Dropzone>
                    </Col>
                </Row>

            </Container>
            <Container style={{
                display: "flex",
                justifyContent: "center"
            }}>
                <Row>
                    <Col>
                        {btn}
                    </Col>
                </Row>
            </Container>
        </div>

    )

}

export default PdfDrop;
