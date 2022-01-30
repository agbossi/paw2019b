import React, {Component, useEffect, useState} from 'react';
import {Button, Card, Container, Modal} from "react-bootstrap";
import '../CardContainer.css'
import SinglePropertyAddModal from "../Modals/SinglePropertyAddModal";
import {useNavigate} from "react-router-dom";
import LocationCalls from "../../api/LocationCalls";
import PrepaidCalls from "../../api/PrepaidCalls";
import SpecialtyCalls from "../../api/SpecialtyCalls";

function Prepaids(props) {
    const [prepaids, setPrepaids] = useState([]);
    const [page, setPage] = useState(0)
    const [maxPage, setMaxPage] = useState(0)
    const navigate = useNavigate()
    const [message, setMessage] = useState("")

    const fetchPrepaids = async (pag) => {
        const response = await PrepaidCalls.getPrepaids(pag);
        if (response && response.ok){
            setPrepaids(response.data)
            setMaxPage(response.headers.xMaxPage)
        }
    }

    useEffect(async () => {
        await fetchPrepaids(page)
    }, [])

    const deletePrepaids = async (name) => {
        const response = await PrepaidCalls.deletePrepaid(name);
        if (response && response.ok) {
            await fetchPrepaids(page)
            setMessage("")
        }
        if (response.status === 404) {
            if (response.data === "prepaid-not-found") {
                setMessage("No prepaid found to delete")
            }
        }
        if (response.status === 401) {
            localStorage.removeItem('token')
            localStorage.removeItem('role')
            navigate('/login')
        }
    }

    const handleAdd = async (newPrepaid) => {
        const response = await PrepaidCalls.addPrepaid(newPrepaid)
        if (response && response.ok) {
            await fetchPrepaids(page)
            setMessage("")
        } else if (response.status === 401) {
            localStorage.removeItem('token')
            localStorage.removeItem('role')
            navigate('/login')
        } else if (response.status === 409) {
            if (response.data === "prepaid-exists") {
                setMessage("Prepaid already exists")
            }
        }
    }

    const nextPage = async () => {
        const newPage = page + 1
        setPage(newPage)
        setMessage("")
        await fetchPrepaids(newPage)
    }
    const prevPage = async () => {
        const newPage = page - 1
        setPage(newPage)
        setMessage("")
        await fetchPrepaids(newPage)
    }

    const renderPrevButton = () => {
        if (page !== 0) {
            return <Button className="remove-button doc-button-color shadow-sm"
                           onClick={() => prevPage()}>Prev</Button>
        }
    }

    const renderNextButton = () => {
        if (page < maxPage - 1) {
            return <Button className="remove-button doc-button-color shadow-sm"
                           onClick={() => nextPage()}>Next</Button>
        }
    }

    return (
        <div className="background">
            <SinglePropertyAddModal handleAdd={handleAdd} property={"Prepaid"}/>
            {message && (
                <div className="form-group">
                    <div className="alert alert-danger" role="alert">
                        {message}
                    </div>
                </div>
            )}
            <Container>
                <div className="admin-info-container admin-clinic-prepaid-container">
                    {prepaids.map((prepaid) => {
                        return (
                            <Card className="mb-3 shadow"
                                  style={{color: "#000", width: '20rem', height: '7rem'}}
                                  key={prepaid.name}>
                                <Card.Body>
                                    <Card.Title>{prepaid.name}</Card.Title>
                                </Card.Body>
                                <Button className="remove-button remove-button-color shadow-sm"
                                        onClick={() => deletePrepaids(prepaid.name)}>
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

export default Prepaids