import React, {Component, useState} from 'react';
import {Button, Modal, Form, Dropdown} from "react-bootstrap";
import DropDownList from "../DropDownList";

function ClinicEditModal(props) {
    const [id, setId] = useState(props.clinic.id);
    const [address, setAddress] = useState(props.clinic.address);
    const [name, setName] = useState(props.clinic.name);
    const [location, setLocation] = useState(props.clinic.location);


    const onChange = (event) => {
        switch(event.target.id) {
            case "name":
                setName(event.target.value);
                break;
            case "address":
                setAddress(event.target.value);
                break;
            case "location":
                setLocation(event.target.value);
                break;
        }
    }

    const handleSelect = (location) => {
        setLocation(location)
    }

    const handleClick = () => {
        if (props.action === "Add") {
            props.handleAdd(
                {
                    id: id,
                    name: name,
                    address: address,
                    location: location
                })
        } else {
            props.handleEdit(
                {
                    id: id,
                    name: name,
                    address: address,
                    location: location
                })
        }
    }

    return (
        <>
            <Modal show={props.show} onHide={props.hideModal}>
                <Modal.Header closeButton>
                    <Modal.Title>{props.action + " clinic"}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group className="mb-3" controlId="name">
                            <Form.Label>Clinic name</Form.Label>
                            <Form.Control value={name}
                                          placeholder="Enter clinic name"
                                          onChange={onChange}/>
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="address">
                            <Form.Label>Clinic address</Form.Label>
                            <Form.Control value={address}
                                          placeholder="Enter clinic address"
                                          onChange={onChange}/>
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="location">
                            <Form.Label>Clinic location</Form.Label>
                            <DropDownList iterable={props.locations}
                                          selectedElement={location}
                                          handleSelect={handleSelect}
                                          elementType='Location'
                                          id='location'/>
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={props.hideModal}>
                        Close
                    </Button>
                    <Button className="doc-button-color" onClick={handleClick} >
                        {props.action}
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    )

}

export default ClinicEditModal;