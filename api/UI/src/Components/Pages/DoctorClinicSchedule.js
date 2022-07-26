import React, {useEffect, useState} from "react";
import {useTranslation} from "react-i18next";
import {Table} from "react-bootstrap";
import './DoctorClinicSchedule.css'
import DoctorCalls from "../../api/DoctorCalls";
import ClinicCalls from "../../api/ClinicCalls";
import {useNavigate, useParams} from "react-router-dom";
import ModifyScheduleModal from "../Modals/ModifyScheduleModal";
import ApiCalls from "../../api/apiCalls";

function DoctorClinicSchedule(props) {
    const [schedule, setSchedule] = useState([]);
    const [message, setMessage] = useState("");
    const [clinic, setClinic] = useState(null);
    const [clinics, setClinics] = useState([]);
    const [doctor, setDoctor] = useState(null);
    const {t} = useTranslation();
    const navigate = useNavigate()
    let {id} = useParams();
    let {license} = useParams()

    const fetchDoctor = async () => {
        //const email = localStorage.getItem('email');
        //const response = await DoctorCalls.getDocByEmail(email);
        const response = await DoctorCalls.getDocByLicense(license);
        if (response && response.ok) {
            setDoctor(response.data);
            setMessage("")
        }
    }

    const fetchClinic = async (id) => {
        return await ClinicCalls.getClinic(id)
    }

    const fetchSchedule = async () => {
        const response = await DoctorCalls.getSchedule(license)
        if (response && response.ok) {
            setSchedule(response.data)
            setMessage("")
        }
    }

    const getClinicName = () => {
        if (clinic === null || clinic === undefined)
            return "";
        return clinic.name
    }

    const fetchClinicSchedules = async () => {
        let clinicSchedules = []
        await DoctorCalls.getSchedule(license).then(resp => {
            if (resp && resp.ok) {
                let uniqueIds = [...new Set(resp.data.map(schedule => schedule.clinicId))];
                const fetchPromises = uniqueIds.map(clinicId => {
                    return new Promise((resolve, reject) => {
                        fetchClinic(clinicId).then(resp2 => {
                            resolve(resp2.data)
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

    const getAllRows = () => {
        const hours = []
        for (let i = 9; i < 19; i++) {
            hours[i] = getRow(i)
        }
        return hours
    }

    useEffect(async () => {
        await ApiCalls.handleInformation()
        await fetchClinic(id).then(response => setClinic(response.data));
        await fetchDoctor();
        await fetchClinicSchedules();
        //await fetchSchedule()
        localStorage.setItem('path', '/doctor/' + license + '/clinics/' + id + '/schedule')
    },[])

    const handleAdd = async (day, hour) => {
        const response = await DoctorCalls.addSchedule(license, id, day, hour)
        if (response && response.ok) {
            //await fetchSchedule();
            await fetchClinicSchedules();
            setMessage("")
        }
        if (response.status === 404) {
            if (response.data === "doctor-clinic-not-found")
                setMessage("errors.docClinicNotFound")
        }
        if (response.status === 409) {
            if (response.data === "schedule-exists")
                setMessage("errors.scheduleExists")
        }

    }

    const handleDelete = async (day, hour) => {
        const response = await DoctorCalls.deleteSchedule(license, id, day, hour)
        if (response && response.ok) {
            //await fetchSchedule();
            await fetchClinicSchedules();
            setMessage("")
        }
        if (response.status === 404) {
            if (response.data === "doctor-clinic-not-found")
                setMessage("errors.docClinicNotFound")
            if (response.data === "schedule-not-found")
                setMessage("errors.scheduleNotFoundDelete")
            if (response.data === "schedule-clinic-not-found")
                setMessage("errors.scheduleOtherClinicDelete")
        }
        if (response.status === 409) {
            if (response.data === "schedule-exists")
                setMessage("errors.scheduleExists")
        }

        if (response.status === 401)
            handleUnauth()
    }

    const handleUnauth = () => {
        localStorage.removeItem('token')
        localStorage.removeItem('role')
        localStorage.removeItem('license')
        localStorage.removeItem('firstName')
        localStorage.removeItem('lastName')
        localStorage.removeItem('specialty')
        navigate('/paw-2019b-4/login')
    }

    return (
        <div className="content">
            <h3 className="clinic-prepaid-title">{t("CLINIC.clinic")} {getClinicName()}</h3>
            {message && (
                <div className="form-group">
                    <div className="alert alert-danger" role="alert">
                        {t(message)}
                    </div>
                </div>
            )}
            <Table className="schedule-table">
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
                                }
                                if (clinic.id === Number(id)) {
                                    return (
                                        <td className="current-clinic"><b>{clinic.name} ({clinic.location})</b></td>
                                    )
                                } else {
                                    return (
                                        <td className="other-clinic">{clinic.name} ({clinic.location})</td>
                                    )
                                }

                            }))}
                        </tr>
                    )
                })}
                </tbody>
            </Table>
            <div>
                <ModifyScheduleModal handleClick={handleAdd}
                                     action="add"
                />
                <ModifyScheduleModal handleClick={handleDelete}
                                     action="delete"
                />
            </div>
        </div>
    );
}

export default DoctorClinicSchedule;