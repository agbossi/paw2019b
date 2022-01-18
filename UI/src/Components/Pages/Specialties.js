import React, {Component} from 'react';
import {Button, Card, Container, Modal} from "react-bootstrap";
import '../CardContainer.css'
import SinglePropertyAddModal from "../Modals/SinglePropertyAddModal";

class Specialties extends Component {

    constructor(props) {
        super(props);
        this.state = {
            specialties: [],
        }
    }

    componentDidMount() {
        this.setState({
            specialties: ['Clinica', 'Cardiologia', 'Neurologia', 'miau']
        })
    }

    deleteSpecialty = (name) => {
        this.setState({
            specialties: this.state.specialties.filter(specialty => specialty !== name)
        })
    }

    handleAdd = (newSpecialty) => {
        this.setState({
            specialties: [...this.state.specialties, newSpecialty]
        })
    }

    render() {
        return (
            <div className="background">
                <SinglePropertyAddModal handleAdd={this.handleAdd} property="Specialty"/>
                <Container>
                    <div className="admin-info-container">
                        {this.state.specialties.map(specialty => {
                            return (
                                <Card className="mb-3 shadow" style={{color: "#000", width: '20rem', height: '7rem'}} key={specialty}>
                                    <Card.Body>
                                        <Card.Title>{specialty}</Card.Title>
                                    </Card.Body>
                                    <Button className="remove-button doc-button-color shadow-sm"
                                            onClick={() => this.deleteSpecialty(specialty)}>
                                        Delete
                                    </Button>
                                </Card>
                            )
                        })}
                    </div>
                </Container>
            </div>
        )
    }
}

export default Specialties