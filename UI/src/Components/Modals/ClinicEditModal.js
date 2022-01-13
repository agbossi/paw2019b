import React, {Component} from 'react';
import {Button, Modal, Form, Dropdown} from "react-bootstrap";
import DropDownList from "../DropDownList";

class ClinicEditModal extends Component {

    constructor(props) {
        super(props);
        this.state = {
            id: this.props.clinic.id,
            address: this.props.clinic.address,
            name: this.props.clinic.name,
            location: this.props.clinic.location,
            locations: this.props.locations
        }
    }

    onChange = (event) => {
        this.setState({
            [event.target.id]: event.target.value
        })
    }

    handleSelect = (location) => {
        this.setState({
            location: location
        })
    }

    render() {
        return (
            <>
                <Modal show={this.props.show} onHide={this.props.hideModal}>
                    <Modal.Header closeButton>
                        <Modal.Title>{this.props.action + " clinic"}</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form>
                            <Form.Group className="mb-3" controlId="name">
                                <Form.Label>Clinic name</Form.Label>
                                <Form.Control value={this.state.name}
                                              placeholder="Enter clinic name"
                                              onChange={this.onChange}/>
                            </Form.Group>

                            <Form.Group className="mb-3" controlId="address">
                                <Form.Label>Clinic address</Form.Label>
                                <Form.Control value={this.state.address}
                                              placeholder="Enter clinic address"
                                              onChange={this.onChange}/>
                            </Form.Group>

                            <Form.Group className="mb-3" controlId="location">
                                <Form.Label>Clinic location</Form.Label>
                                <DropDownList iterable={this.state.locations}
                                              selectedElement={this.state.location}
                                              handleSelect={this.handleSelect}
                                              elementType='Location'
                                              id='location'/>
                            </Form.Group>
                        </Form>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={this.props.hideModal}>
                            Close
                        </Button>
                        <Button variant="primary" onClick={() => this.props.handleFunction({id: this.state.id,
                            name: this.state.name,
                            address: this.state.address,
                            location: this.state.location})
                        }>
                            {this.props.action}
                        </Button>
                    </Modal.Footer>
                </Modal>
            </>
        )
    }
}

export default ClinicEditModal;