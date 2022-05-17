import React, { useState } from 'react';
import {Button, Col, Form, Modal, Row} from "react-bootstrap";
import '../CardContainer.css'
import DropDownList from "../DropDownList";
import {useTranslation} from "react-i18next";
import validation from "../../utils/validationHelper";



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
    const { t } = useTranslation();

    const [firstNameErrors, setFirstNameErrors] = useState([])
    const [lastNameErrors, setLastNameErrors] = useState([])
    const [emailErrors, setEmailErrors] = useState([])
    const [licenseErrors, setLicenseErrors] = useState([])
    const [passwordErrors, setPasswordErrors] = useState([])
    const [repeatPasswordErrors, setRepeatPasswordErrors] = useState([])
    const [phoneNumberErrors, setphoneNumberErrors] = useState([])
    const [invalidForm, setInvalidForm] = useState(true)

    const handleSelect = (specialty) => {
        setSelectedSpecialty(specialty)
    }

    const handleShow = () =>{
        setShow(!show)
    }

    const isPresent = (value) => {
        let is = true
        if(!value) {
            is = false
        }
        return is
    }

    const handleAdd = (doctor) => {
        if(!isPresent(doctor.firstName) || !isPresent(doctor.email) || !isPresent(doctor.lastName) || !isPresent(doctor.license) ||
            !isPresent(doctor.phoneNumber) || !isPresent(doctor.password) || !isPresent(doctor.specialty)) {
            setMessage(t('errors.incompleteForm'))
        } else {
            setMessage('')
            setSelectedSpecialty("")
            props.handleAdd(doctor)
            handleShow()
        }
    }

    const onChange = (event) => {
        // eslint-disable-next-line default-case
        let error = false
        let errors = []
        switch (event.target.id) {
            case "firstName":
                setFirstName(event.target.value)
                error = error || validation.requiredAlpha(event.target.value, errors, "firstName", t)
                setFirstNameErrors(errors)
                break;
            case "lastName":
                setLastName(event.target.value)
                error = error || validation.requiredAlpha(event.target.value, errors, "lastName", t)
                setLastNameErrors(errors)
                break;
            case "license":
                setLicense(event.target.value)
                error = error || validation.requiredNumeric(event.target.value, errors, "license", t)
                setLicenseErrors(errors)
                break;
            case "email":
                setEmail(event.target.value)
                error = error || validation.requiredEmail(event.target.value, errors, "email", t)
                setEmailErrors(errors)
                break;
            case "password":
                setPassword(event.target.value)
                let len = validation.requiredLength(event.target.value, errors, "password", 8, 20, t)
                let match = !validation.passwordMatch(event.target.value, errors, repeatPassword, t)
                error = error || len || match
                setPasswordErrors(errors)
                break;
            case "phoneNumber":
                setPhoneNumber(event.target.value)
                error = error || validation.requiredLength(event.target.value, errors, "phoneNumber", 8, 11, t)
                setphoneNumberErrors(errors)
                break;
            case "repeatPassword":
                setRepeatPassword(event.target.value)
                error = error || !validation.passwordMatch(password, errors, event.target.value, t)
                setRepeatPasswordErrors(errors)
                break;
        }
        setInvalidForm(error)
    }

    return (
        <>
            <Button variant="outline-secondary" onClick={() => handleShow()} size="lg" className="add-margin">
                {t("DOC.addDoc")}
            </Button>
            <Modal show={show} onHide={() => handleShow()}>
                <Modal.Header closeButton>
                    <Modal.Title>{t("DOC.addDoc")}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Row>
                            <Col>
                                <Form.Group className="mb-3" controlId="firstName">
                                    <Form.Label>{t("FORM.firstName")}</Form.Label>
                                    <Form.Control placeholder={t("FORM.enterFirstName")}
                                                  value={firstName}
                                                  onChange={onChange}/>
                                    {firstNameErrors.length !== 0 && (
                                        <div className="form-group">
                                            <div className="alert alert-danger" role="alert">
                                                <ul>
                                                    {firstNameErrors.map((error) => {
                                                        return (
                                                            <li>{error}</li>
                                                        )
                                                    })}
                                                </ul>
                                            </div>
                                        </div>
                                    )}
                                </Form.Group>
                            </Col>
                            <Col>
                                <Form.Group className="mb-3" controlId="lastName">
                                    <Form.Label>{t("FORM.lastName")}</Form.Label>
                                    <Form.Control placeholder={t("FORM.enterLastName")}
                                                  value={lastName}
                                                  onChange={onChange}/>
                                    {lastNameErrors.length !== 0 && (
                                        <div className="form-group">
                                            <div className="alert alert-danger" role="alert">
                                                <ul>
                                                    {lastNameErrors.map((error) => {
                                                        return (
                                                            <li>{error}</li>
                                                        )
                                                    })}
                                                </ul>
                                            </div>
                                        </div>
                                    )}
                                </Form.Group>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <Form.Group className="mb-3" controlId="specialty">
                                    <Form.Label>{t("FORM.specialty")} {selectedSpecialty}</Form.Label>
                                    <DropDownList iterable={props.specialties}
                                                  selectedElement=''
                                                  handleSelect={handleSelect}
                                                  elementType={t("FORM.selectSpecialty")}
                                                  id='specialty'
                                    />
                                </Form.Group>
                            </Col>
                            <Col>
                                <Form.Group className="mb-3" controlId="license">
                                    <Form.Label>{t("DOC.license")}</Form.Label>
                                    <Form.Control placeholder={t("FORM.enterLicense")}
                                                  value={license}
                                                  onChange={onChange}/>
                                    {licenseErrors.length !== 0 && (
                                        <div className="form-group">
                                            <div className="alert alert-danger" role="alert">
                                                <ul>
                                                    {licenseErrors.map((error) => {
                                                        return (
                                                            <li>{error}</li>
                                                        )
                                                    })}
                                                </ul>
                                            </div>
                                        </div>
                                    )}
                                </Form.Group>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <Form.Group className="mb-3" controlId="phoneNumber">
                                    <Form.Label>{t("FORM.phoneNumber")}</Form.Label>
                                    <Form.Control placeholder={t("FORM.enterPhone")}
                                                  value={phoneNumber}
                                                  onChange={onChange}/>
                                    {phoneNumberErrors.length !== 0 && (
                                        <div className="form-group">
                                            <div className="alert alert-danger" role="alert">
                                                <ul>
                                                    {phoneNumberErrors.map((error) => {
                                                        return (
                                                            <li>{error}</li>
                                                        )
                                                    })}
                                                </ul>
                                            </div>
                                        </div>
                                    )}
                                </Form.Group>
                            </Col>
                            <Col>
                                <Form.Group className="mb-3" controlId="email">
                                    <Form.Label>{t("FORM.email")}</Form.Label>
                                    <Form.Control type="email"
                                                  placeholder={t("FORM.enterEmail")}
                                                  value={email}
                                                  onChange={onChange}/>
                                    {emailErrors.length !== 0 && (
                                        <div className="form-group">
                                            <div className="alert alert-danger" role="alert">
                                                <ul>
                                                    {emailErrors.map((error) => {
                                                        return (
                                                            <li>{error}</li>
                                                        )
                                                    })}
                                                </ul>
                                            </div>
                                        </div>
                                    )}
                                </Form.Group>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <Form.Group className="mb-3" controlId="password">
                                    <Form.Label>{t("FORM.password")}</Form.Label>
                                    <Form.Control type="password"
                                                  placeholder={t("FORM.password")}
                                                  value={password}
                                                  onChange={onChange}/>
                                </Form.Group>
                                {passwordErrors.length !== 0 && (
                                    <div className="form-group">
                                        <div className="alert alert-danger" role="alert">
                                            <ul>
                                                {passwordErrors.map((error) => {
                                                    return (
                                                        <li>{error}</li>
                                                    )
                                                })}
                                            </ul>
                                        </div>
                                    </div>
                                )}
                            </Col>
                            <Col>
                                <Form.Group className="mb-3" controlId="repeatPassword">
                                    <Form.Label>{t("FORM.repeatPassword")}</Form.Label>
                                    <Form.Control type="password"
                                                  placeholder={t("FORM.repeatPassword")}
                                                  value={repeatPassword}
                                                  onChange={onChange}/>
                                    {repeatPasswordErrors.length !== 0 && (
                                        <div className="form-group">
                                            <div className="alert alert-danger" role="alert">
                                                <ul>
                                                    {repeatPasswordErrors.map((error) => {
                                                        return (
                                                            <li>{error}</li>
                                                        )
                                                    })}
                                                </ul>
                                            </div>
                                        </div>
                                    )}
                                </Form.Group>
                            </Col>
                        </Row>
                        {message && (
                            <div className="form-group">
                                <div className="alert alert-danger" role="alert">
                                    {t(message)}
                                </div>
                            </div>
                        )}
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={() => handleShow()}>
                        {t("closeButton")}
                    </Button>
                    <Button disabled={invalidForm || selectedSpecialty === ''}
                            className="doc-button-color"
                            onClick={() => handleAdd({
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
                        {t("DOC.addDoc")}
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    )
}

export default DoctorAddModal