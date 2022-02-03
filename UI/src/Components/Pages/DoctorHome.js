import React, {useEffect, useState} from "react";
import DoctorCalls from "../../api/DoctorCalls";
import {Button, Col, Container, Row} from "react-bootstrap";
import Image from 'react-bootstrap/Image'
import './DoctorHome.css'
import '../CardContainer.css'
import {useTranslation} from "react-i18next";
import EditDocProfileModal from "../Modals/EditDocProfileModal";
import SpecialtyCalls from "../../api/SpecialtyCalls";

function DoctorHome(props) {
    const [doctor, setDoctor] = useState({})
    const [message, setMessage] = useState("")
    const [specialties, setSpecialties] = useState([])
    const {t} = useTranslation()

    const fetchDoctor = async () => {
        const email = localStorage.getItem('email')
        if (email) {
            const response = await DoctorCalls.getDocByEmail(email)
            if (response && response.ok) {
                setDoctor(response.data)
                localStorage.setItem('license', response.data.license)
                localStorage.setItem('firstName', response.data.userData.firstName)
                localStorage.setItem('lastName', response.data.userData.lastName)
            }
            if (response.status === 404)
                setMessage("errors.noDocEmail")
        }
        else {
            setMessage("errors.noLoggedDoc")
        }

    }

    const fetchSpecialties = async () => {
        const response = await SpecialtyCalls.getAllSpecialties();
        if (response && response.ok) {
            setSpecialties(response.data)
            setMessage("")
        }
    }

    const getSpecialty = () => {
        return doctor.specialty
    }

    const getDoctorsFirstName = () => {
        if(doctor.userData === undefined)
            return "Doctor!";
        else
            return doctor.userData.firstName + " "
    }

    const getDoctorsLastName = () => {
        if(doctor.userData !== undefined)
            return doctor.userData.lastName
        return ''
    }

    const handleEdit = async (doctor) => {
        const license = localStorage.getItem('license')
        const response = await DoctorCalls.editDoctor(license, doctor)
        if (response && response.ok) {
            await fetchDoctor()
            setMessage("")
        }
        if (response.status === 404) {
            setMessage('errors.doctorNotFoundEdit')
        }
    }

    useEffect(async () => {
        await fetchDoctor();
        await fetchSpecialties()
    }, [])

    return (
        <>
            <Container>
                <Row>
                    <Col>
                        <h3 className="mt-3">
                            {t('welcome')}, {getDoctorsFirstName()}{getDoctorsLastName()}!
                        </h3>
                        <hr />
                    </Col>
                </Row>
                {message && (
                    <div className="form-group">
                        <div className="alert alert-danger" role="alert">
                            {t(message)}
                        </div>
                    </div>
                )}
                <Row>
                    <Col className="info-col">
                        <h4>
                            {t('NAVBAR.profile')}
                        </h4>
                        <div className="info-label">
                            <b>{t('FORM.name')}:</b> {getDoctorsFirstName()}{getDoctorsLastName()}
                        </div>
                        <div className="info-label">
                            <b>{t('FORM.email')}:</b> {localStorage.getItem('email')}
                        </div>
                        <div className="info-label">
                            <b>{t('DOC.license')}:</b> {doctor.license}
                        </div>
                        <div className="info-label">
                            <b>{t('FORM.phoneNumber')}:</b> {doctor.phoneNumber}
                        </div>
                        <div className="info-label">
                            <b>{t('ADMIN.specialty')}:</b> {doctor.specialty}
                        </div>
                        <EditDocProfileModal specialties={specialties.map(specialty=> specialty.name)}
                                             handleEdit={handleEdit}
                                             specialty={getSpecialty}
                                             phoneNumber={doctor.phoneNumber}
                        />
                    </Col>
                    <Col>
                        <Image className="img-size" src="/images/docpic.jpg" />
                        <div className="mt-3">
                            <Button className="edit-remove-button doc-button-color shadow-sm">
                                {t('changeImgButton')}
                            </Button>
                        </div>
                    </Col>
                </Row>
                <hr/>
                <Row>
                </Row>
            </Container>
        </>

    )
}
export default DoctorHome