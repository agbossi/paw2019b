import React, { useState, useEffect } from 'react';
import {Button, Card, Container} from "react-bootstrap";
import '../CardContainer.css'
import DoctorAddModal from "../Modals/DoctorAddModal";
import ApiCalls from "../../api/apiCalls";
import {useNavigate} from "react-router-dom";

function Doctors() {
    const [doctors, setDoctors] = useState([])
    const [specialties, setSpecialties] = useState([])
    const [page, setPage] = useState(0)
    const [maxPage, setMaxPage] = useState(0)
    const [message, setMessage] = useState("")
    const navigate = useNavigate()

    const fetchDoctors = async () => {
        const response = await ApiCalls.getDoctors(page)
        if (response && response.ok) {
            setDoctors(response.data)
            setMaxPage(parseInt(response.headers.xMaxPage))
        }

    }

    const fetchSpecialties = async () => {
        const response = await ApiCalls.getAllSpecialties()
        if (response && response.ok) {
            setSpecialties(response.data)
        }
    }

    useEffect(async () => {
        await fetchDoctors()
        await fetchSpecialties()
    }, [])

    const handleAdd = async (newDoctor) => {
        const response = await ApiCalls.addDoctor(newDoctor);
        if (response && response.ok) {
            const doc = {
                license: newDoctor.license,
                phoneNumber: newDoctor.phoneNumber,
                profileImage: null,
                specialty: newDoctor.specialty,
                user: {
                    email: newDoctor.email,
                    firstName: newDoctor.firstName,
                    lastName: newDoctor.lastName
                }
            }
            const d = [...doctors, doc]
            setDoctors(d)
            setMessage("")
        } else if (response.status === 401) {
            localStorage.removeItem('token')
            localStorage.removeItem('role')
            navigate('/login')
        } else if (response.status === 409) {
            setMessage("Licence already registered")
        } else {
            console.error("could not add: ", response.status)
        }
    }

    const deleteDoctors = (license) => {
        setDoctors(doctors.filter(doctor => doctor.license !== license))
    }

    const nextPage = async () => {
        const pag = page + 1
        const response = await ApiCalls.getDoctors(pag);
        if (response && response.ok) {
            setDoctors(response.data)
            setPage(pag)
            setMaxPage(response.headers.xMaxPage)
        }

    }
    const prevPage = async () => {
        const pag = page - 1
        const response = await ApiCalls.getDoctors(pag);
        if (response && response.ok) {
            setDoctors(response.data)
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
        if (page < maxPage - 1) {
            return <Button className="remove-button doc-button-color shadow-sm"
                           onClick={() => nextPage()}>Next</Button>
        }
    }

    return (
        <div className="background">
            <DoctorAddModal
                handleAdd={handleAdd}
                specialties={specialties.map(specialty => specialty.name)}
            />
            {message && (
                <div className="form-group">
                    <div className="alert alert-danger" role="alert">
                        {message}
                    </div>
                </div>
            )}
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
            {renderPrevButton()}
            {renderNextButton()}
        </div>
    )
}

export default Doctors