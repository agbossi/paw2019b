import React, {Component} from 'react';
import {Button, Modal, Form} from "react-bootstrap";

class LocationAddModal extends Component {

    constructor(props) {
        super(props);
        this.state = {
            show: false,
            newLocation: ''
        }
    }


    handleShow = () => {
        this.setState({
            show: !this.state.show
        })
    }

    handleAdd = () => {
        this.props.handleAdd(this.state.newLocation)
        this.handleShow()
    }

    onChange = (event) => {
        this.setState({
            newLocation: event.target.value
        })
    }

    render() {
        return (
            <>
                <Button variant="outline-success" onClick={this.handleShow} size="lg">
                    Add location
                    <i className="fab fa-typo3"/>
                </Button>
                <Modal show={this.state.show} onHide={this.handleShow}>
                    <Modal.Header closeButton>
                        <Modal.Title>Add location</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form.Label>Location name</Form.Label>
                        <input
                            name="location"
                            type="text"
                            placeholder="Write a location name"
                            onChange={this.onChange}
                            value={this.state.newLocation}/>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={this.handleShow}>
                            Close
                        </Button>
                        <Button variant="primary" onClick={this.handleAdd}>
                            Add
                        </Button>
                    </Modal.Footer>
                </Modal>
            </>
        )
    }
}

export default LocationAddModal;