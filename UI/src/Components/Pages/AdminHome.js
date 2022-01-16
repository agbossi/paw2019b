import React, {Component} from 'react';
import {Link} from "react-router-dom";
import {Card, Col, Row} from "react-bootstrap";

import './AdminHome.css';

class AdminHome extends Component {

    render() {
        return (
            <>
                <container>
                    <Row className="admin_row">
                        <Col>
                            <Card className="admin_card" style={{marginLeft: '3.5rem', marginRight: '1rem'}}>
                                <Card.Img variant="top" className="card_img" src="/images/doctors.jpg" />
                                <Card.Body className="card_body">
                                    <Card.Title style={{verticalAlign: "bottom"}}>Doctors</Card.Title>
                                    <Link className="btn btn-outline-dark btn-lg"
                                          role="button"
                                          to="doctors">See doctors
                                    </Link>
                                </Card.Body>
                            </Card>
                        </Col>
                        <Col>
                            <Card className="mb-3 admin_card" style={{marginLeft: '2.5rem', marginRight: '1rem'}}>
                                <Card.Img variant="top" className="card_img" src="/images/clinics.jpeg" />
                                <Card.Body className="card_body">
                                    <Card.Title>Clinics</Card.Title>
                                    <Link className="btn btn-outline-dark btn-lg"
                                          role="button"
                                          to="clinics">See clinics
                                    </Link>
                                </Card.Body>
                            </Card>
                        </Col>
                        <Col>
                            <Card className="mb-3 admin_card" style={{ marginLeft: '1rem'}}>
                                <Card.Img variant="top" className="card_img" src="/images/locations.jpeg" />
                                <Card.Body className="card_body">
                                    <Card.Title>Locations</Card.Title>
                                    <Link className="btn btn-outline-dark btn-lg"
                                          role="button"
                                          to="locations">See locations
                                    </Link>
                                </Card.Body>
                            </Card>
                        </Col>
                    </Row>
                    <Row className="admin_row">
                        <Col>
                            <Card className="mb-3 admin_card" style={{marginLeft: '3.5rem', marginRight: '1rem'}}>
                                <Card.Img className="card_img" variant="top" src="/images/specialties.jpg" />
                                <Card.Body className="card_body">
                                    <Card.Title>Specialties</Card.Title>
                                    <Link className="btn btn-outline-dark btn-lg"
                                          role="button"
                                          to="specialties">See specialties
                                    </Link>
                                </Card.Body>
                            </Card>
                        </Col>
                        <Col>
                            <Card className="mb-3 admin_card" style={{marginLeft: '2.5rem', marginRight: '1rem'}}>
                                <Card.Img variant="top" className="card_img" src="/images/prepaids.jpeg" />
                                <Card.Body className="card_body">
                                    <Card.Title>Prepaids</Card.Title>
                                    <Link className="btn btn-outline-dark btn-lg"
                                          role="button"
                                          to="prepaids">See prepaids
                                    </Link>
                                </Card.Body>
                            </Card>
                        </Col>
                        <Col></Col>
                    </Row>
                </container>
            </>
        )
    }
}

export default AdminHome