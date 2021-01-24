import React from "react";
import {Route, Switch} from "react-router-dom"
import PdfPage from "../Pages/PdfPage"
import EpubPage from "../Pages/EpubPage"
import HomePage from "../Pages/HomePage";

export default function Router(){
    return (
        <div>
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
        </div>
    )
}
