import React, {useCallback, useState} from 'react'
import Dropzone from 'react-dropzone'
import {Container, Row, Col, Button} from 'react-bootstrap'
import "../Style/drop.css"
import 'bootstrap/dist/css/bootstrap.min.css';
import {postFiles} from '../Config/Api'
import FileDisplay from "./FileDisplay";

function Drop(props) {

    const [btn, setBtn] = useState();
    const [fileDisplay, setFileDisplay] = useState([])
    const [files, setFiles] = useState([])
    let fileArr;
    fileArr = [];


    const onDrop = useCallback((acceptedFiles) =>{
        acceptedFiles.forEach((file) =>{

            const reader = new FileReader();
            reader.onabort = () => {

            }

            reader.onerror = () =>{

            }

            reader.onload = () =>{
                console.log(file.type.split("/")[0])

                switch (props.req) {
                    case "pdf":
                        if(file.type.split("/")[0] !== "image"){
                            alert("Wrong file type.")
                        }else{

                            setFileDisplay(fileDisplay => fileDisplay.concat(<FileDisplay
                                file={file}
                                key={fileArr.length+1}
                                duration={fileArr.length*1000}
                            />))

                        }
                        break;
                    case "epub":
                        console.log("yep");
                        break;
                    default:
                        console.log(`Got new file. ${files}`)
                        break;
                }

                files.push(file)
                setFiles(files)
            }
            reader.readAsArrayBuffer(file)

        })

    }, [])


    const handleReset = () =>{
        setBtn()
        setFiles([])
        setFileDisplay([])
    }

    const onClickConvert = () =>{
        if(files.length === 0){
            return;
        }
        postFiles(props.req, props.extra, files)
            .then((res) =>{
                const url = window.URL.createObjectURL(new Blob([res.data]))
                if(btn !== undefined){
                    setBtn(undefined)
                }

                setBtn(
                    <a
                        className={`btn shadow =`}
                        id={`${props.req}-drop`}
                        href={url}
                        download={`${files[0].name.split(".")[0]}.${props.req}`}
                        data-aos={"fade-right"}
                        data-aos-duration={1000}
                    >
                        <strong>Download</strong>
                    </a>
                )

            }).catch((err) =>{
                console.log(err)
            })
    }

    return (
        <div
            data-aos={"fade-down"}
            data-aos-duration={"1500"}
            style={{
            paddingTop: "2%"
        }}>
            <div className={"file-display"}>
                <Row>
                    {fileDisplay}
                </Row>
            </div>
            <Container style={{
                display: "flex",
                justifyContent: "center"
            }} >

                <Row>
                    <Col className={"shadow"} style={{
                        padding: "0",
                        borderRadius: "5px"
                    }}>
                        <Dropzone onDrop={onDrop} multiple={true}>
                            {({getRootProps, getInputProps}) => (
                                <section>
                                    <div {...getRootProps()} className="drop-box" >
                                        <input {...getInputProps()} />
                                        <Row>
                                            <Col>
                                                <Button variant="light" className="input-button" >
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
                justifyContent: "center",
                marginBottom: "5%"
            }}>
                <Row>
                    <Col>
                        {btn ? btn : <Button
                            onClick={onClickConvert}
                            id={"convert-button"}
                        >
                            Convert
                        </Button>}
                    </Col>
                    <Col>
                        {
                            btn ? <Button
                                          className={"btn shadow"}
                                          style={{
                                              marginTop:"20px",
                                              backgroundColor: "black",

                                          }}
                                          data-aos={"fade-left"}
                                          onClick={handleReset}
                            >
                                Reset
                            </Button> : null
                        }
                    </Col>
                </Row>

            </Container>

        </div>

    )

}

export default Drop;
