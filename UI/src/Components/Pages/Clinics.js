import React, {Component, useEffect, useState} from 'react';
import {Button, Card, Container} from "react-bootstrap";
import '../CardContainer.css'
import ClinicEditModal from "../Modals/ClinicEditModal";
import {Link, useNavigate} from "react-router-dom";
import ApiCalls from "../../api/apiCalls";

function Clinics(props) {

    const [clinics, setClinics] = useState([])
    const [show, setShow] = useState(false)
    const [editableClinic, setEditableClinic] = useState( {id: ' ', name: ' ', address: ' ', location: ' '})
    const [editIndex, setEditIndex] = useState(-1)
    const [handleFunction, setHandleFunction] = useState(null)
    const [action, setAction] = useState("Add")
    const [locations, setLocations] = useState([])
    const [page, setPage] = useState(0)
    const [maxPage, setMaxPage] = useState(0)
    const [message, setMessage] = useState("")
    const navigate = useNavigate()

    const fetchClinics = async () => {
        const response = await ApiCalls.getClinics(page)
        if (response && response.ok) {
            setClinics(response.data)
            setMaxPage(parseInt(response.headers['X-max-page']))
        }
    }

    const fetchLocation = async () => {
        const response = await ApiCalls.getAllLocations()
        if (response && response.ok) {
            setLocations(response.data)
        }
    }

    useEffect( async () => {
        await fetchClinics()
        await fetchLocation()
    }, [])

    const deleteClinic = (id) => {
        setClinics(clinics.filter(clinic => clinic.id !== id))
    }

    const handleAdd = (newClinic) => {
        setClinics([...clinics, newClinic])
        setShow(false)
    }

    const handleEdit = (editedClinic) => {
        let index = editIndex
        let clinicList = clinics
        clinicList[index] = editedClinic

        setClinics(clinicList)
        setShow(false)
    }

    const handleShow = (index) => {
        let action
        let clinic
        if (index === -1) {
            action = "Add"
            clinic = {id: ' ', name: ' ', address: ' ', location: ' '}
        } else {
            action = "Edit"
            clinic = clinics[index]
        }

        setEditableClinic(clinic)
        setEditIndex(index)
        setShow(true)
        setAction(action)
    }

    const hideModal = () => {
        setShow(false)
    }

    const nextPage = async () => {
        setPage(page + 1)
        await fetchClinics()

    }
    const prevPage = async () => {
        setPage(page - 1)
        await fetchClinics()
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
            <Button variant="outline-secondary"
                    onClick={() => handleShow(-1)}
                    size="lg"
                    className="add-margin">
                Add Clinic
            </Button>
            {show && <ClinicEditModal show={show}
                             clinic={editableClinic}
                             handleAdd={handleAdd}
                             handleEdit={handleEdit}
                             action={action}
                             hideModal={hideModal}
                             locations={locations.map(location => location.name)}
            /> }
            <Container>
                <div className="admin-info-container">
                    {clinics.map(( clinic, index) => {
                        return (
                            <Card className="mb-3 shadow" style={{color: "#000", width: '20rem', height: '15rem'}} key={clinic.id}>
                                <Card.Body>
                                    <Card.Title>{clinic.name}</Card.Title>
                                    <Card.Text>
                                        {clinic.address + ' (' + clinic.location + ')'}
                                    </Card.Text>
                                </Card.Body>
                                <Link className="btn btn-outline-dark btn-lg see-prepaid-button shadow-sm"
                                      role="button"
                                      to={'admin/clinics/' + clinic.id + '/prepaids'}>See Prepaids
                                </Link>
                                <div className="buttons-div">
                                    <Button className="edit-remove-button doc-button-color shadow-sm"
                                            onClick={() => deleteClinic(clinic.id)}>
                                    Delete
                                    </Button>
                                    <Button className="btn edit-remove-button doc-button-color shadow-sm"
                                            onClick={() => handleShow(index)}>
                                        <i className="far fa-edit"/>
                                    </Button>
                                </div>

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

export default Clinics