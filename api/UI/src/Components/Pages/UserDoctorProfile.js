import {useNavigate, useParams} from "react-router-dom";
import {Button, Col, Container, Form, Row, Table} from "react-bootstrap";
import {BASE_URL} from "../../Constants";
import React, {useEffect, useState} from "react";
import {useTranslation} from "react-i18next";
import DoctorCalls from "../../api/DoctorCalls";
import ImageCalls from "../../api/ImageCalls";
import AppointmentCalls from "../../api/AppointmentCalls";
import DropDownList from "../DropDownList";
import {dateToString, getMonth, getWeekDate} from "../../utils/dateHelper";
import PatientCalls from "../../api/PatientCalls";
import './UserDoctorProfile.css'
import docpic from "../../Assets/docpic.jpg";
import yesFav from "../../Assets/yesfav.png";
import noFav from "../../Assets/nofav.png";
import ClinicCalls from "../../api/ClinicCalls";
import Utils from "../../utils/paginationHelper";
import ApiCalls from "../../api/apiCalls";

function UserDoctorProfile(props) {
    const {license} = useParams()
    const {t} = useTranslation()
    const navigate = useNavigate()
    const [image, setImage] = useState(null)
    const [doctor, setDoctor] = useState({})
    const [message, setMessage] = useState("")
    const [schedule, setSchedule] = useState([])
    const [available, setAvailable] = useState([])
    const [clinics, setClinics] = useState([])
    const [selectedClinic, setSelectedClinic] = useState(null)
    const [selectedDateTime, setSelectedDateTime] = useState(null)
    const [isFavorite, setIsFavorite] = useState(false)

    const fetchDoctor = async () => {
        const response = await DoctorCalls.getDocByLicense(license);
        if (response && response.ok) {
            setDoctor(response.data)
        }
        return response.data
    }

    const fetchAvailableAppointments = async (email) => {
        const response = await DoctorCalls.getAvailableAppointments(email);
        if (response && response.ok) {
            setAvailable(response.data)
            setMessage("")
        }
    }

    const fetchAllClinics = async () => {
        let clinicsRet = []
        const response = await DoctorCalls.getAllClinics(license);
        if (response && response.ok) {
            const fetchPromises = response.data.map(dc => {
                return new Promise((resolve, reject) => {
                    fetchClinic(dc.clinicId).then(resp => resolve(resp.data))
                });
            })
            Promise.all(fetchPromises).then(clinics => {
                setClinics(clinics)
                setMessage("")
                clinicsRet = clinics
            })
        }
        return clinicsRet
    }

    const fetchClinic = async (id) => {
        return await ClinicCalls.getClinic(id)
    }

    const fetchImage = async () => {
        const response = await ImageCalls.getImage(license);
        if (response && response.ok) {
            setImage(response.data)
        }
        if (response.status === 404) {
            if (response.data === "image-not-found") {
                setImage(null)
            }
            if (response.data === "doctor-not-found") {
                setMessage("errors.noDocFound")
            }
        }
    }

    const fetchClinicSchedules = async () => {
        let clinicSchedules = []
        await DoctorCalls.getSchedule(license).then(resp => {
            if (resp && resp.ok) {
                let uniqueIds = [...new Set(resp.data.map(schedule => schedule.clinicId))];
                const fetchPromises = uniqueIds.map(clinicId => {
                    return new Promise((resolve, reject) => {
                        fetchClinic(clinicId).then(resp => {
                            resolve(resp.data)
                        })
                    });
                })
                Promise.all(fetchPromises).then(clinics => {
                    for(let i = 0;i < resp.data.length; i++) {
                        clinicSchedules.push({
                            schedule: resp.data[i],
                            clinic: clinics.filter(clinic => clinic.id === resp.data[i].clinicId)[0]
                        })
                    }
                    setSchedule(clinicSchedules)
                    setClinics(clinics)
                })
                setMessage("")
            }

        })
    }

    const fetchIsFavorite = async () => {
        if (localStorage.getItem('email') === null) {
            setIsFavorite(false)
        }
        const response = await PatientCalls.isFavorite(localStorage.getItem('email'), license)
        if (response && response.ok) {
            setIsFavorite(true)
            setMessage("")
        }
        if (response.status === 404) {
            if (response.data === "doctor-not-found")
                setMessage("errors.noDocFound")
            if (response.data === "not-favorite"){
                setIsFavorite(false)
                setMessage("")
            }
        }

    }


    useEffect(async () => {
        const doctor = await fetchDoctor();
        localStorage.setItem('path', "/" + license + "/profile")
        await fetchImage();
        //await fetchAllClinics();
        await fetchClinicSchedules();
        await fetchAvailableAppointments(doctor.email);
        await ApiCalls.handleInformation()
        if(localStorage.getItem('email') !== null) {
            await fetchIsFavorite();

        }
    },[])

    const getName = () => {
        if (doctor.firstName === undefined) {
            return ""
        }
        return doctor.firstName + " " + doctor.lastName
    }

    const handleMakeApp = async () => {
        if (localStorage.getItem('email') === null) {
            navigate("/paw-2019b-4/login")
        }
        if (selectedClinic === null) {
            setMessage("errors.selectTime")
        }
        if (selectedDateTime === null) {
            setMessage("errors.selectTime")
        }
        else {
            const data = {
                license: license,
                clinic: selectedClinic.id,
                patient: localStorage.getItem('email'),
                time: selectedDateTime.hour,
                day: selectedDateTime.day,
                month: selectedDateTime.month,
                year: selectedDateTime.year
            }
            const response = await AppointmentCalls.makeAppointment(data);
            if (response && response.ok) {
                navigate("/paw-2019b-4/appointments")
            }

            if (response.status === 400) {
                if (response.data === "past-date")
                    setMessage("errors.datePast")
            }
            if (response.status === 409) {
                if (response.data === "out-of-schedule")
                    setMessage("errors.outOfSchedule")
                if (response.data === "appointment-exists")
                    setMessage("errors.appointmentExists")
                if (response.data === "doctor-already-has-appointment")
                    setMessage("errors.doctorAppointment")
                if (response.data === "patient-already-has-appointment")
                    setMessage("errors.patientAppointment")
             }
        }

    }

    const makeFavorite = async () => {
        if (localStorage.getItem('email') === null) {
            navigate("/paw-2019b-4/login")
        }
        const response = await PatientCalls.addFavoriteDoctor(localStorage.getItem('email'), {license: license})
        if (response && response.ok) {
            setIsFavorite(true)
            setMessage("")
        }
        if (response.status === 404) {
            if (response.data === "doctor-not-found")
                setMessage("errors.noDocFound")
            if (response.data === "patient-not-found")
                setMessage("errors.noPatientEmail")
        }
        if (response.status === 409) {
            if (response.data === "favorite-exists")
                setMessage("errors.favExists")
        }
        if (response.status === 401) {
            localStorage.removeItem('token')
            localStorage.removeItem('role')
            localStorage.setItem('path', "/" + license + "/profile")
            navigate('/paw-2019b-4/login')
        }
    }

    const deleteFavorite = async () => {
        if (localStorage.getItem('email') === null) {
            navigate("/paw-2019b-4/login")
        }
        const response = await PatientCalls.deleteFavoriteDoctor(localStorage.getItem('email'), license)
        if (response && response.ok) {
            setIsFavorite(false)
            setMessage("")
        }
        if (response.status === 404) {
            if (response.data === "doctor-not-found")
                setMessage("errors.noDocFound")
            if (response.data === "patient-not-found")
                setMessage("errors.noPatientEmail")
        }
    }

    const getEmail = () => {
        if (doctor.email === undefined || doctor.email === null) {
            return ""
        }
        return doctor.email
    }

    const getRow = (row) => {
        const days = []
        if(schedule.length !== 0) {
            const rowSchedule = schedule.filter(schedule => schedule.schedule.hour === row)
            for (let i = 1; i < 8; i++) {
                const scheduleDay = rowSchedule.filter(schedule => (schedule.schedule.day) === i)
                if (scheduleDay.length > 0) {
                    days[i - 1] = scheduleDay[0].clinic
                } else {
                    days[i - 1] = null
                }

            }
        }
        return days
    }

    const handleSelectClinic = (clinicName) => {
        const selected = clinics.filter(dc => dc.name + " (" + dc.location + ")" === clinicName)
        setSelectedClinic(selected[0])
    }

    const handleSelectDateTime = (dateFormated) => {
        const selected = getDateTimes().filter(date => t(getWeekDate(date.dayWeek)) + " " + date.day + " " + t(getMonth(date.month)) + ", " + date.year +
            " " + date.hour + ":00" === dateFormated)
        setSelectedDateTime(selected[0])
    }

    const getAllRows = () => {
        const hours = []
        for (let i = 9; i < 19; i++) {
            hours[i] = getRow(i)
        }
        return hours
    }

    const getDateTimes = () => {
        if (selectedClinic === null) {
            return []
        }
        return available.filter(appointment => appointment.clinicId === selectedClinic.id)
    }

    return (
        <>
            <Container>
                <Row className="mt-3">

                    <Col className="info-col-user">
                        <h4 style={{display: "flex"}}>
                            <div style={{alignSelf: "center"}}>{t('USER.dataProfile')}</div>
                            {isFavorite ? <Button className="m-2 fav-button" onClick={deleteFavorite}>
                                <img src={yesFav}/>
                            </Button>:<Button className="m-2 fav-button" onClick={makeFavorite}>
                                <img src={noFav}/>
                            </Button>}
                        </h4>
                        <div className="user-info-label">
                            <b>{t('FORM.name')}:</b> {getName()}
                        </div>
                        <div className="user-info-label">
                            <b>{t('FORM.email')}:</b> {getEmail()}
                        </div>
                        <div className="user-info-label">
                            <b>{t('DOC.license')}:</b> {doctor.license}
                        </div>
                        <div className="user-info-label">
                            <b>{t('FORM.phoneNumber')}:</b> {doctor.phoneNumber}
                        </div>
                        <div className="user-info-label">
                            <b>{t('ADMIN.specialty')}:</b> {doctor.specialty}
                        </div>
                    </Col>
                    <Col className="img-col-user mx-3">
                        <img className="user-img-size"
                             src={image === null?
                                 {docpic}: BASE_URL + "/doctors/" + license +"/image"} />
                    </Col>
                </Row>
                <hr/>
                <Row>
                    <Col className="col-8">
                        <Table className="user-schedule-table">
                            <thead>
                            <tr>
                                <th width="9%">{t('CAL.hour')}</th>
                                <th width="13%">{t('CAL.mon')}</th>
                                <th width="13%">{t('CAL.tue')}</th>
                                <th width="13%">{t('CAL.wed')}</th>
                                <th width="13%">{t('CAL.thu')}</th>
                                <th width="13%">{t('CAL.fri')}</th>
                                <th width="13%">{t('CAL.sat')}</th>
                                <th width="13%">{t('CAL.sun')}</th>
                            </tr>
                            </thead>
                            <tbody>
                            {getAllRows().map((row, index) => {
                                return (
                                    <tr>
                                        <td>{index}:00</td>
                                        {row.map((clinic => {
                                            if (clinic === null) {
                                                return <td/>
                                            } else {
                                                return (
                                                    <td className="user-clinic">{clinic.name} ({clinic.location})</td>
                                                )
                                            }

                                        }))}
                                    </tr>
                                )
                            })}
                            </tbody>
                        </Table>
                    </Col>
                    <Col>
                        <h4 className="m-3">
                            {t("actions.makeApp")}
                        </h4>
                        <Form.Group className="m-3">
                            <Form.Label><b>{t("CLINIC.clinic")}</b>: {selectedClinic === null? "":
                                selectedClinic.name + " - " + selectedClinic.location}</Form.Label>
                            <DropDownList iterable={clinics.map(dc => dc.name + " (" + dc.location + ")")}
                                          selectedElement=''
                                          handleSelect={handleSelectClinic}
                                          elementType={t('FORM.selectClinic')}
                                          id='clinic'/>
                        </Form.Group>
                        <Form.Group className="m-3">
                            <Form.Label><b>{t("FORM.dateTime")}</b>: {selectedDateTime === null? "":
                                dateToString(selectedDateTime, t)}</Form.Label>
                            <DropDownList iterable={getDateTimes().map(date => dateToString(date, t))}
                                          selectedElement=''
                                          handleSelect={handleSelectDateTime}
                                          elementType={t('FORM.selectDateTime')}
                                          id='time'/>
                        </Form.Group>
                        <Button className="doc-button-color m-5" onClick={handleMakeApp}>
                            {t("actions.makeApp")}
                        </Button>
                        {message && (
                            <div className="form-group">
                                <div className="alert alert-danger" role="alert">
                                    {t(message)}
                                </div>
                            </div>
                        )}
                    </Col>

                </Row>
            </Container>
        </>
    )

}

export default UserDoctorProfile