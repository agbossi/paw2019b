import React, {useEffect, useState} from "react";
import DoctorCalls from "../../api/DoctorCalls";
import ClinicCalls from "../../api/ClinicCalls";
import {Button, Card, Container, Row} from "react-bootstrap";
import {useTranslation} from "react-i18next";
import {Link} from "react-router-dom";
import DoctorClinicAddModal from "../Modals/DoctorClinicAddModal";

function DoctorClinics(props) {
    const [clinics, setClinics] = useState([]);
    const [allClinics, setAllClinics] = useState([])
    const [allDoctorClinics, setAllDoctorClinics] = useState([])
    const [page, setPage] = useState(0);
    const [maxPage, setMaxPage] = useState(0);
    const [message, setMessage] = useState("")
    const license = localStorage.getItem('license');
    const {t} = useTranslation()

    const fetchDoctorsClinics = async (pag) => {
        const response = await DoctorCalls.getClinics(license, pag)
        if (response && response.ok) {
            setClinics(response.data)
            setMaxPage(Number(response.headers.xMaxPage))
            setMessage("")
            console.log(response.data)
        }
        if(response.status === 404) {
            setMessage("docLoggedNotFound")
        }
    }

    const fetchAllDoctorClinics = async () => {
        const response = await DoctorCalls.getAllClinics(license)
        if (response && response.ok) {
            setAllDoctorClinics(response.data)
            setMessage("")
        }
        if(response.status === 404) {
            setMessage("docLoggedNotFound")
        }
    }

    const fetchAllClinics = async () => {
        const response = await ClinicCalls.getAllClinics();
        if (response && response.ok) {
            setAllClinics(response.data);
        }

    }

    const handleAdd = async (newDocClinic) => {
        const response = await DoctorCalls.addDoctorToClinic(newDocClinic, license);
        if (response && response.ok) {
            await fetchDoctorsClinics(page)
        }
        if (response.status === 404) {
            if (response.data === "doctor-not-found") {
                setMessage("docLoggedNotFound")
            }
            if (response.data === "clinic-not-found") {
                setMessage("clinicNotFound")
            }
        }
    }

    useEffect(async () => {
        await fetchDoctorsClinics(page);
        await fetchAllClinics();
        await fetchAllDoctorClinics();
    },[])

    return (
        <>
            <DoctorClinicAddModal
                handleAdd={handleAdd}
                allClinics={allClinics}
                clinics={allDoctorClinics.map(clinic => clinic.id)}
            />
            {message && (
                <div className="form-group">
                    <div className="alert alert-danger" role="alert">
                        {t(message)}
                    </div>
                </div>
            )}
            <Container>
                <div className="admin-info-container admin-clinic-container">
                    {clinics.map((dc) => {
                        return (
                            <Card className="mb-3 shadow" style={{color: "#000", width: '20rem', height: '16.5rem'}} key={dc.id}>
                                <Card.Body>
                                    <Card.Title>{dc.clinic.name}</Card.Title>
                                    <Card.Text>
                                        {dc.clinic.address + ' (' + dc.clinic.location + ')'}
                                    </Card.Text>
                                    <Card.Text>
                                        {t('DOC.price')}: {String(dc.consultPrice)}
                                        <Button className="mx-3 shadow-sm doc-button-color">
                                            <i className="far fa-edit"/>
                                        </Button>
                                    </Card.Text>
                                    <Link className="btn btn-outline-dark btn-lg see-prepaid-button shadow-sm"
                                          role="button"
                                          to={'/doctor/clinics/' + dc.clinic.id + '/schedule'}>{t('scheduleButton')}
                                    </Link>
                                </Card.Body>
                                <div className="buttons-div">
                                    <Button className="edit-remove-button remove-button-color shadow-sm">
                                        {t("deleteButton")}
                                    </Button>
                                </div>

                            </Card>
                        )
                    })}
                </div>
            </Container>
        </>
    )
}

export default DoctorClinics