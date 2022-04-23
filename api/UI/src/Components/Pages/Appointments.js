import React, {useEffect, useState} from "react";
import {useTranslation} from "react-i18next";
import {useNavigate} from "react-router-dom";
import AppointmentCalls from "../../api/AppointmentCalls";
import {Button, Card, Container, Row} from "react-bootstrap";
import Utils from "../../utils/paginationHelper";
import './Favorites.css'
import {dateToString, getMonth, getWeekDate} from "../../utils/dateHelper";
import DoctorCalls from "../../api/DoctorCalls";
import ClinicCalls from "../../api/ClinicCalls";
import ApiCalls from "../../api/apiCalls";

function Appointments(props) {
    const [appointments, setAppointments] = useState([])
    const [page, setPage] = useState(0)
    const [maxPage, setMaxPage] = useState(0)
    const [message, setMessage] = useState("")
    const [isLoading, setIsLoading] = useState(false)
    const {t} = useTranslation()
    const navigate = useNavigate()

    const fetchAppointments = async (pag) => {
        const email = localStorage.getItem('email')
        if (email === null) {
            localStorage.removeItem('token')
            localStorage.removeItem('role')
            navigate('/paw-2019b-4/login')
        }
        setIsLoading(true)
        const response = await AppointmentCalls.getAppointment(email, pag)
        if (response && response.ok) {
            await generateAppointments(response.data).then(() => {
                setMaxPage(Utils.getMaxPage(response.headers.link));
                setMessage("")
                setIsLoading(false)
            })
        }
        if (response.status === 404) {
            if (response.data === "user-not-found")
                setMessage("errors.noLoggedDoc")
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
                    clinic: clinics[i],
                    doctor: doctors[i]
                })
            }
            setAppointments(apps)
        })
    }

    const deleteAppointment = async (app) => {
        const email = localStorage.getItem('email')
        if (email === null) {
            localStorage.removeItem('token')
            localStorage.removeItem('role')
            navigate('/paw-2019b-4/login')
        }

        const response = await AppointmentCalls.deleteAppointment(
            email,
            app.license,
            app.clinicId,
            app.year,
            app.month,
            app.day,
            app.hour)
        if (response && response.ok) {
            await fetchAppointments(page)
            setMessage("")
        }
        if (response.status === 404) {
            if (response.data === "doctor-clinic-not-found")
                setMessage("errors.docClinicNotFound")
            if (response.data === "appointment-not-found")
                setMessage("errors.noAppointmentFound")
        }
    }

    useEffect(async () => {
        await ApiCalls.handleInformation()
        let path = props.user === 'doctor/' ? props.user : ''
        localStorage.setItem('path', '/' + path + 'appointments')
        await fetchAppointments(page);
    }, [])

    const nextPage = async () => {
        const newPage = page + 1
        setPage(newPage)
        setMessage("")
        await fetchAppointments(newPage)

    }
    const prevPage = async () => {
        const newPage = page - 1
        setPage(newPage)
        setMessage("")
        await fetchAppointments(newPage)
    }

    const renderPrevButton = () => {
        if (page !== 0) {
            return <Button className="doc-button doc-button-color shadow-sm"
                           onClick={() => prevPage()}>{t('prevButton')}</Button>
        }
    }

    const renderNextButton = () => {
        if (page < maxPage) {
            return <Button className="doc-button doc-button-color shadow-sm"
                           onClick={() => nextPage()}>{t('nextButton')}</Button>
        }
    }

    const getDoctorName = (appointment) => {
        if(appointment.doctor !== null && appointment.doctor !== undefined) {
            return appointment.doctor.firstName + ' ' + appointment.doctor.lastName
        }
        return ''
    }

    const getPatientName = (ap) => {
        if(ap.appointment.patient !== null && ap.appointment.patient !== undefined) {
            return ap.appointment.patient.firstName + ' ' + ap.appointment.patient.lastName + ' (' + ap.appointment.patient.email + ')'
        }
        return ''
    }

    const getClinicName = (ap) => {
        if(ap.clinic !== null && ap.clinic !== undefined) {
            return ap.clinic.name + ' - ' + ap.clinic.location + ' ' + '(' + ap.clinic.address + ')'
        }
        return ''
    }

    return(
        <>
            <Row style={{display:"flex"}}>
                <h2 className="m-3 fav-title">{t("NAVBAR.appointments")}</h2>
                {message && (
                    <div className="form-group">
                        <div className="alert alert-danger" role="alert">
                            {t(message)}
                        </div>
                    </div>
                )}
                {appointments.length === 0 && !isLoading && <h4 className="m-3 no-fav">{t("USER.noApp")}</h4>}
            </Row>
            <Container>
                <div className="admin-info-container app-user-container">
                    {appointments.map((ap) => {
                        return (
                            <Card className="mb-3 app-card shadow">
                                <Card.Body>
                                    <Card.Title><b>{dateToString(ap.appointment, t)}</b></Card.Title>
                                    <Card.Text>
                                        {props.user === "patient"? <div>
                                            {t("USER.doc")}{getDoctorName(ap)}
                                        </div> : <div>
                                            {t("USER.patient")}{getPatientName(ap)}
                                        </div> }

                                        <div>
                                            {t("USER.clinic")} {getClinicName(ap)}
                                        </div>

                                    </Card.Text>
                                </Card.Body>
                                <Button className="remove-button remove-button-color shadow-sm"
                                        onClick={() => {deleteAppointment(ap.appointment)}}>
                                    {t('cancelButton')}
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
export default Appointments;