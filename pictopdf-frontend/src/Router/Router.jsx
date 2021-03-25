import React from "react";
import {Route, Switch} from "react-router-dom"
import PdfPage from "../Pages/PdfPage"
import EpubPage from "../Pages/EpubPage"
import HomePage from "../Pages/HomePage";
import Navbr from "../Components/Nav";
import Footer from "../Components/Footer";

export default function Router(){
    return (
        <div className={"main-page"}>
            <Navbr/>
            <Switch>
                <Route path="/pdf">
                    <PdfPage/>
                </Route>
                <Route path="/epub">
                    <EpubPage />
                </Route>
                <Route exact path="/">
                    <HomePage/>
                </Route>
            </Switch>
            <Footer/>
        </div>
    )
}
