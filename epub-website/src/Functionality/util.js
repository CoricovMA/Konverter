import {apiGetList} from "../Config/Env";

function getListOfChapters(bookID){
    let arr = apiGetList(bookID).then((response) =>{
        if(response.status === 200 && response.data !== undefined){
            return response.data;
        }
    })
    return arr;
}

const getListOfChaptersFrom = async(bookID, indexFrom) =>{
    return apiGetList(bookID).then((res) =>{
        if(res.data !== undefined){
            return res.data.splice(indexFrom)
        }
    })

}

function getListOfChaptersUpTo(bookID, indexTo){
    apiGetList(bookID).then((res) =>{
        if(res.data !== undefined){
            return res.data.splice(0, indexTo);
        }
    })
}

function getCustomListOfChapters(bookID, indexFrom, indexTo){
    apiGetList(bookID).then((res) =>{
        if(res.data !== undefined){
            return res.data.splice(indexFrom, indexTo);
        }
    })
}

export {
    getListOfChapters,
    getListOfChaptersFrom,
    getListOfChaptersUpTo,
    getCustomListOfChapters
}
