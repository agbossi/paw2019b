import React, {Component, useEffect, useState} from 'react';
import {Button, Modal, Form, Dropdown} from "react-bootstrap";
import DropDownList from "../DropDownList";
import '../CardContainer.css'
import PrepaidCalls from "../../api/PrepaidCalls";
import ClinicCalls from "../../api/ClinicCalls";
import {element} from "prop-types";
import {useNavigate} from "react-router-dom";

 function ClinicPrepaidAddModal(props) {
    const [show, setShow] = useState(false);
    const [newPrepaid, setNewPrepaid] = useState('');

    const handleSelect = (prepaid) => {
        setNewPrepaid(prepaid)
    }

    const handleShow = () => {
        setShow(!show)
    }

    const handleAdd = async () => {
        setNewPrepaid("")
        await props.handleAdd(newPrepaid)
        handleShow()

    }

    const remainingPrepaids = () => {
        return props.allPrepaids.filter(prepaid => !props.prepaids.includes(prepaid));
    }


    return (
        <>
            <Button variant="outline-secondary add-margin" onClick={handleShow} size="lg">
                Add prepaid to clinic
            </Button>
            <Modal show={show} onHide={handleShow}>
                <Modal.Header closeButton>
                    <Modal.Title>Add prepaid to clinic</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form.Group className="mb-3" controlId="name">
                        <Form.Label>Prepaid name: {newPrepaid}</Form.Label>
                        <DropDownList iterable={remainingPrepaids()}
                                      selectedElement=''
                                      handleSelect={handleSelect}
                                      elementType='Prepaid'
                                      id='prepaid'/>
                    </Form.Group>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleShow}>
                        Close
                    </Button>
                    <Button className="doc-button-color" onClick={handleAdd}>
                        Add
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    )

}

export default ClinicPrepaidAddModal;