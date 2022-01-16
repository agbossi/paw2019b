import React, {Component} from 'react';
import {Container, Nav, ButtonGroup, Button, Navbar} from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import PropTypes from 'prop-types';


class NavBar extends Component {

    propTypes = {
        items: PropTypes.array.isRequired,
        test: PropTypes.string.isRequired
    }

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <>
                <Navbar variant="dark" expand="lg" sticky="top" className="container-fluid" style={{backgroundColor: "CadetBlue"}}>
                    <Container style={{justifyContent: "flex-start"}}>
                        <Navbar.Brand href="/">DoctorSearch</Navbar.Brand>
                        {this.props.items.map((item) => {
                            return (
                                <Nav.Item class="ml-auto">
                                    <Nav.Link href={item.link} style={{color: "white", }}>{item.text}</Nav.Link>
                                </Nav.Item>
                            )
                        })}
                    </Container>
                    <ButtonGroup aria-label="Basic example">
                        <Button variant="light">EN</Button>
                        <Button variant="light">ES</Button>
                    </ButtonGroup>
                </Navbar>
            </>
        )
    }
}

export default NavBar