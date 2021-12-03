import React, {Component} from 'react';
import {Button, Modal, Form} from "react-bootstrap";

class SpecialtyAddModal extends Component {

    constructor(props) {
        super(props);
        this.state = {
            show: false,
            newSpecialty: ''
        }
    }


    handleShow = () => {
        this.setState({
            show: !this.state.show
        })
    }

    handleAdd = () => {
        this.props.handleAdd(this.state.newSpecialty)
        this.handleShow()
    }

    onChange = (event) => {
        this.setState({
            newSpecialty: event.target.value
        })
    }

    render() {
        return (
            <>
                <Button variant="outline-success"
                        onClick={this.handleShow}
                        size="lg">
                    Add specialty
                    <i className="fab fa-typo3"/>
                </Button>
                <Modal show={this.state.show} onHide={this.handleShow}>
                    <Modal.Header closeButton>
                        <Modal.Title>Add specialty</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form.Label>Specialty name</Form.Label>
                        <input
                            name="specialty"
                            type="text"
                            placeholder="Write a specialty name"
                            onChange={this.onChange}
                            value={this.state.newSpecialty}/>
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

export default SpecialtyAddModal;