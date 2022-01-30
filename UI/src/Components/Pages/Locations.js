import React, {Component, useEffect, useState} from 'react';
import {Button, Card, Container} from "react-bootstrap";
import '../CardContainer.css'
import SinglePropertyAddModal from "../Modals/SinglePropertyAddModal";
import LocationCalls from "../../api/LocationCalls";
import {useNavigate} from "react-router-dom";

function Locations(props){
    const [locations, setLocations] = useState([])
    const [page, setPage] = useState(0)
    const [maxPage, setMaxPage] = useState(0)
    const navigate = useNavigate()
    const [message, setMessage] = useState("")

    const fetchLocations = async (pag) => {
        const response = await LocationCalls.getLocations(pag)
        if (response && response.ok){
            setLocations(response.data)
            setMaxPage(response.headers.xMaxPage)
        }
    }

     useEffect(async () => {
        await fetchLocations(page)
    }, [])

    const deleteLocation = async (name) => {
        const response = await LocationCalls.deleteLocation(name);
        if (response && response.ok) {
            await fetchLocations(page)
            setMessage("")
        }
        if (response.status === 404) {
            if (response.data === "location-not-found") {
                setMessage("No location found to delete")
            }
        }
        if (response.status === 401) {
            localStorage.removeItem('token')
            localStorage.removeItem('role')
            navigate('/login')
        }
        if (response.status === 409) {
            if (response.data === "clinics-dependency") {
                setMessage("Could not delete location: One or more clinics are still in this location")
            }
        }
    }

    const handleAdd = async (newLocation) => {
        const response = await LocationCalls.addLocation(newLocation);
        if (response && response.ok) {
            await fetchLocations(page)
            setMessage("")
        } else if (response.status === 401) {
            localStorage.removeItem('token')
            localStorage.removeItem('role')
            navigate('/login')
        } else if (response.status === 409) {
            if (response.data === "location-exists") {
                setMessage("Location already exists")
            }
        }
    }

    const nextPage = async () => {
        const newPage = page + 1
        setPage(newPage)
        setMessage("")
        await fetchLocations(newPage)
    }
    const prevPage = async () => {
        const newPage = page - 1
        setPage(newPage)
        setMessage("")
        await fetchLocations(newPage)
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
            <SinglePropertyAddModal handleAdd={handleAdd} property="Location"/>
            {message && (
                <div className="form-group">
                    <div className="alert alert-danger" role="alert">
                        {message}
                    </div>
                </div>
            )}
            <Container>
                <div className="admin-info-container admin-clinic-prepaid-container">
                    {locations.map(location => {
                        return (
                            <Card className="mb-3 shadow"
                                  style={{color: "#000", width: '20rem', height: '7rem'}} key={location.name}>
                                <Card.Body>
                                    <Card.Title>{location.name}</Card.Title>
                                </Card.Body>
                                <Button className="remove-button remove-button-color shadow-sm"
                                        onClick={() => deleteLocation(location.name)}>
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

export default Locations