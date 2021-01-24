import React, {useCallback, useState} from 'react'
import Dropzone from 'react-dropzone'
import {Container, Row, Col, Button} from 'react-bootstrap'
import "../Style/drop.css"
import 'bootstrap/dist/css/bootstrap.min.css';
import {postFile, postFiles} from '../Config/Api'
import FileDisplay from "./FileDisplay";
import {useMonitorOutput} from "react-dnd/lib/hooks/internal/useMonitorOutput";

function Drop(props) {

    const [btn, setBtn] = useState();
    const [fileDisplay, setFileDisplay] = useState([])
    const [files, setFiles] = useState([])
    let fileArr = []


    const onDrop = useCallback((acceptedFiles) =>{
        acceptedFiles.forEach((file) =>{

            const reader = new FileReader();
            reader.onabort = () => {

            }

            reader.onerror = () =>{

            }

            reader.onload = () =>{
                fileArr.push(<FileDisplay
                    file={file}
                    key={fileArr.length+1}
                    duration={fileArr.length*1000}
                />)
                setFileDisplay(fileDisplay)
                setFileDisplay(fileArr)
                files.push(file)
                setFiles(files)
            }
            reader.readAsArrayBuffer(file)

        })
        // acceptedFiles.forEach((file) =>{
        //     const reader = new FileReader()
        //
        //     console.log(props)
        //
        //     reader.onabort = () => console.log("Aborting file reading.")
        //     reader.onerror = () => console.log("Error while reading file")
        //     reader.onload = () => {
        //         postFile(file, props.req, props.extra)
        //             .then((res) =>{
        //                 const url = window.URL.createObjectURL(new Blob([res.data]));
        //                 if(btn !== undefined){
        //                     setBtn(undefined);
        //                 }
        //                 setBtn(
        //                     <a
        //                         className={`btn shadow `}
        //                         id={`${props.req}-drop`}
        //                         href={url}
        //                         download={`${file.name.split(".")[0]}.${props.req}`}
        //                         data-aos={"zoom-in-up"}
        //                         data-aos-duration={1000}
        //                     >
        //                         <strong>Download</strong>
        //                     </a>
        //                 )
        //             })
        //
        //     }
        //
        //     reader.readAsArrayBuffer(file);
        // })
    }, [])

    const onClickConvert = () =>{
        postFiles(props.req, props.extra, files)
            .then((res) =>{
                const url = window.URL.createObjectURL(new Blob([res.data]))
                if(btn !== undefined){
                    setBtn(undefined)
                }

                setBtn(
                    <a
                        className={`btn shadow `}
                        id={`${props.req}-drop`}
                        href={url}
                        download={`${files[0].name.split(".")[0]}.${props.req}`}
                        data-aos={"zoom-in-up"}
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
        <div style={{
            paddingTop: "5%%"
        }}>
            <Container style={{
                display: "flex",
                justifyContent: "center",
                marginBottom: "20px"
            }}>
                <Row>
                    {fileDisplay}
                </Row>
            </Container>
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
                justifyContent: "center"
            }}>
                <Row>
                    {btn? btn : <Button
                        variant={"success"}
                        onClick={onClickConvert}
                        style={{
                            marginTop: "20px"
                        }}
                    >
                        Convert
                    </Button>}
                </Row>
                <Row>
                    <Col>
                        {btn}
                    </Col>
                </Row>
            </Container>
        </div>

    )

}

export default Drop;
