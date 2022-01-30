import {Button, Card, Container} from "react-bootstrap";
import '../CardContainer.css'
import {useNavigate, useParams} from "react-router-dom";
import React, { useState, useEffect } from 'react';
import ClinicPrepaidAddModal from "../Modals/ClinicPrepaidAddModal";
import ClinicCalls from "../../api/ClinicCalls";
import PrepaidCalls from "../../api/PrepaidCalls";

function ClinicPrepaids() {

    let {id} = useParams()
    const [prepaidClinics, setPrepaidClinics] = useState([])
    const [allPrepaidClinics, setAllPrepaidClinics] = useState([])
    const [allPrepaids, setAllPrepaids] = useState([])
    const [clinic, setClinic] = useState({})
    const [message, setMessage] = useState("")
    const [page, setPage] = useState(0)
    const [maxPage, setMaxPage] = useState(0)

    const navigate = useNavigate()

    const fetchAllClinicPrepaids = async () => {
        const response = await ClinicCalls.getAllClinicPrepaids(id);
        if (response && response.ok) {
            setAllPrepaidClinics(response.data)
        }
        if (response.status === 404) {
            if (response.data === "clinic-not-found") {
                setMessage("Clinic does not exist")
            }
        }

    }

    const fetchClinicPrepaids = async (pag) => {
        const response = await ClinicCalls.getClinicPrepaids(id, pag);
        if (response && response.ok) {
            setPrepaidClinics(response.data)
            setMaxPage(response.headers.xMaxPage)
        }
        if (response.status === 404) {
            if (response.data === "clinic-not-found") {
                setMessage("Clinic does not exist")
            }
        }

    }

    const fetchPrepaids = async () => {
        const response = await PrepaidCalls.getAllPrepaids();
        if (response && response.ok) {
            setAllPrepaids(response.data)
        }
    }

    const fetchClinic = async (id) => {
        const response = await ClinicCalls.getClinic(id);
        if (response && response.ok) {
            setClinic(response.data)
            setMessage("")
        }
        if (response.status === 404) {
            setMessage("Clinic does not exists")
        }
    }

    useEffect(async () => {
        await fetchClinicPrepaids(page)
        await fetchAllClinicPrepaids()
        await fetchPrepaids()
        await fetchClinic(id)
    }, [])

    const handleAdd = async (newPrepaid) => {
        const response = await ClinicCalls.addClinicPrepaid(id, newPrepaid)
        if (response && response.ok) {
            await fetchAllClinicPrepaids()
            await fetchClinicPrepaids(page)
            setMessage("")
        }
        if(response.status === 401) {
            localStorage.removeItem('token')
            localStorage.removeItem('role')
            navigate('/login')
        }
        if (response.status === 404) {
            if (response.data === "prepaid-not-found") {
                setMessage("Prepaid chosen does not exist")
            }
            if (response.data === "clinic-not-found") {
                setMessage("Clinic does not exist")
            }
        }
    }

    const deletePrepaid = async (name) => {
        const response = await ClinicCalls.deleteClinicPrepaid(id, name)
        if (response && response.ok) {
            await fetchAllClinicPrepaids()
            await fetchClinicPrepaids(page)
            setMessage("")
        }
        if (response.status === 404) {
            if (response.data === "prepaid-not-found") {
                setMessage("Prepaid chosen does not exist")
            }
            if (response.data === "clinic-not-found") {
                setMessage("Clinic does not exist")
            }
            if (response.data === "clinic-prepaid-not-found") {
                setMessage("No prepaid in clinic found to delete")
            }
        }
        if(response.status === 401) {
            localStorage.removeItem('token')
            localStorage.removeItem('role')
            navigate('/login')
        }

    }

    const nextPage = async () => {
        const newPage = page + 1
        setPage(newPage)
        await fetchClinicPrepaids(newPage)
    }
    const prevPage = async () => {
        const newPage = page - 1
        setPage(newPage)
        await fetchClinicPrepaids(newPage)
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
        <>
            <h3 className="clinic-prepaid-title">{'Clinic ' + clinic.name}</h3>
            {message && (
                <div className="form-group">
                    <div className="alert alert-danger" role="alert">
                        {message}
                    </div>
                </div>
            )}
            <ClinicPrepaidAddModal
                handleAdd={handleAdd}
                prepaids={allPrepaidClinics.map(prepaid => prepaid.name)}
                allPrepaids={allPrepaids.map(prepaid => prepaid.name)}
                id={id}
                navigate={{navigate}}
            />
            <Container>
                <div className="admin-info-container admin-clinic-prepaid-container">
                    {prepaidClinics.map((prepaidClinics, index) => {
                        return (
                            <Card className="mb-3 shadow"
                                  style={{color: "#000", width: '20rem', height: '7rem'}}
                                  key={prepaidClinics.name}>
                                <Card.Body>
                                    <Card.Title>{prepaidClinics.name}</Card.Title>
                                </Card.Body>
                                <Button className="remove-button-color remove-button shadow-sm"
                                        onClick={() => deletePrepaid(prepaidClinics.name)}>
                                    Delete
                                </Button>
                            </Card>
                        )
                    })}
                </div>
            </Container>
            {renderPrevButton()}
            {renderNextButton()}
        </>
    )
}

export default ClinicPrepaids