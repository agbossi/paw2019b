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
    const [allPrepaids, setAllPrepaids] = useState([])
    const [clinic, setClinic] = useState({})
    const [message, setMessage] = useState("")
    const navigate = useNavigate()

    const fetchClinicPrepaids = async () => {
        const response = await ClinicCalls.getClinicPrepaids(id);
        if (response && response.ok) {
            setPrepaidClinics(response.data)
            setMessage("")
        }
        if(response.status === 401) {
            localStorage.removeItem('token')
            localStorage.removeItem('role')
            navigate('/login')
        }
    }

    const fetchPrepaids = async () => {
        const response = await PrepaidCalls.getAllPrepaids();
        if (response && response.ok) {
            setAllPrepaids(response.data)
            setMessage("")
        }
        if(response.status === 401) {
            localStorage.removeItem('token')
            localStorage.removeItem('role')
            navigate('/login')
        }
    }

    const fetchClinic = async (id) => {
        const response = await ClinicCalls.getClinic(id);
        if (response && response.ok) {
            setClinic(response.data)
            setMessage("")
        }
        if(response.status === 401) {
            localStorage.removeItem('token')
            localStorage.removeItem('role')
            navigate('/login')
        }

        if (response.status === 404) {
            setMessage("Clinic not found")
        }
    }

    useEffect(async () => {
        await fetchClinicPrepaids()
        await fetchPrepaids()
        await fetchClinic(id)
    }, [])

    const handleAdd = async (newPrepaid) => {
        const response = await ClinicCalls.addClinicPrepaid(id, newPrepaid)
        if (response && response.ok) {
            setPrepaidClinics([...prepaidClinics, {name: newPrepaid}])
        }
        if(response.status === 401) {
            localStorage.removeItem('token')
            localStorage.removeItem('role')
            navigate('/login')
        }
    }

    const deletePrepaid = async (name) => {
        const response = await ClinicCalls.deleteClinicPrepaid(id, name)
        if (response && response.ok) {
            setPrepaidClinics(prepaidClinics.filter(prepaid => prepaid.name !== name))
        }

        if(response.status === 401) {
            localStorage.removeItem('token')
            localStorage.removeItem('role')
            navigate('/login')
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
                prepaids={prepaidClinics.map(prepaid => prepaid.name)}
                allPrepaids={allPrepaids.map(prepaid => prepaid.name)}
                id={id}
                navigate={{navigate}}
            />
            <Container>
                <div className="admin-info-container">
                    {prepaidClinics.map((prepaidClinics, index) => {
                        return (
                            <Card className="mb-3 shadow"
                                  style={{color: "#000", width: '20rem', height: '7rem'}}
                                  key={prepaidClinics.name}>
                                <Card.Body>
                                    <Card.Title>{prepaidClinics.name}</Card.Title>
                                </Card.Body>
                                <Button className="doc-button-color remove-button shadow-sm"
                                        onClick={() => deletePrepaid(prepaidClinics.name)}>
                                    Delete
                                </Button>
                            </Card>
                        )
                    })}
                </div>
            </Container>
        </>
    )
}

export default ClinicPrepaids