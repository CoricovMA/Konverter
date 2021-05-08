import React, {useCallback} from "react";
import {postPicture} from "../Config/Api";

const onDrop = useCallback((acceptedFiles) =>{
        acceptedFiles.forEach((file) =>{
            const reader = new FileReader()

            reader.onabort = () => console.log("Aborting file reading.")
            reader.onerror = () => console.log("Error while reading file")
            reader.onload = () => {
                const binaryStr = reader.result;
                postPicture(file, binaryStr.byteLength)
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
                                className={"btn shadow"}
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
    }, [])

