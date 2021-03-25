import React from "react";
import  {Nav, Navbar} from "react-bootstrap";
import '../Style/nav.css'

export default function Navbr(){
    return (
        <Navbar
            expand="md"
            className={"nav"}
            variant={"dark"}
        >
            <Navbar.Brand href="/" id={"title"}>Konverter</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="mr-auto">
                    <Nav.Link href="/" className={"nav-item"}>Home</Nav.Link>
                    <Nav.Link href="/pdf" className={"nav-item"}>PDF</Nav.Link>
                    <Nav.Link href="/epub" className={"nav-item"}>ePub</Nav.Link>
                </Nav>
            </Navbar.Collapse>
        </Navbar>
    )
}
