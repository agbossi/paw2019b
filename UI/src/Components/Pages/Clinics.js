import React, {Component} from 'react';
import {Button, Card, Container} from "react-bootstrap";
import '../CardContainer.css'
import ClinicEditModal from "../Modals/ClinicEditModal";
import {Link} from "react-router-dom";

class Clinics extends Component {

    constructor(props) {
        super(props);
        this.state = {
            clinics: [],
            show: false,
            editableClinic: {id: '', name: '', address: '', location: ''},
            editIndex: -1,
            handleFunction: this.handleAdd,
            action: "Add",
            locations: []
        }
    }

    componentDidMount() {
        this.setState({
            clinics: [
                {
                    "id": 1,
                    "address": "calle falsa 853",
                    "name": "asdf",
                    "location": "moron",
                    "prepaids": "URL"
                },
                {
                    "id": 2,
                    "address": "calle falsa 123",
                    "name": "clinica falsa",
                    "location": "mordor",
                    "prepaids": ""
                },
                {
                    "id": 3,
                    "address": "Panamericana 10000",
                    "name": "clinica abandonada",
                    "location": "Martinez",
                    "prepaids": ""
                },
                {
                    "id": 4,
                    "address": "ningun lugar 0",
                    "name": "qwert",
                    "location": "Belgrano",
                    "prepaids": ""
                }
            ],
            locations: ['Moron', 'Centro', 'Quilmes', 'Belgrano']
        })
    }


    deleteClinic = (id) => {
        this.setState({
            clinics: this.state.clinics.filter(clinic => clinic.id !== id)
        })
    }

    handleAdd = (newClinic) => {
        this.setState({
            clinics: [...this.state.clinics, newClinic],
            show: false
        })
    }

    handleEdit = (editedClinic) => {
        let index = this.state.editIndex
        let clinics = this.state.clinics
        clinics[index] = editedClinic
        this.setState({
            clinics: clinics,
            show: false
        })
    }

    handleShow = (index, func) => {
        let action
        let clinic
        if (index === -1) {
            action = "Add"
            clinic = {id: '', name: '', address: '', location: ''}
        } else {
            action = "Edit"
            clinic = this.state.clinics[index]
        }
        this.setState({
            editableClinic: clinic,
            editIndex: index,
            handleFunction: func,
            show: true,
            action: action
        })
    }

    hideModal = () => {
        this.setState({
            show: false
        })
    }

    render() {
        return (
            <>
                <Button variant="outline-success"
                        onClick={() => this.handleShow(-1, this.handleAdd)}
                        size="lg">
                    Add Clinic
                    <i className="fab fa-typo3"/>
                </Button>
                {this.state.show && <ClinicEditModal show={this.state.show}
                                 clinic={this.state.editableClinic}
                                 handleFunction={this.state.handleFunction}
                                 action={this.state.action}
                                 hideModal={this.hideModal}
                                 locations={this.state.locations}
                /> }
                <Container>
                    <div className="admin-info-container">
                        {this.state.clinics.map(( clinic, index) => {
                            return (
                                <Card className="mb-3" style={{color: "#000", width: '20rem', height: '15rem'}} key={clinic.id}>
                                    <Card.Body>
                                        <Card.Title>{clinic.name}</Card.Title>
                                        <Card.Text>
                                            {clinic.address + ' (' + clinic.location + ')'}
                                        </Card.Text>
                                    </Card.Body>
                                    <Link className="btn btn-outline-dark btn-lg"
                                          role="button"
                                          to={'admin/clinics/' + clinic.id + '/prepaids'}>See Prepaids
                                    </Link>
                                    <Button variant="danger" onClick={() => this.deleteClinic(clinic.id)}>
                                        X
                                    </Button>
                                    <Button className="btn btn-outline-dark btn-lg"
                                    onClick={() => this.handleShow(index, this.handleEdit)}>
                                        <i className="far fa-edit"/>
                                    </Button>
                                </Card>
                            )
                        })}
                    </div>
                </Container>
            </>
        )
    }
}

export default Clinics