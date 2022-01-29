import React, { useState } from 'react';
import {Button, Col, Form, Modal, Row} from "react-bootstrap";
import '../CardContainer.css'
import DropDownList from "../DropDownList";


function DoctorAddModal(props) {
    const [show, setShow] = useState(false)
    const [selectedSpecialty, setSelectedSpecialty] = useState('')
    const [firstName, setFirstName] = useState('')
    const [lastName, setLastName] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [repeatPassword, setRepeatPassword] = useState('')
    const [license, setLicense] = useState('')
    const [phoneNumber, setPhoneNumber] = useState('')
    const [message, setMessage] = useState('')

    const handleSelect = (specialty) => {
        setSelectedSpecialty(specialty)
    }

    const handleShow = () =>{
        setShow(!show)
    }

    const handleAdd = (doctor) => {
        if (password !== repeatPassword)
            setMessage("Passwords don't match")
        else {
            setMessage('')
            setSelectedSpecialty("")
            props.handleAdd(doctor)
            handleShow()
        }
    }

    const onChange = (event) => {
        // eslint-disable-next-line default-case
        switch (event.target.id) {
            case "firstName":
                setFirstName(event.target.value)
                break;
            case "lastName":
                setLastName(event.target.value)
                break;
            case "license":
                setLicense(event.target.value)
                break;
            case "email":
                setEmail(event.target.value)
                break;
            case "password":
                setPassword(event.target.value)
                break;
            case "phoneNumber":
                setPhoneNumber(event.target.value)
                break;
            case "repeatPassword":
                setRepeatPassword(event.target.value)
                break;
        }
    }

    return (
        <>
            <Button variant="outline-secondary" onClick={() => handleShow()} size="lg" className="add-margin">
                Add Doctor
            </Button>
            <Modal show={show} onHide={() => handleShow()}>
                <Modal.Header closeButton>
                    <Modal.Title>Add Doctor</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Row>
                            <Col>
                                <Form.Group className="mb-3" controlId="firstName">
                                    <Form.Label>First Name</Form.Label>
                                    <Form.Control placeholder="Enter first name" onChange={onChange}/>
                                </Form.Group>
                            </Col>
                            <Col>
                                <Form.Group className="mb-3" controlId="lastName">
                                    <Form.Label>Last Name</Form.Label>
                                    <Form.Control placeholder="Enter last name" onChange={onChange}/>
                                </Form.Group>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <Form.Group className="mb-3" controlId="specialty">
                                    <Form.Label>Specialty: {selectedSpecialty}</Form.Label>
                                    <DropDownList iterable={props.specialties}
                                                  selectedElement=''
                                                  handleSelect={handleSelect}
                                                  elementType='Specialty'
                                                  id='specialty'
                                    />
                                </Form.Group>
                            </Col>
                            <Col>
                                <Form.Group className="mb-3" controlId="license">
                                    <Form.Label>License</Form.Label>
                                    <Form.Control placeholder="Enter license" onChange={onChange}/>
                                </Form.Group>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <Form.Group className="mb-3" controlId="phoneNumber">
                                    <Form.Label>Phone Number</Form.Label>
                                    <Form.Control placeholder="Enter phone number" onChange={onChange}/>
                                </Form.Group>
                            </Col>
                            <Col>
                                <Form.Group className="mb-3" controlId="email">
                                    <Form.Label>Email</Form.Label>
                                    <Form.Control type="email" placeholder="Enter email" onChange={onChange}/>
                                </Form.Group>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <Form.Group className="mb-3" controlId="password">
                                    <Form.Label>Password</Form.Label>
                                    <Form.Control type="password" placeholder="Password" onChange={onChange}/>
                                </Form.Group>
                            </Col>
                            <Col>
                                <Form.Group className="mb-3" controlId="repeatPassword">
                                    <Form.Label>Repeat Password</Form.Label>
                                    <Form.Control type="password" placeholder="Repeat password" onChange={onChange}/>
                                </Form.Group>
                            </Col>
                        </Row>
                        {message && (
                            <div className="form-group">
                                <div className="alert alert-danger" role="alert">
                                    {message}
                                </div>
                            </div>
                        )}
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={() => handleShow()}>
                        Close
                    </Button>
                    <Button className="doc-button-color" onClick={() => handleAdd({
                        firstName: firstName,
                        lastName: lastName,
                        email: email,
                        password: password,
                        repeatPassword: repeatPassword,
                        license: license,
                        specialty: selectedSpecialty,
                        phoneNumber: phoneNumber
                    })
                    }>
                        Add Doctor
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    )
}

export default DoctorAddModal