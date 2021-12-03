import React, {Component} from 'react';
import {Button, Card, Container, Modal} from "react-bootstrap";
import '../CardContainer.css'
import SpecialtyAddModal from "../Modals/SpecialtyAddModal";

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
            <>
                <SpecialtyAddModal handleAdd={this.handleAdd}/>
                <Container>
                    <div className="admin-info-container">
                        {this.state.specialties.map(specialty => {
                            return (
                                <Card className="mb-3" style={{color: "#000", width: '20rem', height: '7rem'}} key={specialty}>
                                    <Card.Body>
                                        <Card.Title>{specialty}</Card.Title>
                                    </Card.Body>
                                    <Button variant="danger" onClick={() => this.deleteSpecialty(specialty)}>
                                        X
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

export default Specialties