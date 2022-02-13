import React, {useEffect, useState} from "react";
import {useTranslation} from "react-i18next";
import PatientCalls from "../../api/PatientCalls";
import {Button, Card, Col, Container, Row} from "react-bootstrap";
import {Link} from "react-router-dom";

function Favorites() {
    const [doctors, setDoctors] = useState([])
    const {t} = useTranslation()


    const fetchFavorites = async () => {
        let id = localStorage.getItem('email')
        const response = await PatientCalls.getFavoriteDoctors(id)
        if (response && response.ok) {
            setDoctors(response.data)
        }
    }

    const removeFromFavorites = async (license) => {
        let id = localStorage.getItem('email')
        const response = await PatientCalls.deleteFavoriteDoctor(id, license)
        await fetchFavorites()
    }

    useEffect(async () => {
        await fetchFavorites();
    }, [])

    return (
        <>
            <Row>
                {doctors.length === 0 && <h2>{t("USER.emptyFavorites")}</h2>}
                <Col xs={9}>
                    <Container>
                        <div className="admin-info-container search-doctor-container">
                            {doctors.map((doctor) => {
                                return (
                                    <Card className="mb-3 doc-card shadow"
                                          style={{color: "#000", width: '20rem', height: '8rem'}}
                                          key={doctor.license}>
                                        <Card.Body className="card-body-doc">
                                            <Card.Title>{doctor.user.firstName + ' ' + doctor.user.lastName}</Card.Title>
                                            <Card.Text>
                                                {doctor.specialty}
                                            </Card.Text>
                                        </Card.Body>
                                        <Link className="doc-button-color btn m-1"
                                              role="button"
                                              to={'/home/' + doctor.license + '/profile'}>{t("USER.seeProfile")}
                                        </Link>
                                        <div className="buttons-div">
                                            <Button className="edit-remove-button remove-button-color shadow-sm"
                                                    onClick={() => removeFromFavorites(doctor.license)}>
                                                {t("deleteButton")}
                                            </Button>
                                        </div>
                                    </Card>
                                )
                            })}
                        </div>
                    </Container>
                </Col>
            </Row>
        </>
    )
}

export default Favorites;