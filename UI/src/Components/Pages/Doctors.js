import React, { useState, useEffect } from 'react';
import {Button, Card, Container} from "react-bootstrap";
import '../CardContainer.css'
import DoctorAddModal from "../Modals/DoctorAddModal";
import ApiCalls from "../../api/apiCalls";

function Doctors() {
    const [doctors, setDoctors] = useState([])
    const [specialties, setSpecialties] = useState([])
    const [page, setPage] = useState(0)
    const [maxPage, setMaxPage] = useState(0)

    const fetchDoctors = async () => {
        const response = await ApiCalls.getDoctors(page)
        if (response && response.ok) {
            setDoctors(response.data)
            setMaxPage(parseInt(response.headers.xMaxPage))
        }

    }

    const fetchSpecialties = () => {
        return ['Clinica', 'Cardiologia', 'Neurologia', 'miau']
    }

    useEffect(async () => {
        await fetchDoctors()
        setSpecialties(fetchSpecialties())
    }, [])

    const handleAdd = (newDoctor) => {
        let d = [...doctors, newDoctor]
        setDoctors(d)
    }

    const deleteDoctors = (license) => {
        setDoctors(doctors.filter(doctor => doctor.license !== license))
    }

    return (
        <div className="background">
            <DoctorAddModal handleAdd={handleAdd} specialties={specialties} />
            <Container>
                <div className="admin-info-container">
                    {doctors.map((doctor) => {
                        return (
                            <Card className="mb-3 doc-card shadow"
                                  key={doctor.license}>
                                <Card.Body>
                                    <Card.Title><b>{doctor.user.firstName + ' ' + doctor.user.lastName}</b></Card.Title>
                                    <Card.Text>
                                        <b>License</b>: {doctor.license}
                                    </Card.Text>
                                </Card.Body>
                                <Button className="remove-button doc-button-color shadow-sm"
                                        onClick={() => deleteDoctors(doctor.license)}>
                                    Delete
                                </Button>
                            </Card>
                        )
                    })}
                </div>
            </Container>
        </div>
    )
}

export default Doctors