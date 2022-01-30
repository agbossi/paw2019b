import React, {Component, useState} from 'react';
import {Button, Modal, Form} from "react-bootstrap";

function SinglePropertyAddModal(props) {
    const [show, setShow] = useState(false);
    const [newProperty, setNewProperty] = useState('');

    const handleShow = () => {
        setShow(!show)
    }

    const handleAdd = (newProp) => {
        props.handleAdd(newProp)
        setNewProperty("")
        handleShow()
    }

    const onChange = (event) => {
        setNewProperty(event.target.value)
    }

    return (
        <>
            <Button variant="outline-secondary" onClick={handleShow} size="lg" className="add-margin">
                {'Add ' +  props.property}
            </Button>
            <Modal show={show} onHide={handleShow}>
                <Modal.Header closeButton>
                    <Modal.Title>{'Add ' + props.property}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form.Group className="mb-3" controlId="name">
                        <Form.Label>{props.property + ' name'}</Form.Label>
                        <Form.Control value={newProperty}
                                  placeholder={'Write a ' + props.property + ' name'}
                                  onChange={onChange}/>
                    </Form.Group>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleShow}>
                        Close
                    </Button>
                    <Button className="doc-button-color" onClick={() => handleAdd({name: newProperty})}>
                        Add
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    )
}

export default SinglePropertyAddModal;