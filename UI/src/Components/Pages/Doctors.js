import React, { useState, useEffect } from 'react';
import {Button, Card, Container} from "react-bootstrap";
import '../CardContainer.css'
import DoctorAddModal from "../Modals/DoctorAddModal";
import DoctorCalls from "../../api/DoctorCalls";
import SpecialtyCalls from "../../api/SpecialtyCalls";
import {useNavigate} from "react-router-dom";

function Doctors() {
    const [doctors, setDoctors] = useState([])
    const [specialties, setSpecialties] = useState([])
    const [page, setPage] = useState(0)
    const [maxPage, setMaxPage] = useState(0)
    const [message, setMessage] = useState("")
    const navigate = useNavigate()

    const fetchDoctors = async (pag) => {
        const response = await DoctorCalls.getDoctorsAdmin(pag)
        if (response && response.ok) {
            setDoctors(response.data)
            setMaxPage(Number(response.headers.xMaxPage))
        }

    }

    const fetchSpecialties = async () => {
        const response = await SpecialtyCalls.getAllSpecialties()
        if (response && response.ok) {
            setSpecialties(response.data)
        }
    }

    useEffect(async () => {
        await fetchDoctors(page)
        await fetchSpecialties()
    }, [])

    const handleAdd = async (newDoctor) => {
        const response = await DoctorCalls.addDoctor(newDoctor);
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
        } else if (response.status === 409 && response.data === 'license-in-use') {
            setMessage("Licence already registered")
        } else {
            console.error("could not add doctor: ", response.status)
        }
    }

    const deleteDoctors = async (license) => {
        const response = await DoctorCalls.deleteDoctor(license)
        if (response && response.status === 204)
            setDoctors(doctors.filter(doctor => doctor.license !== license))
        else
            console.error("could not delete doctor: ", response.status)
    }

    const nextPage = async () => {
        const newPage = page + 1
        setPage(newPage)
        await fetchDoctors(newPage)

    }
    const prevPage = async () => {
        const newPage = page - 1
        setPage(newPage)
        await fetchDoctors(newPage)
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
                <div className="admin-info-container admin-doctor-container">
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