import React, {useState} from "react";
import {Col} from "react-bootstrap";


export default function FileDisplay(props){
    // const [files, setFiles] = useState([])
    // const [imgs, setImgs] = useState([])
    //
    // const createImages = () =>{
    //     let arr = []
    //     let count = 1;
    //     files.forEach(img =>{
    //         console.log(`for each${img}`)
    //         arr.push(
    //             <img
    //                 src={img? URL.createObjectURL(img) : null}
    //                 alt={img? img.name : null}
    //                 key={count}
    //             />
    //         )
    //         count++;
    //     })
    //     setImgs(arr);
    // }
    //
    // const handleUpload = (e) =>{
    //     console.log(e)
    //     files.push(e.target.files[0])
    //     setFiles(files);
    //     createImages();
    // }
    //
    // return (
    //     <div>
    //         {imgs}
    //         <input type="file" onChange={handleUpload}/>
    //     </div>
    // )
    return (
        <Col
            style={{
                borderRadius:"5px",
                height: "130px",
                width: "180px",
            }}
            lg={3}
            md={4}
            sm={4}
            xs={6}
        >
            <img
                className={"shadow"}
                style={{
                    marginTop:"20px"
                }}
                src={URL.createObjectURL(props.file)}
                alt={props.file.name}
            />
        </Col>
    )
}
