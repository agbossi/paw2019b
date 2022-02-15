import React, {useEffect, useState} from 'react';
import {Button, Col, Form, Modal, Row} from "react-bootstrap";
import DropDownList from "../DropDownList";
import {useTranslation} from "react-i18next";
import '../../i18n/i18n'
import {useNavigate} from "react-router-dom";
import PatientCalls from "../../api/PatientCalls";
import PrepaidCalls from "../../api/PrepaidCalls";

function Profile() {
    const [selectedPrepaid, setSelectedPrepaid] = useState('')
    const [firstName, setFirstName] = useState('')
    const [lastName, setLastName] = useState('')
    const [password, setPassword] = useState('')
    const [repeatPassword, setRepeatPassword] = useState('')
    const [prepaidNumber, setPrepaidNumber] = useState('')
    const [prepaids, setPrepaids] = useState([])

    const [firstNameErrors, setFirstNameErrors] = useState([])
    const [lastNameErrors, setLastNameErrors] = useState([])
    const [prepaidNumberErrors, setPrepaidNumberErrors] = useState([])
    const [passwordErrors, setPasswordErrors] = useState([])
    const [repeatPasswordErrors, setRepeatPasswordErrors] = useState([])
    const [invalidForm, setInvalidForm] = useState(true)
    const { t } = useTranslation();
    const navigate = useNavigate();

    const handleSelect = (prepaid) => {
        setSelectedPrepaid(prepaid)
    }

    useEffect(async () => {
        await fetchProfile()
        await fetchPrepaids()
    }, [])

    const fetchPrepaids = async () => {
        const response = await PrepaidCalls.getAllPrepaids();
        if (response && response.ok) {
            setPrepaids(response.data.map(prepaid => prepaid.name))
        }
    }

    const fetchProfile = async () => {
        const response = await PatientCalls.getProfile(localStorage.getItem('email'));
        if (response && response.ok) {
            setFirstName(response.data.firstName)
            setLastName(response.data.lastName)
            setSelectedPrepaid(response.data.prepaid)
            setPrepaidNumber(response.data.prepaidNumber)
        } else if (response.status === 401) {
            localStorage.removeItem('token')
            localStorage.removeItem('role')
            navigate('/login')
        }
    }

    const handleProfileUpdate = async (e) => {
        setInvalidForm(true)
        e.preventDefault();
        const response = await PatientCalls.updateProfile({
            firstName: firstName,
            lastName: lastName,
            newPassword: password,
            repeatPassword: repeatPassword,
            prepaid: selectedPrepaid,
            prepaidNumber: prepaidNumber
        }, localStorage.getItem('email'))
        if (response && response.ok) {
            await fetchProfile()
            await fetchPrepaids()
        }
        if (response.status === 401) {
            localStorage.removeItem('token')
            localStorage.removeItem('role')
            navigate('/login')
        }
        setInvalidForm(false)
    }

    const isPresent = (value) => {
        let is = true
        if(!value) {
            is = false
        }
        return is
    }

    const onChange = (event) => {
        // eslint-disable-next-line default-case
        let error = false
        let errors = []
        switch (event.target.id) {
            case "firstName":
                setFirstName(event.target.value)
                if(!isPresent(event.target.value)) {
                    errors.push(t("FORM.firstName") + "  " + t("errors.required"))
                    error = true
                }
                if(!/^[a-zA-ZÀ-ÿ\s]{1,40}$/.test(event.target.value)) {
                    errors.push(t("FORM.firstName") + "  " + t("errors.alphabetic"))
                    error = true
                }
                setFirstNameErrors(errors)
                break;
            case "lastName":
                setLastName(event.target.value)
                if(!isPresent(event.target.value)) {
                    errors.push(t("FORM.lastName") + "  " + t("errors.required"))
                    error = true
                }
                if(!/^[a-zA-ZÀ-ÿ\s]{1,40}$/.test(event.target.value)) {
                    errors.push(t("FORM.lastName") + "  " + t("errors.alphabetic"))
                    error = true
                }
                setLastNameErrors(errors)
                break;
            case "password":
                setPassword(event.target.value)
                if(event.target.value !== '' && event.target.value.length < 8) {
                    errors.push(t("errors.passwordTooShort"))
                    error = true
                }
                setPasswordErrors(errors)
                break;
            case "prepaidNumber":
                setPrepaidNumber(event.target.value)
                if(!isPresent(event.target.value)) {
                    errors.push(t("FORM.prepaidNumber") + "  " + t("errors.required"))
                    error = true
                }
                if(!/^\d+$/.test(event.target.value)) {
                    errors.push(t("FORM.prepaidNumber") + "  " + t("errors.numeric"))
                    error = true
                }
                if(event.target.value.length > 20) {
                    errors.push(t("errors.prepaidNumberTooLong"))
                    error = true
                }
                setPrepaidNumberErrors(errors)
                break;
            case "repeatPassword":
                setRepeatPassword(event.target.value)
                if(event.target.value !== '' && password !== '' && password !== event.target.value) {
                    errors.push(t("errors.passwordMismatch"))
                    error = true
                }
                setRepeatPasswordErrors(errors)
                break;
        }
        setInvalidForm(error)
    }

    return (
        <div className="m-3">
            <h3>{t("FORM.profile")}</h3>
            <Form onSubmit={handleProfileUpdate}>
                <Row>
                    <Col className="mx-4">
                        <Form.Group className="mb-3 div-signup" controlId="firstName">
                            <Form.Label className="label-signup m-3">{t("FORM.firstName")}</Form.Label>
                            <Form.Control placeholder={t("FORM.enterFirstName")}
                                          value={firstName}
                                          onChange={onChange}
                            />
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
                    <Col className="mx-4">
                        <Form.Group className="mb-3 div-signup" controlId="lastName">
                            <Form.Label className="label-signup m-3">{t("FORM.lastName")}</Form.Label>
                            <Form.Control placeholder={t("FORM.enterLastName")}
                                          value={lastName}
                                          onChange={onChange}
                            />
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
                    <Col className="mx-4">
                        <Form.Group className="mb-3 div-signup" controlId="prepaids">
                            <Form.Label className="label-signup m-3">{t("FORM.prepaid")} {selectedPrepaid}</Form.Label>
                            <DropDownList iterable={prepaids}
                                          selectedElement={selectedPrepaid}
                                          handleSelect={handleSelect}
                                          elementType={t("FORM.selectPrepaid")}
                                          id='prepaids'
                            />
                        </Form.Group>
                    </Col>
                    <Col className="mx-4">
                        <Form.Group className="mb-3 div-signup" controlId="prepaidNumber">
                            <Form.Label className="label-signup m-3">{t("FORM.prepaidNumber")}</Form.Label>
                            <Form.Control placeholder={t("FORM.enterPrepaidNumber")}
                                          value={prepaidNumber}
                                          onChange={onChange}
                            />
                            {prepaidNumberErrors.length !== 0 && (
                                <div className="form-group">
                                    <div className="alert alert-danger" role="alert">
                                        <ul>
                                            {prepaidNumberErrors.map((error) => {
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
                    <Col className="mx-4">
                        <Form.Group className="mb-3 div-signup" controlId="password">
                            <Form.Label className="label-signup m-3">{t("FORM.password")}</Form.Label>
                            <Form.Control type="password"
                                          placeholder={t("FORM.password")}
                                          value={password} onChange={onChange}
                            />
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
                        </Form.Group>
                    </Col>
                    <Col className="mx-4">
                        <Form.Group className="mb-3 div-signup" controlId="repeatPassword">
                            <Form.Label className="label-signup m-3">{t("FORM.repeatPassword")}</Form.Label>
                            <Form.Control type="password"
                                          placeholder={t("FORM.repeatPassword")}
                                          value={repeatPassword}
                                          onChange={onChange}
                            />
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
                <br/>
                <Button type="submit" disabled={invalidForm} variant="secondary" >
                    {t("FORM.update")}
                </Button>
            </Form>
        </div>
    )
}

export default Profile;