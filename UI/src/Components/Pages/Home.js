import React, {useEffect, useState} from 'react';
import SearchBar from "../SearchBar";
import {Button, Card, Col, Container, Row} from "react-bootstrap";
import {Link} from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import DoctorCalls from "../../api/DoctorCalls";
import './home.css'
import {useTranslation} from "react-i18next";


function Home(props) {
    const [doctors, setDoctors] = useState([])
    const [specialties, setSpecialties] = useState([])
    const [locations, setLocations] = useState([])
    const [prepaids, setPrepaids] = useState([])
    const [message, setMessage] = useState("")
    const [searchCriteria, setSearchCriteria] = useState(null)
    const [maxPage, setMaxPage] = useState(0)
    const [page, setPage] = useState(0)
    const {t} = useTranslation()

    const fetchAllDoctorsWithAvailability = async (page) => {
        const response = await DoctorCalls.searchDocs(page, null, null,
            null, null, null , null)
        if (response && response.ok) {
            setDoctors(response.data)
            setMaxPage(Number(response.headers.xMaxPage))
            setMessage("")
        }
    }

    useEffect(async () => {
        await fetchAllDoctorsWithAvailability(page);
    }, [])

    const nextPage = async () => {
        const newPage = page + 1
        setPage(newPage)
        setMessage("")
        await handleSearch(searchCriteria, newPage)

    }
    const prevPage = async () => {
        const newPage = page - 1
        setPage(newPage)
        setMessage("")
        await handleSearch(searchCriteria, newPage)
    }

    const renderPrevButton = () => {
        if (page !== 0) {
            return <Button className="doc-button doc-button-color shadow-sm"
                           onClick={() => prevPage()}>{t('prevButton')}</Button>
        }
    }

    const renderNextButton = () => {
        if (page < maxPage - 1) {
            return <Button className="doc-button doc-button-color shadow-sm"
                           onClick={() => nextPage()}>{t('nextButton')}</Button>
        }
    }

    const handleSearch = async (criteria, pag) => {
        setSearchCriteria(criteria);
        if (criteria == null) {
            await fetchAllDoctorsWithAvailability(pag);
            return;
        }
        const response = await DoctorCalls.searchDocs(pag, criteria.location, criteria.specialty,
            criteria.firstName, criteria.lastName, criteria.consultPrice , criteria.prepaid)
        if (response && response.ok) {
            setDoctors(response.data)
            setMaxPage(Number(response.headers.xMaxPage))
            setMessage("")
        }
    }

    return (
        <>
            <Row>
                <Col>
                    <SearchBar handleSearch={handleSearch}/>
                </Col>
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
                                              to={doctor.license + '/profile'}>{t("USER.seeProfile")}
                                        </Link>
                                    </Card>
                                )
                            })}
                        </div>
                    </Container>
                    {renderPrevButton()}
                    {renderNextButton()}
                </Col>
            </Row>
        </>
    )
}

export default Home