import React, {Component} from 'react';
import {Button, Modal, Form} from "react-bootstrap";

class SinglePropertyAddModal extends Component {

    constructor(props) {
        super(props);
        this.state = {
            show: false,
            newProperty: ''
        }
    }


    handleShow = () => {
        this.setState({
            show: !this.state.show
        })
    }

    handleAdd = () => {
        this.props.handleAdd(this.state.newProperty)
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
                    {'Add ' +  this.props.property}
                    <i className="fab fa-typo3"/>
                </Button>
                <Modal show={this.state.show} onHide={this.handleShow}>
                    <Modal.Header closeButton>
                        <Modal.Title>{'Add ' +  this.props.property}</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form.Group className="mb-3" controlId="name">
                            <Form.Label>{this.props.property + ' name'}</Form.Label>
                            <Form.Control value={this.state.newProperty}
                                      placeholder={'Write a ' + this.props.property + ' name'}
                                      onChange={this.onChange}/>
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

export default SinglePropertyAddModal;