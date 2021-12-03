import React, {Component} from 'react';
import {Link} from "react-router-dom";
import {Card, Col, Row} from "react-bootstrap";
class AdminHome extends Component {

    render() {
        return (
            <>
                <container>
                    <Row>
                        <Col>
                            <Card style={{color: "#000", width: '20rem', height: '27rem', marginLeft: '3.5rem', marginRight: '1rem'}}>
                                <Card.Img variant="top" src="http://localhost:3000/src/Resources/doctors.jpg" />
                                <Card.Body>
                                    <Card.Title>Doctors</Card.Title>
                                    <Link className="btn btn-outline-dark btn-lg"
                                          role="button"
                                          to="/doctors">See doctors
                                    </Link>
                                </Card.Body>
                            </Card>
                        </Col>
                        <Col>
                            <Card className="mb-3" style={{color: "#000", width: '20rem', height: '27rem', marginLeft: '2.5rem', marginRight: '1rem'}}>
                                <Card.Img variant="top" src="http://localhost:3000/src/Resources/clinics.jpeg" />
                                <Card.Body>
                                    <Card.Title>Clinics</Card.Title>
                                    <Link className="btn btn-outline-dark btn-lg"
                                          role="button"
                                          to="/clinics">See clinics
                                    </Link>
                                </Card.Body>
                            </Card>
                        </Col>
                        <Col>
                            <Card className="mb-3" style={{color: "#000", width: '20rem', height: '27rem', marginLeft: '1rem'}}>
                                <Card.Img variant="top" src="http://localhost:3000/src/Resources/locations.jpeg" />
                                <Card.Body>
                                    <Card.Title>Locations</Card.Title>
                                    <Link className="btn btn-outline-dark btn-lg"
                                          role="button"
                                          to="/locations">See locations
                                    </Link>
                                </Card.Body>
                            </Card>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <Card className="mb-3" style={{color: "#000", width: '20rem', height: '27rem', marginLeft: '3.5rem', marginRight: '1rem'}}>
                                <Card.Img variant="top" src="http://localhost:3000/src/Resources/specialties.jpg" />
                                <Card.Body>
                                    <Card.Title>Specialties</Card.Title>
                                    <Link className="btn btn-outline-dark btn-lg"
                                          role="button"
                                          to="/specialties">See specialties
                                    </Link>
                                </Card.Body>
                            </Card>
                        </Col>
                        <Col>
                            <Card className="mb-3" style={{color: "#000", width: '20rem', height: '27rem', marginLeft: '2.5rem', marginRight: '1rem'}}>
                                <Card.Img variant="top" src="http://localhost:3000/src/Resources/prepaids.jpeg" />
                                <Card.Body>
                                    <Card.Title>Prepaids</Card.Title>
                                    <Link className="btn btn-outline-dark btn-lg"
                                          role="button"
                                          to="/prepaids">See prepaids
                                    </Link>
                                </Card.Body>
                            </Card>
                        </Col>
                        <Col>
                            <Card className="mb-3" style={{color: "#000", width: '20rem', height: '27rem', marginLeft: '1rem'}}>
                                <Card.Img variant="top" src="http://localhost:3000/src/Resources/prepaidsToClinics.jpg" />
                                <Card.Body>
                                    <Card.Title>prepaids to clinics</Card.Title>
                                    <Link className="btn btn-outline-dark btn-lg"
                                          role="button"
                                          to="/prepaidsToClinics">See prepaids to clinics
                                    </Link>
                                </Card.Body>
                            </Card>
                        </Col>
                    </Row>
                </container>
            </>
        )
    }
}

export default AdminHome