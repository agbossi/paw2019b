import React, {useEffect, useState} from 'react';
import SearchBar from "../SearchBar";
import {Card, Col, Container, Row} from "react-bootstrap";
import {Link} from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';


function Home() {

    const fetchLocations = () => {
       return ['moron', 'moreno', 'belgrano', 'centro', 'liniers', 'san telmo', 'quilmes', 'almagro', 'batman']
    }

    const fetchSpecialties = () => {
        return ['Clinica', 'Cardiologia', 'Neurologia', 'miau']
    }

    const fetchPrepaids = () => {
        return ['OSDE', 'Simeco']
    }

    const [doctors, setDoctors] = useState([])
    const [specialties, setSpecialties] = useState(fetchSpecialties())
    const [locations, setLocations] = useState(fetchLocations())
    const [prepaids, setPrepaids] = useState(fetchPrepaids())

    useEffect(() => {
        setDoctors(handleSearch())
    }, [])

    const handleSearch = (criteria) => {
        console.log(criteria)
        return [
            {
                "firstName": "Cecilia",
                "specialty": "Cardiology",
                "lastName": "Rodriguez",
                "license": "123313213166"
            },
            {
                "firstName": "Dr Evil",
                "specialty": "Evil",
                "lastName": "Evil",
                "license": "66666666"
            },
            {
                "firstName": "Jose",
                "specialty": "neurology",
                "lastName": "Hernandez",
                "license": "12332111"
            },
            {
                "firstName": "eeeee",
                "specialty": "eeeee",
                "lastName": "eeeeee",
                "license": "131443556"
            }
        ]
    }

    return (
        <>
            <Row>
                <Col>
                    <SearchBar handleSearch={handleSearch}
                               specialties={specialties}
                               locations={locations}
                               prepaids={prepaids}/>
                </Col>
                <Col>
                    <Container>
                        <div className="admin-info-container">
                            {doctors.map((doctor) => {
                                return (
                                    <Card className="mb-3"
                                          style={{color: "#000", width: '20rem', height: '30rem'}}
                                          key={doctor.license}>
                                        <Card.Img variant="top" src="/images/docpic.jpg" />
                                        <Card.Body>
                                            <Card.Title>{doctor.firstName + ' ' + doctor.lastName}</Card.Title>
                                            <Card.Text>
                                                {doctor.specialty}
                                            </Card.Text>
                                        </Card.Body>
                                        <Link className="btn btn-outline-dark btn-lg"
                                              role="button"
                                              to={'/home/' + doctor.license + '/doctorClinics'}>See clinics
                                        </Link>
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

export default Home