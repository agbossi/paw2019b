import React, {useEffect, useState} from 'react';
import {Container, Nav, ButtonGroup, Button, Navbar} from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import PropTypes from 'prop-types';
import './NavBar.css'
import ApiCalls from "../api/apiCalls";
import {useLocation, useNavigate} from "react-router-dom";
import { useTranslation } from "react-i18next";
import "../i18n/i18n";
import {changeLanguage} from "i18next";


function NavBar(props) {
    const navigate = useNavigate()
    const location = useLocation()
    const [items, setItems] = useState([]);
    const { t } = useTranslation();

    const userNavbarItems = [
        {
            link: '/favorites',
            text: "favourites"
        },
        {
            link: '/appointments',
            text: 'appointments'
        },
        {
            link: '/profile',
            text: 'profile'
        }
    ]

    const docNavBarItems = [
        {
            link: '/doctor/clinics',
            text: "clinics"
        },
        {
            link: '/doctor/appointments',
            text: 'appointments'
        }
    ]
    const getItems = () => {
        if (!props.isAuth()) return [];
        switch (localStorage.getItem('role')) {
            case "ROLE_ADMIN":
                return [];
            case "ROLE_DOCTOR":
                return docNavBarItems;
            case "ROLE_USER":
                return userNavbarItems;
        }
    }

    useEffect(() => {
        setItems(getItems())
    },[])

    const handleLogout = () => {
        ApiCalls.logout().then(() => {
            navigate("/");
            window.location.reload()
        })

    }

    const getRoleHome = () => {
        if (!props.isAuth()) return "/";
        switch (localStorage.getItem('role')) {
            case "ROLE_ADMIN":
                return '/admin';
            case "ROLE_DOCTOR":
                return '/doctor';
            case "ROLE_USER":
                return '/';
        }
    }

    return (
        <>
            <Navbar variant="dark" expand="lg" sticky="top" className="container-fluid nav-bar shadow-sm">
                <Container style={{justifyContent: "flex-start"}}>
                    <Navbar.Brand href={getRoleHome()}>DoctorSearch</Navbar.Brand>
                    {items.map((item) => {
                        return (
                            <Nav.Item class="ml-auto">
                                <Nav.Link href={item.link} style={{color: "white"}}>{t("NAVBAR." +item.text)}</Nav.Link>
                            </Nav.Item>
                        )
                    })}
                </Container>
                <Container style={{justifyContent: "flex-end"}}>
                    {localStorage.getItem('role') !== null ?
                        <Nav.Item class="ml-auto">
                            <Nav.Link onClick={() => handleLogout()} style={{color: "white"}}>{t('NAVBAR.logout')}</Nav.Link>
                        </Nav.Item>
                        :
                        <Nav.Item class="ml-auto">
                            <Nav.Link href="/login" style={{color: "white"}}>{t('NAVBAR.login')}</Nav.Link>
                        </Nav.Item>}
                    <ButtonGroup aria-label="Basic example">
                        <Button className="lang-buttons" onClick={() =>changeLanguage('en')}>EN</Button>
                        <Button className="lang-buttons" onClick={() =>changeLanguage('es')}>ES</Button>
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