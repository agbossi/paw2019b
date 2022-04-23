import {useTranslation} from "react-i18next";
import React, {useState} from "react";
import {Button, Form, Modal} from "react-bootstrap";
import DropDownList from "../DropDownList";
import validation from "../../utils/validationHelper";

function EditDocProfileModal(props) {
    const [firstName, setFirstName] = useState(localStorage.getItem('firstName'))
    const [lastName, setLastName] = useState(localStorage.getItem('lastName'))
    const [newPassword, setNewPassword] = useState('')
    const [repeatPassword, setRepeatPassword] = useState('')
    const [selectedSpecialty, setSelectedSpecialty] = useState(localStorage.getItem('specialty'))
    const [phoneNumber, setPhoneNumber] = useState(localStorage.getItem('phone'))
    const [show, setShow] = useState(false)
    const [message, setMessage] = useState('')
    const {t} = useTranslation()

    const [invalidForm, setInvalidForm] = useState(true)

    const [firstNameErrors, setFirstNameErrors] = useState([])
    const [lastNameErrors, setLastNameErrors] = useState([])
    const [phoneNumberErrors, setPhoneNumberErrors] = useState([])
    const [passwordErrors, setPasswordErrors] = useState([])
    const [repeatPasswordErrors, setRepeatPasswordErrors] = useState([])

    const onChange = (event) => {
        let error = false
        let errors = []
        switch(event.target.id) {
            case "firstName":
                setFirstName(event.target.value);
                error = error || validation.requiredAlpha(event.target.value, errors, "firstName", t)
                setFirstNameErrors(errors)
                break;
            case "lastName":
                setLastName(event.target.value);
                error = error || validation.requiredAlpha(event.target.value, errors, "lastName", t)
                setLastNameErrors(errors)
                break;
            case "newPassword":
                setNewPassword(event.target.value);
                error = error || validation.requiredLength(event.target.value, errors, "password", 8, 20, t)
                    || !validation.passwordMatch(event.target.value, errors, repeatPassword, t)
                setPasswordErrors(errors)
                break;
            case "repeatPassword":
                setRepeatPassword(event.target.value);
                error = error || !validation.passwordMatch(newPassword, errors, event.target.value, t)
                setRepeatPasswordErrors(errors)
                break;
            case "phoneNumber":
                setPhoneNumber(event.target.value);
                error = error || validation.requiredNumeric(event.target.value, errors, "phoneNumber", t) ||
                    !validation.checkLength(event.target.value, errors, "phoneNumber", 8, 11, t)
                setPhoneNumberErrors(errors)
                break;
        }
        setInvalidForm(error)
    }

    const handleSelect = (specialty) => {
        setSelectedSpecialty(specialty)
    }

    const handleShow = () => {
        setShow(!show)

    }

    const handleClick = () => {
        setInvalidForm(true)
        if (selectedSpecialty === '')
            setSelectedSpecialty(localStorage.getItem('specialty'))
        const doctor = {
            firstName: firstName,
            lastName: lastName,
            newPassword: newPassword,
            repeatPassword: repeatPassword,
            specialty: selectedSpecialty,
            phoneNumber: phoneNumber
        }
        if (newPassword !== repeatPassword)
            setMessage("errors.passwordMismatch")
        else {
            setMessage('')
            props.handleEdit(doctor)
            setInvalidForm(false)
            handleShow()
        }
        setInvalidForm(false)
    }

    return (
        <>
            <Button variant="edit-remove-button doc-button-color shadow-sm edit-button"
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

                        <Form.Group className="mb-3" controlId="lastName">
                            <Form.Label>{t("FORM.lastName")}</Form.Label>
                            <Form.Control value={lastName}
                                          placeholder={t("FORM.enterLastName")}
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

                        <Form.Group className="mb-3" controlId="specialty">
                            <Form.Label>{t("FORM.specialty")} {selectedSpecialty}</Form.Label>
                            <DropDownList iterable={props.specialties}
                                          selectedElement={selectedSpecialty}
                                          handleSelect={handleSelect}
                                          elementType={t("FORM.selectSpecialty")}
                                          id='specialty'/>
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="phoneNumber">
                            <Form.Label>{t("FORM.phoneNumber")}</Form.Label>
                            <Form.Control value={phoneNumber}
                                          type="text"
                                          placeholder={t("FORM.enterPhone")}
                                          onChange={onChange}
                            />
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

                        <Form.Group className="mb-3" controlId="newPassword">
                            <Form.Label>{t("FORM.password")}</Form.Label>
                            <Form.Control type="password"
                                          placeholder={t("FORM.password")}
                                          onChange={onChange}
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

                        <Form.Group className="mb-3" controlId="repeatPassword">
                            <Form.Label>{t("FORM.repeatPassword")}</Form.Label>
                            <Form.Control type="password"
                                          placeholder={t("FORM.repeatPassword")}
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
                    </Form>
                    {message && (
                        <div className="form-group">
                            <div className="alert alert-danger" role="alert">
                                {t(message)}
                            </div>
                        </div>
                    )}
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={() => handleShow()}>
                        {t("closeButton")}
                    </Button>
                    <Button className="doc-button-color" disabled={invalidForm} onClick={() => handleClick()} >
                        {t('actions.edit')}
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    )
}

export default EditDocProfileModal