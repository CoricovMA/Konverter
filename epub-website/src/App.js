import './Style/App.css';
import React, {useEffect, useState} from "react";
import Search from "./Components/Search";
import AOS from 'aos'
import 'aos/dist/aos.css';

function App() {

    useEffect(() => {
        AOS.init();
        AOS.refresh();
    }, []);

    return (
        <div>
            <Search className={"search-card"}/>
        </div>
    );
}

export default App;
