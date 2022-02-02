// import {Card, Container, ListGroup, Row, Col} from "react-bootstrap";
// import '../CardContainer.css'
// import {Link, useParams} from "react-router-dom";
// import React, { useState, useEffect } from 'react';
//
// function userDoctorClinics(props) {
//
//     const fetchDoctorClinics = (license) => {
//         return [
//             {
//                 doctor: {
//                     "firstName": "Cecilia",
//                     "lastName": "Rodriguez",
//                     "license": "123313213166",
//                     "email": "cecilia@doctor.com",
//                     "phoneNumber":  "1565676787",
//                     "specialty": "Dermatology",
//                 },
//                 clinic: {
//                     "id": 1,
//                     "address": "calle falsa 853",
//                     "name": "asdf",
//                     "location": "moron",
//                     "prepaids": "URL"
//                 },
//                 consultPrice: 3,
//                 schedules: 'URI',
//                 appointments: 'URI',
//                 week: [[]]
//             },
//         ]
//     }
//
//     const fetchDoctor = () => {
//         return {
//             "firstName": "Cecilia",
//             "lastName": "Rodriguez",
//             "license": "123313213166",
//             "email": "cecilia@doctor.com",
//             "phoneNumber":  "1565676787",
//             "specialty": "Dermatology",
//         }
//     }
//
//     let {license} = useParams()
//     const [doctorClinics, setDoctorClinics] = useState(fetchDoctorClinics(license))
//     const [doctor, setDoctor] = useState(fetchDoctor())
//     const [favorite, setFavorite] = useState(false)
//
//     const handleFavoriteClick = () => {
//         console.log('favorite handler')
//         console.log(favorite)
//         setFavorite(!favorite)
//     }
//
//     return (
//         <>
//             <Row>
//                 <Col>
//                     <Card style={{ width: '18rem' }}>
//                         <Card.Header>
//                             {doctor.firstName + ' ' + doctor.lastName + '  '}
//                             <i className={favorite ? "fas fa-heart" : "far fa-heart"} onClick={() => handleFavoriteClick()}/>
//                         </Card.Header>
//                         <ListGroup variant="flush">
//                             <ListGroup.Item>{'License: ' + doctor.license}</ListGroup.Item>
//                             <ListGroup.Item>{'Specialty: ' + doctor.specialty}</ListGroup.Item>
//                             <ListGroup.Item>{'Phone Number: ' + doctor.phoneNumber}</ListGroup.Item>
//                             <ListGroup.Item>{'email: ' + doctor.email}</ListGroup.Item>
//                         </ListGroup>
//                         <Card.Footer className="text-muted">
//                             Click on any clinic to check out the doctor's schedule and book an appointment
//                         </Card.Footer>
//                     </Card>
//                 </Col>
//                 <Col>
//                     <Container>
//                         <div className="admin-info-container">
//                             {doctorClinics.map(( doctorClinic, index) => {
//                                 return (
//                                     <Card className="mb-3" style={{color: "#000", width: '20rem', height: '15rem'}}
//                                           key={doctorClinic.clinic.id}>
//                                         <Card.Body>
//                                             <Card.Title>{doctorClinic.clinic.name}</Card.Title>
//                                             <Card.Text>
//                                                 {doctorClinic.clinic.address + ' (' + doctorClinic.clinic.location + ')'}
//                                             </Card.Text>
//                                         </Card.Body>
//                                         <Link className="btn btn-outline-dark btn-lg"
//                                               role="button"
//                                               to={'home/' + doctor.license + '/doctorClinics/' + doctorClinic.clinic.id}>
//                                             See Appointments
//                                         </Link>
//                                     </Card>
//                                 )
//                             })}
//                         </div>
//                     </Container>
//                 </Col>
//             </Row>
//         </>
//     )
// }
//
// export default userDoctorClinics