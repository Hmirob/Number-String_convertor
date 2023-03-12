import React from "react";
import "./App.css";
import SignIn from "./components/SignIn";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {Convertor} from "./components/Convertor";


function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/morda">
                    <Route path="login" element={<SignIn/>}/>
                    <Route path="" element={
                        <Convertor/>
                    }/>
                </Route>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
