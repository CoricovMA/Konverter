import React from "react";
import  {Nav, Button, NavDropdown, Form, Navbar, FormControl} from "react-bootstrap";

export default function Navbr(){
    return (
        <Navbar bg="light" expand="lg">
            <Navbar.Brand href="/">Konverter</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="mr-auto">
                    <Nav.Link href="#home">Home</Nav.Link>
                    <Nav.Link href="/pdf">PDF</Nav.Link>
                    <Nav.Link href="/epub">ePub</Nav.Link>
                </Nav>
            </Navbar.Collapse>
        </Navbar>
    )
}
