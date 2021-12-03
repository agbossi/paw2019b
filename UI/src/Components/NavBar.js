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
                <Navbar bg="dark" variant="dark" expand="lg" sticky="top">
                    <Container>
                        <Navbar.Brand href="/">DoctorSearch</Navbar.Brand>
                        {this.props.items.map((item) => {
                            return (
                                <Nav.Item>
                                    <Nav.Link href={item.link}>{item.text}</Nav.Link>
                                </Nav.Item>
                            )
                        })}
                    </Container>
                    <ButtonGroup aria-label="Basic example">
                        <Button variant="secondary">EN</Button>
                        <Button variant="secondary">ES</Button>
                    </ButtonGroup>
                </Navbar>
            </>
        )
    }
}

export default NavBar