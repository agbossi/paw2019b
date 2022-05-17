import React, {useState} from "react";
import {useTranslation} from "react-i18next";
import {Button, Form, Modal} from "react-bootstrap";
import DropDownList from "../DropDownList";
import PatientCalls from "../../api/PatientCalls";
import {useNavigate} from "react-router-dom";
import '../Pages/Profile.css'
import validation from "../../utils/validationHelper";

function EditUserProfileModal(props) {
    const [firstName, setFirstName] = useState(localStorage.getItem('firstName'))
    const [lastName, setLastName] = useState(localStorage.getItem('lastName'))
    const [newPassword, setNewPassword] = useState('')
    const [repeatPassword, setRepeatPassword] = useState('')
    const [selectedPrepaid, setSelectedPrepaid] = useState(localStorage.getItem('prepaid'))
    const [prepaidNumber, setPrepaidNumber] = useState(localStorage.getItem('prepaidNumber'))
    const [show, setShow] = useState(false)
    const [invalidForm, setInvalidForm] = useState(true)

    const [firstNameErrors, setFirstNameErrors] = useState([])
    const [lastNameErrors, setLastNameErrors] = useState([])
    const [prepaidNumberErrors, setPrepaidNumberErrors] = useState([])
    const [passwordErrors, setPasswordErrors] = useState([])
    const [repeatPasswordErrors, setRepeatPasswordErrors] = useState([])
    const {t} = useTranslation()
    const navigate = useNavigate()

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
            case "password":
                setNewPassword(event.target.value)
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
                error = error || !validation.passwordMatch(newPassword, errors, event.target.value, t)
                setRepeatPasswordErrors(errors)
                break;
        }
        setInvalidForm(error)
    }

    const handleSelect = (prepaid) => {
        setSelectedPrepaid(prepaid)
        if(prepaidNumber === '') {
            let error = t("FORM.prepaidNumber") + "  " + t("errors.required")
            setPrepaidNumberErrors([error])
        }
    }

    const handleShow = () => {
        setShow(!show)

    }

    const handleProfileUpdate = async () => {
        setInvalidForm(true)
        let data = {
            firstName: firstName,
            lastName: lastName,
            newPassword: newPassword,
            repeatPassword: repeatPassword,
            prepaid: selectedPrepaid,
            prepaidNumber: prepaidNumber
        }
        const response = await PatientCalls.updateProfile(data,
            localStorage.getItem('email'))
        if (response && response.ok) {
            props.handleOk()
        }

        setInvalidForm(false)
        handleShow()
    }

    return (
        <>
            <Button variant="edit-remove-button doc-button-color shadow-sm edit-button app-btn"
                    onClick={() => handleShow()} size="lg"
                    className="add-margin">
                {t("editProfileButton")}
            </Button>
            <Modal show={show} onHide={() => handleShow()}>
                <Modal.Header closeButton>
                    <Modal.Title>{t('editProfileButton')}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group className="mb-3" controlId="firstName">
                            <Form.Label>{t("FORM.firstName")}</Form.Label>
                            <Form.Control value={firstName}
                                          placeholder={t("FORM.enterFirstName")}
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

                        <Form.Group className="mb-3" controlId="lastName">
                            <Form.Label>{t("FORM.lastName")}</Form.Label>
                            <Form.Control value={lastName}
                                          placeholder={t("FORM.enterLastName")}
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

                        <Form.Group className="mb-3" controlId="prepaid">
                            <Form.Label>{t("FORM.prepaid")}: {selectedPrepaid === "undefined"? "":selectedPrepaid}</Form.Label>
                            <DropDownList iterable={props.prepaids}
                                          selectedElement={selectedPrepaid}
                                          handleSelect={handleSelect}
                                          elementType={t("FORM.selectPrepaid")}
                                          id='prepaid'/>
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="prepaidNumber">
                            <Form.Label>{t("FORM.prepaidNumber")}</Form.Label>
                            <Form.Control value={prepaidNumber}
                                          type="text"
                                          placeholder={t("FORM.enterPrepaidNumber")}
                                          onChange={onChange}/>
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

                        <Form.Group className="mb-3" controlId="newPassword">
                            <Form.Label>{t("FORM.password")}</Form.Label>
                            <Form.Control type="password"
                                          placeholder={t("FORM.password")}
                                          onChange={onChange}/>
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

                        <Form.Group className="mb-3" controlId="repeatPassword">
                            <Form.Label>{t("FORM.repeatPassword")}</Form.Label>
                            <Form.Control type="password"
                                          placeholder={t("FORM.repeatPassword")}
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
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={() => handleShow()}>
                        {t("closeButton")}
                    </Button>
                    <Button className="doc-button-color" disabled={invalidForm} onClick={handleProfileUpdate}>
                        {t('actions.edit')}
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    )

}
export default EditUserProfileModal