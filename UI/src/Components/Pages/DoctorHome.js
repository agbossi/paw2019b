import {useEffect, useState} from "react";
import DoctorCalls from "../../api/DoctorCalls";
import {Button, Col, Container, Row} from "react-bootstrap";
import Image from 'react-bootstrap/Image'
import './DoctorHome.css'
import '../CardContainer.css'
import {useTranslation} from "react-i18next";

function DoctorHome(props) {
    const [doctor, setDoctor] = useState({})
    const [message, setMessage] = useState("")
    const {t} = useTranslation()

    const fetchDoctor = async () => {
        const email = localStorage.getItem('email')
        if (email) {
            const response = await DoctorCalls.getDocByEmail(email)
            if (response && response.ok) {
                setDoctor(response.data)
                localStorage.setItem('license', response.data.license)
            }
            if (response.status === 404)
                setMessage("No doctor found with logged email.")
        }
        setMessage("No logged email found, try logging out and logging in again.")
    }

    const getDoctorsName = () => {
        if(doctor.user === undefined)
            return "Doctor!";
        else
            return doctor.userData.firstName + " " + doctor.userData.lastName;
    }

    useEffect(async () => {
        await fetchDoctor()
    }, [])

    return (
        <>
            <Container>
                <Row>
                    <Col>
                        <h3 className="mt-3">
                            {t('welcome')}, {getDoctorsName()}!
                        </h3>
                        <hr />
                    </Col>
                </Row>
                <Row>
                    <Col className="info-col">
                        <h4>
                            {t('NAVBAR.profile')}
                        </h4>
                        <div className="info-label">
                            <b>{t('FORM.name')}:</b> {getDoctorsName()}
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
                        <Button className="edit-remove-button doc-button-color shadow-sm edit-button">
                            {t('editProfileButton')}
                        </Button>
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