import React, {Component, useEffect, useState} from 'react';
import {Button, Card, Container, Modal} from "react-bootstrap";
import '../CardContainer.css'
import SinglePropertyAddModal from "../Modals/SinglePropertyAddModal";
import ApiCalls from "../../api/apiCalls";
import {useNavigate} from "react-router-dom";

function Specialties(props){
    const [specialties, setSpecialties] = useState([])
    const [page, setPage] = useState(0)
    const [maxPage, setMaxPage] = useState(0)
    const navigate = useNavigate()

    const fetchSpecialties = async () => {
        const response = await ApiCalls.getSpecialties(page);
        if (response && response.ok) {
            setSpecialties(response.data);
            setMaxPage(response.headers.xMaxPage);
        }
    }

    useEffect(async () => {
        await fetchSpecialties()
    }, [])

    const deleteSpecialty = (name) => {
        setSpecialties(specialties.filter(specialty => specialty !== name));
    }

    const handleAdd = (newSpecialty) => {
        setSpecialties([...specialties, newSpecialty]);
    }

    const nextPage = async () => {
        setPage(page + 1)
        await fetchSpecialties()
    }
    const prevPage = async () => {
        setPage(page - 1)
        await fetchSpecialties()
    }

    const renderPrevButton = () => {
        if (page !== 0) {
            return <Button className="remove-button doc-button-color shadow-sm"
                           onClick={() => prevPage()}>Prev</Button>
        }
    }

    const renderNextButton = () => {
        if (page < maxPage) {
            return <Button className="remove-button doc-button-color shadow-sm"
                    onClick={() => nextPage()}>Next</Button>
        }
    }

    return (
        <div className="background">
            <SinglePropertyAddModal handleAdd={handleAdd} property="Specialty"/>
            <Container>
                <div className="admin-info-container">
                    {specialties.map(specialty => {
                        return (
                            <Card className="mb-3 shadow" style={{color: "#000", width: '20rem', height: '7rem'}} key={specialty.name}>
                                <Card.Body>
                                    <Card.Title>{specialty.name}</Card.Title>
                                </Card.Body>
                                <Button className="remove-button doc-button-color shadow-sm"
                                        onClick={() => deleteSpecialty(specialty.name)}>
                                    Delete
                                </Button>
                            </Card>
                        )
                    })}
                </div>
            </Container>
            {renderPrevButton()}
            {renderNextButton()}
        </div>
    )

}

export default Specialties