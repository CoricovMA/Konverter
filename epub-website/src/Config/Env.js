import axios from "axios"

export function apiGetList(bookID){
    return axios.get(`https://jpmtl.com/v2/chapter/${bookID}/list`)
}

export function apiGetChapter(chapterID){

}

export function apiGetBook(bookID){

}

export function apiSearch(toSearch){
    return axios.get(`https://jpmtl.com/v2/book/show/browse?query=${toSearch}&categories=&content_type=0&direction=0&page=1&limit=10&type=5&status=all&language=3&exclude_categories=`)
}
