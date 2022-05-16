import React, {useEffect, useState} from 'react';
import {Button, Col, Form, Row} from "react-bootstrap";
import '../CardContainer.css'
import DropDownList from "../DropDownList";
import {useTranslation} from "react-i18next";
import '../../i18n/i18n'
import ApiCalls from "../../api/apiCalls"
import {useNavigate} from "react-router-dom";
import PrepaidCalls from "../../api/PrepaidCalls";
import validation from "../../utils/validationHelper";

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

    const [message, setMessage] = useState("")
    const [firstNameErrors, setFirstNameErrors] = useState([])
    const [lastNameErrors, setLastNameErrors] = useState([])
    const [emailErrors, setEmailErrors] = useState([])
    const [prepaidNumberErrors, setPrepaidNumberErrors] = useState([])
    const [passwordErrors, setPasswordErrors] = useState([])
    const [repeatPasswordErrors, setRepeatPasswordErrors] = useState([])
    const [documentErrors, setDocumentErrors] = useState([])
    const [invalidForm, setInvalidForm] = useState(true)

    const { t } = useTranslation();
    const navigate = useNavigate();

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

    const handleSignUp = async (e) => {

        e.preventDefault();
        setInvalidForm(true)
        if(!isPresent(firstName) || !isPresent(email) || !isPresent(lastName) ||
            !isPresent(document) || !isPresent(password) || (isPresent(selectedPrepaid) && !isPresent(prepaidNumber))) {
            setMessage(t('errors.incompleteForm'))
            return
        }
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

        await ApiCalls.signUp(data)
            .then(
                (resp) => {
                    if (resp.status === 201) {
                        navigate("/paw-2019b-4/login");
                        window.location.reload()
                    }
                    if (resp.status === 409) {
                        if (resp.data === "user-exists") {
                            console.log('409 en signup')
                            setMessage(t("errors.emailInUse"))
                        }
                    }
                },
                error => {
                    setMessage(t('error.emailInUse'))
                }
            );


        setInvalidForm(false)
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
            case "document":
                setDocument(event.target.value)
                error = error || validation.requiredNumeric(event.target.value, errors, "document", t)
                    || !validation.checkLengthExact(event.target.value, errors, "document", 8, t)
                setDocumentErrors(errors)
                break;
            case "email":
                setEmail(event.target.value)
                error = error || validation.requiredEmail(event.target.value, errors, "email", t)
                setEmailErrors(errors)
                break;
            case "password":
                setPassword(event.target.value)
                error = error || validation.requiredLength(event.target.value, errors, "password", 8, 20, t)
                    || !validation.passwordMatch(event.target.value, errors, repeatPassword, t)
                setPasswordErrors(errors)
                break;
            case "prepaidNumber":
                setPrepaidNumber(event.target.value)
                error = error || validation.requiredNumeric(event.target.value, errors, "prepaidNumber", t) ||
                    !validation.checkLength(event.target.value, errors, "prepaidNumber", 8, 20, t)
                setPrepaidNumberErrors(errors)
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
                        {firstNameErrors.length !== 0 && (
                            <div className="form-group">
                                <div className="alert alert-danger" role="alert">
                                    <ul id='firstNameErrors'>
                                        {firstNameErrors.map((error, index) => {
                                            return (
                                                <li key={index}>{error}</li>
                                            )
                                        })}
                                    </ul>
                                </div>
                            </div>
                        )}
                    </Col>
                    <Col className="mx-4">
                        <Form.Group className="mb-3 div-signup" controlId="lastName">
                            <Form.Label className="label-signup m-3">{t("FORM.lastName")}</Form.Label>
                            <Form.Control placeholder={t("FORM.enterLastName")}
                                          value={lastName}
                                          onChange={onChange}
                            />
                        </Form.Group>
                        {lastNameErrors.length !== 0 && (
                            <div className="form-group">
                                <div className="alert alert-danger" role="alert">
                                    <ul id='lastNameErrors'>
                                        {lastNameErrors.map((error, index) => {
                                            return (
                                                <li key={index}>{error}</li>
                                            )
                                        })}
                                    </ul>
                                </div>
                            </div>
                        )}
                    </Col>
                </Row>
                <Row>
                    <Col className="mx-4">
                        <Form.Group className="mb-3 div-signup" controlId="prepaids">
                            <Form.Label className="label-signup m-3">{t("FORM.prepaid")}</Form.Label>
                            <DropDownList iterable={prepaids}
                                          selectedElement=''
                                          handleSelect={handleSelect}
                                          elementType={t("FORM.selectPrepaid")}
                                          id='prepaids'
                            />
                            <Form.Label id='selectedPrepaid' className="label-signup m-3">{selectedPrepaid}</Form.Label>
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
                                        <ul id='prepaidNumberErrors'>
                                            {prepaidNumberErrors.map((error, index) => {
                                                return (
                                                    <li key={index}>{error}</li>
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
                        <Form.Group className="mb-3 div-signup" controlId="document">
                            <Form.Label className="label-signup m-3">{t("FORM.document")}</Form.Label>
                            <Form.Control placeholder={t("FORM.enterDocument")}
                                          value={document}
                                          onChange={onChange}
                            />
                        </Form.Group>
                        {documentErrors.length !== 0 && (
                            <div className="form-group">
                                <div className="alert alert-danger" role="alert">
                                    <ul id='documentErrors'>
                                        {documentErrors.map((error, index) => {
                                            return (
                                                <li key={index}>{error}</li>
                                            )
                                        })}
                                    </ul>
                                </div>
                            </div>
                        )}
                    </Col>
                    <Col className="mx-4">
                        <Form.Group className="mb-3 div-signup" controlId="email">
                            <Form.Label className="label-signup m-3">{t("FORM.email")}</Form.Label>
                            <Form.Control type="email"
                                          placeholder={t("FORM.enterEmail")}
                                          value={email}
                                          onChange={onChange}
                            />
                            {emailErrors.length !== 0 && (
                                <div className="form-group">
                                    <div className="alert alert-danger" role="alert">
                                        <ul id='emailErrors'>
                                            {emailErrors.map((error, index) => {
                                                return (
                                                    <li key={index}>{error}</li>
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
                                          placeholder={t("FORM.enterPassword")}
                                          value={password} onChange={onChange}
                            />
                            {passwordErrors.length !== 0 && (
                                <div className="form-group">
                                    <div className="alert alert-danger" role="alert">
                                        <ul id='passwordErrors'>
                                            {passwordErrors.map((error, index) => {
                                                return (
                                                    <li key={index}>{error}</li>
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
                                          placeholder={t("FORM.enterRepeatPassword")}
                                          value={repeatPassword}
                                          onChange={onChange}
                            />
                            {repeatPasswordErrors.length !== 0 && (
                                <div className="form-group">
                                    <div className="alert alert-danger" role="alert">
                                        <ul id='repeatPasswordErrors'>
                                            {repeatPasswordErrors.map((error, index) => {
                                                return (
                                                    <li key={index}>{error}</li>
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
                <Button type="submit" id='submitButton' disabled={invalidForm || selectedPrepaid === ''} variant="secondary" >
                    {t("FORM.signUp")}
                </Button>
            </Form>
            {message && (
                <div className="form-group">
                    <div className="alert alert-danger m-3" role="alert">
                        {t(message)}
                    </div>
                </div>
            )}
        </div>
    )
}

export default SignUp;