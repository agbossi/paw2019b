import {Button, Card, Container} from "react-bootstrap";
import '../CardContainer.css'
import {useParams} from "react-router-dom";
import React, { useState, useEffect } from 'react';
import ClinicPrepaidAddModal from "../Modals/ClinicPrepaidAddModal";

function ClinicPrepaids() {

    let {id} = useParams()
    const [prepaidClinics, setPrepaidClinics] = useState([])
    const [clinic, setClinic] = useState({})

    const fetchPrepaids = (id) => {
        return ['OSDE', '']
    }

    const fetchClinic = (id) => {
        return {
            "id": id,
            "address": "",
            "name": id,
            "location": "",
            "prepaids": ""
        }
    }

    useEffect(() => {
        setClinic(fetchClinic(id))
        setPrepaidClinics(fetchPrepaids(id))
    }, [id])

    const handleAdd = (newPrepaid) => {
        let prepaids = [...prepaidClinics, newPrepaid]
        setPrepaidClinics(prepaids)
    }

    const deletePrepaid = (name) => {
        setPrepaidClinics(prepaidClinics.filter(prepaid => prepaid !== name))
    }

    return (
        <>
            <h3 style={{color: "#FFF"}}>{'Clinic ' + clinic.name}</h3>
            <ClinicPrepaidAddModal handleAdd={handleAdd}/>
            <Container>
                <div className="admin-info-container">
                    {prepaidClinics.map((prepaidClinics, index) => {
                        return (
                            <Card className="mb-3"
                                  style={{color: "#000", width: '20rem', height: '7rem'}}
                                  key={prepaidClinics}>
                                <Card.Body>
                                    <Card.Title>{prepaidClinics}</Card.Title>
                                </Card.Body>
                                <Button variant="danger" onClick={() => deletePrepaid(prepaidClinics)}>
                                    X
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