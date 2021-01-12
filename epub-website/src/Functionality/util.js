import {apiGetList, apiSearch} from "../Config/Env";

function searchTitle(toSearch) {
    return apiSearch(toSearch).then((res) =>{
        return res.data
    }).catch((err) =>{
        console.log(`There was an error: ${err}`)
    })
}


function getListOfChapters(bookID){
   return apiGetList(bookID).then((response) =>{
        if(response.status === 200 && response.data !== undefined){
            return response.data;
        }
    })
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
    getCustomListOfChapters,
    searchTitle
}
