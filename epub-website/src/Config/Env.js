import axios from "axios"

export function apiGetList(bookID){
    return axios.get(`https://jpmtl.com/v2/chapter/${bookID}/list`)
}

export function apiGetChapter(chapterID){

}

export function apiGetBook(bookID){

}
