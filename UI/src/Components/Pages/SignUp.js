import React, {useEffect, useState} from 'react';
import {Button, Col, Form, Modal, Row} from "react-bootstrap";
import '../CardContainer.css'
import DropDownList from "../DropDownList";
import {useTranslation} from "react-i18next";
import '../../i18n/i18n'
import ApiCalls from "../../api/apiCalls"
import {useNavigate} from "react-router-dom";
import PrepaidCalls from "../../api/PrepaidCalls";

function SignUp() {
    const [selectedPrepaid, setSelectedPrepaid] = useState('')
    const [firstName, setFirstName] = useState('')
    const [lastName, setLastName] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [repeatPassword, setRepeatPassword] = useState('')
    const [document, setDocument] = useState('')
    const [prepaidNumber, setPrepaidNumber] = useState('')
    const [prepaids, setPrepaids] = useState([])
    const { t } = useTranslation();
    const navigate = useNavigate();
    const [errors, setErrors] = useState([])

    const handleSelect = (prepaid) => {
        setSelectedPrepaid(prepaid)
    }

    useEffect(async () => {
        await fetchPrepaids()
    }, [])

    const fetchPrepaids = async () => {
        const response = await PrepaidCalls.getAllPrepaids();
        if (response && response.ok) {
            setPrepaids(response.data.map(prepaid => prepaid.name))
        }
    }

    const isPresent = (value) => {
        let is = true
        if(!value) {
            is = false
        }
        return is
    }

    const checkRequired = (values, errors) => {
        console.log('first name antes de required: ' + values.firstName)
        if(!isPresent(values.firstName)) {
            errors.push(t("FORM.firstName") + "  " + t("errors.required"))
        }
        if(!isPresent(values.lastName)) {
            errors.push(t("FORM.lastName") + "  " + t("errors.required"))
        }
        console.log('email antes de required: ' + values.email)
        if(!isPresent(values.email)) {
            console.log('entro igual')
            errors.push(t("FORM.email") + "  " + t("errors.required"))
        }
        if(!isPresent(values.id)) {
            errors.push(t("FORM.document") + "  " + t("errors.required"))
        }
        console.log('pass antes de required: ' + values.password)
        if(!isPresent(values.password)) {
            console.log('fallo pass')
            errors.push(t("FORM.password") + "  " + t("errors.required"))
        }
        if(!isPresent(values.repeatPassword)) {
            errors.push(t("FORM.repeatPassword") + "  " + t("errors.required"))
        }
        if(!isPresent(values.prepaidNumber)) {
            errors.push(t("FORM.prepaidNumber") + "  " + t("errors.required"))
        }
    }

    const checkDigits = (values, errors) => {
        if(!/^\d+$/.test(values.id)) {
            errors.push(t("FORM.document") + "  " + t("errors.numeric"))
        }
        if(!/^\d+$/.test(values.prepaidNumber)) {
            errors.push(t("FORM.prepaidNumber") + "  " + t("errors.numeric"))
        }
    }

    const checkPassword = (values, errors) => {
        if(values.password !== values.repeatPassword) {
            errors.push(t("errors.passwordMismatch"))
        }
        if(values.password.length < 8) {
            errors.push(t("errors.passwordTooShort"))
        }
    }

    const checkAlpha = (values, errors) => {
        if(!/^[a-zA-ZÀ-ÿ\s]{1,40}$/.test(values.firstName)) {
            errors.push(t("FORM.firstName") + "  " + t("errors.alphabetic"))
        }
        if(!/^[a-zA-ZÀ-ÿ\s]{1,40}$/.test(values.lastName)) {
            errors.push(t("FORM.lastName") + "  " + t("errors.alphabetic"))        }
    }

    const checkEmail = (values, errors) => {
        if(!/^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/.test(values.email)) {
            errors.push(t("errors.invalidEmail"))
        }
    }

    const handleValidation = (values) => {
        console.log('en validacion')
        console.log('errors antes de vaciar')
        console.log(errors)
        setErrors([])
        console.log('errors desps de vaciar')
        console.log(errors)
        checkRequired(values, errors)
        checkDigits(values, errors)
        checkPassword(values, errors)
        checkEmail(values, errors)
        checkAlpha(values, errors)
        if(values.id.length !== 8) {
            errors.push(t("errors.invalidDocumentLength"))
        }
        if(values.prepaidNumber.length > 20) {
            errors.push(t("errors.prepaidNumberTooLong"))
        }
        setErrors(errors)
        return errors
    }

    const handleSignUp = (e) => {
        e.preventDefault();
        let data = {
            firstName: firstName,
            lastName: lastName,
            email: email,
            password: password,
            repeatPassword: repeatPassword,
            id: document,
            prepaid: selectedPrepaid,
            prepaidNumber: prepaidNumber
        }
        console.log('me toma la funcion?')
        let errors = []//handleValidation(data)
        console.log('hay ' + errors.length + ' errores')
        if(errors.length === 0) {
            console.log('entonces entra aca?')
            ApiCalls.signUp(data)
                .then(resp => {
                    console.log('se supone que se llamo al api signup')
                    if (resp.status === 201) {
                        ApiCalls.login(data.email, data.password).then(
                            (resp) => {
                                console.log('post login')
                                if (resp.status === 200) {
                                    switch (localStorage.getItem('role')) {
                                        case "ROLE_ADMIN":
                                            navigate("/admin");
                                            window.location.reload()
                                            break;
                                        case "ROLE_DOCTOR":
                                            navigate("/doctor");
                                            window.location.reload()
                                            break;
                                        case "ROLE_USER":
                                            navigate("/");
                                            window.location.reload()
                                            break;
                                    }
                                }
                            },
                            error => {
                                const resMessage =
                                    (error.response &&
                                        error.response.data &&
                                        error.response.data.message) ||
                                    error.message ||
                                    error.toString();
                                console.log('caso error')
                                console.log(resMessage)
                            }
                        );
                    } else {
                        console.log('no fue 201')
                        console.log(resp)
                        console.log(resp.status)
                    }
                })
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
            case "document":
                setDocument(event.target.value)
                break;
            case "email":
                setEmail(event.target.value)
                break;
            case "password":
                setPassword(event.target.value)
                break;
            case "prepaidNumber":
                setPrepaidNumber(event.target.value)
                break;
            case "repeatPassword":
                setRepeatPassword(event.target.value)
                break;
        }
    }

    return (
        <div className="m-3">
            <h3>{t("FORM.signUp")}</h3>
            <Form onSubmit={handleSignUp}>
                <Row>
                    <Col className="mx-4">
                        <Form.Group className="mb-3 div-signup" controlId="firstName">
                            <Form.Label className="label-signup m-3">{t("FORM.firstName")}</Form.Label>
                            <Form.Control placeholder={t("FORM.enterFirstName")}
                                          value={firstName}
                                          onChange={onChange}
                            />
                        </Form.Group>
                    </Col>
                    <Col className="mx-4">
                        <Form.Group className="mb-3 div-signup" controlId="lastName">
                            <Form.Label className="label-signup m-3">{t("FORM.lastName")}</Form.Label>
                            <Form.Control placeholder={t("FORM.enterLastName")}
                                          value={lastName}
                                          onChange={onChange}
                            />
                        </Form.Group>
                    </Col>
                </Row>
                <Row>
                    <Col className="mx-4">
                        <Form.Group className="mb-3 div-signup" controlId="prepaids">
                            <Form.Label className="label-signup m-3">{t("FORM.prepaid")} {selectedPrepaid}</Form.Label>
                            <DropDownList iterable={prepaids}
                                          selectedElement=''
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
                        </Form.Group>
                    </Col>
                </Row>
                <Row>
                    <Col className="mx-4">
                        <Form.Group className="mb-3 div-signup" controlId="document">
                            <Form.Label className="label-signup m-3">{t("FORM.document")}</Form.Label>
                            <Form.Control placeholder={t("FORM.enterDocument")}
                                          value={document}
                                          onChange={onChange}
                            />
                        </Form.Group>
                    </Col>
                    <Col className="mx-4">
                        <Form.Group className="mb-3 div-signup" controlId="email">
                            <Form.Label className="label-signup m-3">{t("FORM.email")}</Form.Label>
                            <Form.Control type="email"
                                          placeholder={t("FORM.enterEmail")}
                                          value={email}
                                          onChange={onChange}
                            />
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
                        </Form.Group>
                    </Col>
                </Row>
                {console.log('errores antes del cartel: ' + errors.length)}
                {errors.length !== 0 && (
                    <div className="form-group">
                        <div className="alert alert-danger" role="alert">
                            <ul>
                                {errors.map((error) => {
                                    {console.log(error)}
                                    return (
                                        <li>{error}</li>
                                    )
                                })}
                            </ul>
                        </div>
                    </div>
                )}
                <br/>
                <Button type="submit" variant="secondary" >
                    {t("FORM.signUp")}
                </Button>
            </Form>
        </div>
    )
}

export default SignUp;