import React, {useEffect, useState} from 'react';
import {Button, Col, Container, Form, Row} from "react-bootstrap";
import DropDownList from "../DropDownList";
import {useTranslation} from "react-i18next";
import '../../i18n/i18n'
import {Link, useNavigate} from "react-router-dom";
import PatientCalls from "../../api/PatientCalls";
import PrepaidCalls from "../../api/PrepaidCalls";
import EditUserProfileModal from "../Modals/EditUserProfileModal";
import AppointmentCalls from "../../api/AppointmentCalls";
import {dateToString} from "../../utils/dateHelper";
import './Profile.css'
import ClinicCalls from "../../api/ClinicCalls";
import DoctorCalls from "../../api/DoctorCalls";
import ApiCalls from "../../api/apiCalls";

function Profile() {
    const [selectedPrepaid, setSelectedPrepaid] = useState('')
    const [firstName, setFirstName] = useState(localStorage.getItem('firstName'))
    const [lastName, setLastName] = useState(localStorage.getItem('lastName'))
    const [id, setId] = useState('')
    const [prepaidNumber, setPrepaidNumber] = useState(localStorage.getItem('prepaid'))
    const [prepaids, setPrepaids] = useState([])
    const [appointments, setAppointments] = useState([])
    const [modalReady, setModalReady] = useState(false)

    const { t } = useTranslation();
    const navigate = useNavigate();

    useEffect(async () => {
        await ApiCalls.handleInformation()
        await fetchAppointments()
        await fetchProfile()
        await fetchPrepaids()
        localStorage.setItem('path', '/profile')
        awaitModal()
    }, [])

    const awaitModal = () => {
        while(localStorage.getItem('lastName') === null) {

        }
        setModalReady(true)
    }

    const fetchPrepaids = async () => {
        const response = await PrepaidCalls.getAllPrepaids();
        if (response && response.ok) {
            setPrepaids(response.data.map(prepaid => prepaid.name))
        }
    }

    const fetchDoctor = async (license) => {
        return await DoctorCalls.getDocByLicense(license)
    }

    const fetchClinic = async (id) => {
        return await ClinicCalls.getClinic(id)
    }

    const generateAppointments = async (appointments) => {
        let doctors = []
        let clinics = []
        const apps = []
        const doctorFetchPromises = appointments.map(ap => {
            return new Promise((resolve, reject) => {
                fetchDoctor(ap.license).then(resp => resolve(resp.data))
            })
        })
        const clinicFetchPromises = appointments.map(ap => {
            return new Promise((resolve, reject) => {
                fetchClinic(ap.clinicId).then(resp => resolve(resp.data))
            })
        })
        Promise.all(doctorFetchPromises).then(d => doctors = d)
        Promise.all(clinicFetchPromises).then(c => clinics = c).then(() => {
            for (let i = 0; i < appointments.length; i++) {
                apps.push({
                    appointment: appointments[i],
                    name: clinics[i].name,
                    firstName: doctors[i].firstName,
                    lastName: doctors[i].lastName
                })
            }
            setAppointments(apps)
        })
    }

    const fetchProfile = async () => {
        const response = await PatientCalls.getProfile(localStorage.getItem('email'));
        if (response && response.ok) {
            setFirstName(response.data.firstName)
            //localStorage.setItem('firstName', response.data.firstName)
            setLastName(response.data.lastName)
            //localStorage.setItem('lastName', response.data.lastName)
            setSelectedPrepaid(response.data.prepaid)
            //localStorage.setItem('prepaid', response.data.prepaid)
            setPrepaidNumber(response.data.prepaidNumber)
           // localStorage.setItem('prepaidNumber', response.data.prepaidNumber)
            setId(response.data.id)
        } else if (response.status === 401) {
            localStorage.removeItem('token')
            localStorage.removeItem('role')
            navigate('/paw-2019b-4/login')
        }
    }

    const fetchAppointments = async () => {
        const email = localStorage.getItem('email')
        if (email === null) {
            localStorage.removeItem('token')
            localStorage.removeItem('role')
            navigate('/paw-2019b-4/login')
        }
        const response = await AppointmentCalls.getAppointment(email, 0)
        if (response && response.ok) {
            await generateAppointments(response.data.slice(0, 3))
            //setAppointments(response.data.slice(0, 3))
        }
    }
    const handleProfileUpdateOk = async () => {
            await fetchProfile()
            await ApiCalls.handleInformation();
            await fetchPrepaids()
    }

    return (
        <>
            <Container>
                <Row>
                    <Col>
                        <h3 className="mt-3">
                            {t("welcome")}, {firstName} {lastName}!
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
                            <b>{t('FORM.name')}:</b> {firstName} {lastName}
                        </div>
                        <div className="info-label">
                            <b>{t('FORM.email')}:</b> {localStorage.getItem('email')}
                        </div>
                        <div className="info-label">
                            <b>{t('FORM.id')}:</b> {id}
                        </div>
                        <div className="info-label">
                            <b>{t('FORM.prepaid')}:</b> {selectedPrepaid !== undefined? selectedPrepaid: t("FORM.none")}
                        </div>
                        {selectedPrepaid &&
                            <div className="info-label">
                                <b>{t('FORM.prepaidNumber')}:</b> {prepaidNumber}
                            </div>
                        }
                    </Col>
                    <Col>
                        <h4>{t('nextApp')}</h4>
                        <ul style={{display:"grid", justifyItems:"flex-start"}}>
                            {appointments.map(app => {
                                return(
                                    <li className="my-3">
                                        <b>{dateToString(app.appointment, t)}</b> {t("with")} <b>{app.firstName + ' ' + app.lastName}</b> ({app.name})
                                    </li>
                                )
                            })}
                        </ul>

                    </Col>
                </Row>
                <Row>
                    <Col className="col-button">
                        {modalReady && <EditUserProfileModal prepaids={prepaids}
                                              handleOk={handleProfileUpdateOk}
                                              prepaidNumber={prepaidNumber}
                        />}
                    </Col>
                    <Col className="col-button">
                        <Link
                            className="edit-remove-button doc-button-color shadow-sm edit-button btn app-btn"
                            role="button"
                            to="/paw-2019b-4/appointments">{t('NAVBAR.appointments')}
                        </Link>
                    </Col>
                </Row>
                <hr/>
                <Row>
                </Row>
            </Container>
        </>
    )
}

export default Profile;