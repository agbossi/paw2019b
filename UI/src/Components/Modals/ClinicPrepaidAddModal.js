import React, {Component} from 'react';
import {Button, Modal, Form, Dropdown} from "react-bootstrap";
import DropDownList from "../DropDownList";

class ClinicPrepaidAddModal extends Component {

    constructor(props) {
        super(props);
        this.state = {
            show: false,
            newPrepaid: '',
            prepaids: []
        }
    }

    componentDidMount() {
        this.setState({
            prepaids: ['OSDE', 'Simeco', 'Swiss medical', 'wiwiwi']
        })
    }

    handleSelect = (prepaid) => {
        this.setState({
            newPrepaid: prepaid
        })
    }

    handleShow = () => {
        this.setState({
            show: !this.state.show
        })
    }

    handleAdd = () => {
        this.props.handleAdd(this.state.newPrepaid)
        this.handleShow()
    }

    onChange = (event) => {
        this.setState({
            newProperty: event.target.value
        })
    }

    render() {
        return (
            <>
                <Button variant="outline-success" onClick={this.handleShow} size="lg">
                    Add prepaid to clinic
                    <i className="fab fa-typo3"/>
                </Button>
                <Modal show={this.state.show} onHide={this.handleShow}>
                    <Modal.Header closeButton>
                        <Modal.Title>Add prepaid to clinic</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form.Group className="mb-3" controlId="name">
                            <Form.Label>Prepaid name</Form.Label>
                            <DropDownList iterable={this.state.prepaids}
                                          selectedElement=''
                                          handleSelect={this.handleSelect}
                                          elementType='Prepaid'
                                          id='prepaid'/>
                        </Form.Group>
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

export default ClinicPrepaidAddModal;