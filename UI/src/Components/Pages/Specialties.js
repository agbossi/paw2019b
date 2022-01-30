import React, {Component, useEffect, useState} from 'react';
import {Button, Card, Container, Modal} from "react-bootstrap";
import '../CardContainer.css'
import SinglePropertyAddModal from "../Modals/SinglePropertyAddModal";
import {useNavigate} from "react-router-dom";
import SpecialtyCalls from "../../api/SpecialtyCalls";

function Specialties(props){
    const [specialties, setSpecialties] = useState([])
    const [page, setPage] = useState(0)
    const [maxPage, setMaxPage] = useState(0)
    const navigate = useNavigate()
    const [message, setMessage] = useState("")

    const fetchSpecialties = async (pag) => {
        const response = await SpecialtyCalls.getSpecialties(pag);
        if (response && response.ok) {
            setSpecialties(response.data);
            setMaxPage(response.headers.xMaxPage);
        }
    }

    useEffect(async () => {
        await fetchSpecialties(page)
    }, [])

    const deleteSpecialty = async (name) => {
        const response = await SpecialtyCalls.deleteSpecialty(name);
        if (response && response.ok) {
            await fetchSpecialties(page)
            setMessage("")
        }
        if (response.status === 404) {
            if (response.data === "specialty-not-found") {
                setMessage("No specialty found to delete")
            }
        }
        if (response.status === 409) {
            if (response.data === "doctors-dependency") {
                setMessage("Could not delete specialty: One or more doctors still have this specialty")
            }
        }
        if (response.status === 401) {
            localStorage.removeItem('token')
            localStorage.removeItem('role')
            navigate('/login')
        }
    }

    const handleAdd = async (newSpecialty) => {
        const response = await SpecialtyCalls.addSpecialty(newSpecialty);
        if (response && response.ok) {
            await fetchSpecialties(page)
            setMessage("")
        } else if (response.status === 401) {
            localStorage.removeItem('token')
            localStorage.removeItem('role')
            navigate('/login')
        } else if (response.status === 409) {
            if (response.data === "specialty-exists") {
                setMessage("Specialty already exists")
            }
        }
    }

    const nextPage = async () => {
        const newPage = page + 1
        setPage(newPage)
        setMessage("")
        await fetchSpecialties(newPage)
    }
    const prevPage = async () => {
        const newPage = page - 1
        setPage(newPage)
        setMessage("")
        await fetchSpecialties(newPage)
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
            {message && (
                <div className="form-group">
                    <div className="alert alert-danger" role="alert">
                        {message}
                    </div>
                </div>
            )}
            <Container>
                <div className="admin-info-container admin-clinic-prepaid-container">
                    {specialties.map(specialty => {
                        return (
                            <Card className="mb-3 shadow" style={{color: "#000", width: '20rem', height: '7rem'}}
                                  key={specialty.name}>
                                <Card.Body>
                                    <Card.Title>{specialty.name}</Card.Title>
                                </Card.Body>
                                <Button className="remove-button remove-button-color shadow-sm"
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