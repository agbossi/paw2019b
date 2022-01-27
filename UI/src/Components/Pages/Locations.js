import React, {Component, useEffect, useState} from 'react';
import {Button, Card, Container} from "react-bootstrap";
import '../CardContainer.css'
import SinglePropertyAddModal from "../Modals/SinglePropertyAddModal";
import ApiCalls from "../../api/apiCalls"
import {useNavigate} from "react-router-dom";

function Locations(props){
    const [locations, setLocations] = useState([])
    const [page, setPage] = useState(0)
    const [maxPage, setMaxPage] = useState(0)
    const navigate = useNavigate()

    const fetchLocations = async () => {
        const response = await ApiCalls.getLocations(page)
        if (response && response.ok){
            setLocations(response.data)
            setMaxPage(response.headers.xMaxPage)
        }
    }

     useEffect(async () => {
        await fetchLocations()
    }, [])

    const deleteLocation = (name) => {
        setLocations(locations.filter(location => location.name !== name))
    }

    const handleAdd = async (newLocation) => {
        const response = await ApiCalls.addLocation(newLocation);
        if (response && response.ok) {
            setLocations([...locations, newLocation])
        } else if (response.status === 401) {
            localStorage.removeItem('token')
            localStorage.removeItem('role')
            navigate('/login')
        } else if (response.status === 409){

        } else
            console.error("could not add location: ", response.status)
    }

    const nextPage = async () => {
        const pag = page + 1
        const response = await ApiCalls.getLocations(pag);
        if (response && response.ok) {
            setLocations(response.data)
            setPage(pag)
            setMaxPage(response.headers.xMaxPage)
        }
    }
    const prevPage = async () => {
        const pag = page - 1
        const response = await ApiCalls.getLocations(pag);
        if (response && response.ok) {
            setLocations(response.data)
            setPage(pag)
            setMaxPage(response.headers.xMaxPage)
        }
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
            <Container>
                <div className="admin-info-container">
                    {locations.map(location => {
                        return (
                            <Card className="mb-3 shadow"
                                  style={{color: "#000", width: '20rem', height: '7rem'}} key={location.name}>
                                <Card.Body>
                                    <Card.Title>{location.name}</Card.Title>
                                </Card.Body>
                                <Button className="remove-button doc-button-color shadow-sm"
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