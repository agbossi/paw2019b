import React, {Component} from 'react';
import {Container, Nav, ButtonGroup, Button, Navbar} from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import PropTypes from 'prop-types';
import './NavBar.css'
import ApiCalls from "../api/apiCalls";
import {useLocation, useNavigate} from "react-router-dom";


function NavBar(props) {
    const navigate = useNavigate()
    const location = useLocation()

    const handleLogout = () => {
        ApiCalls.logout().then(() => {
            navigate("/");
            window.location.reload()
        })

    }

    return (
        <>
            <Navbar variant="dark" expand="lg" sticky="top" className="container-fluid nav-bar shadow-sm">
                <Container style={{justifyContent: "flex-start"}}>
                    <Navbar.Brand href="/">DoctorSearch</Navbar.Brand>
                    {props.items.map((item) => {
                        return (
                            <Nav.Item class="ml-auto">
                                <Nav.Link href={item.link} style={{color: "white"}}>{item.text}</Nav.Link>
                            </Nav.Item>
                        )
                    })}
                </Container>
                <Container style={{justifyContent: "flex-end"}}>
                    {localStorage.getItem('role') !== null ?
                        <Nav.Item class="ml-auto">
                            <Nav.Link onClick={() => handleLogout()} style={{color: "white"}}>Logout</Nav.Link>
                        </Nav.Item>
                        :
                        <Nav.Item class="ml-auto">
                            <Nav.Link href="/login" style={{color: "white"}}>Login</Nav.Link>
                        </Nav.Item>}
                    <ButtonGroup aria-label="Basic example">
                        <Button className="lang-buttons">EN</Button>
                        <Button className="lang-buttons">ES</Button>
                    </ButtonGroup>
                </Container>
            </Navbar>
        </>
    )

}

NavBar.propTypes = {
    items: PropTypes.array.isRequired,
    test: PropTypes.string.isRequired

}

export default NavBar