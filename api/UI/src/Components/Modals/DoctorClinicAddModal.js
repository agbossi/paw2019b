import {Button, Col, Form, Modal} from "react-bootstrap";
import DropDownList from "../DropDownList";
import React, {useState} from "react";
import {useTranslation} from "react-i18next";
import validation from "../../utils/validationHelper";


function DoctorClinicAddModal(props) {
    const [show, setShow] = useState(false)
    const [selectedClinic, setSelectedClinic] = useState({})
    const [price, setPrice] = useState(0)

    const [priceErrors, setPriceErrors] = useState([])
    const [invalidForm, setInvalidForm] = useState(false)

    const {t} = useTranslation()

    const handleSelect = (clinicName) => {
        const selected = props.allClinics.filter(clinic => clinic.name + " (" + clinic.location + ")" === clinicName)
        setSelectedClinic(selected[0])
    }

    const handleShow = () => {
        setShow(!show)
    }

    const onChange = (event) => {
        let error = false
        let errors = []
        setPrice(event.target.value)
        error = validation.requiredNumeric(event.target.value, errors, "price", t)
        setPriceErrors(errors)
        setInvalidForm(error)
    }

    const handleAdd = async () => {
        const data = {
            clinic: selectedClinic.id,
            consultPrice: Number(price)
        }
        setSelectedClinic({})
        props.handleAdd(data)
        handleShow()
    }

    const remainingClinics = () => {
        return props.allClinics.filter(clinic => !props.clinics.includes(clinic.id))
            .map(clinic => clinic.name + " (" + clinic.location + ")");

    }

    return(
        <>
            <Button variant="outline-secondary add-margin" onClick={handleShow} size="lg">
                {t('DOC.subscribeClinic')}
            </Button>
            <Modal show={show} onHide={handleShow}>
                <Modal.Header closeButton>
                    <Modal.Title>{t('DOC.subscribeClinic')}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form.Group className="mb-3" controlId="name">
                        <Form.Label>{t('CLINIC.clinic')}: {selectedClinic.name} - {selectedClinic.location}</Form.Label>
                        <DropDownList iterable={remainingClinics()}
                                      selectedElement=''
                                      handleSelect={handleSelect}
                                      elementType={t('FORM.selectClinic')}
                                      id='clinic'/>
                        <Form.Group className="mb-3 mt-3" controlId="price">
                            <Form.Label>{t('DOC.price')}</Form.Label>
                            <Form.Control type="number"
                                          placeholder={t("FORM.enterPrice")}
                                          value={price}
                                          onChange={onChange}/>
                            {priceErrors.length !== 0 && (
                                <div className="form-group">
                                    <div className="alert alert-danger" role="alert">
                                        <ul>
                                            {priceErrors.map((error) => {
                                                return (
                                                    <li>{error}</li>
                                                )
                                            })}
                                        </ul>
                                    </div>
                                </div>
                            )}
                        </Form.Group>
                    </Form.Group>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleShow}>
                        {t("closeButton")}
                    </Button>
                    <Button disabled={invalidForm && selectedClinic !== ''} className="doc-button-color" onClick={handleAdd}>
                        {t("actions.add")}
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    )
}

export default DoctorClinicAddModal