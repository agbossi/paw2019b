import React, { useState, useEffect } from 'react';
import {Button, Card, Container} from "react-bootstrap";
import '../CardContainer.css'
import DoctorAddModal from "../Modals/DoctorAddModal";

function Doctors() {
    const [doctors, setDoctors] = useState([])
    const [specialties, setSpecialties] = useState([])

    const fetchDoctors = () => {
        return [
            {
                "firstName": "Cecilia",
                "lastName": "Rodriguez",
                "license": "123313213166"
            },
            {
                "firstName": "Dr Evil",
                "lastName": "Evil",
                "license": "66666666"
            },
            {
                "firstName": "Jose",
                "lastName": "Hernandez",
                "license": "12332111"
            },
            {
                "firstName": "eeeee",
                "lastName": "eeeeee",
                "license": "131443556"
            }
            ]
    }

    const fetchSpecialties = () => {
        return ['Clinica', 'Cardiologia', 'Neurologia', 'miau']
    }

    useEffect(() => {
        setDoctors(fetchDoctors())
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
        <>
            <DoctorAddModal handleAdd={handleAdd} specialties={specialties}/>
            <Container>
                <div className="admin-info-container">
                    {doctors.map((doctor) => {
                        return (
                            <Card className="mb-3"
                                  style={{color: "#000", width: '20rem', height: '7rem'}}
                                  key={doctor.license}>
                                <Card.Body>
                                    <Card.Title>{doctor.firstName + ' ' + doctor.lastName}</Card.Title>
                                    <Card.Text>
                                        {doctor.license}
                                    </Card.Text>
                                </Card.Body>
                                <Button variant="danger" onClick={() => deleteDoctors(doctor.license)}>
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

export default Doctors