import axios from 'axios';
import {mainUrl} from '../Config/env'

export function postFiles( req, extra, files) {
    let formData = new FormData();

    files.forEach((file)=>{
        formData.append(`files`, file)
    })
    // formData.append("options", extra)

    return axios.post(`${mainUrl}/convert/${req}`,
        formData,
        {
            headers: {
                "Content-Type": "multipart/form-data; boundary=----------287032381131322",
                "Access-Control-Allow-Origin": "*"
            },
            responseType: "blob"
        })
}


