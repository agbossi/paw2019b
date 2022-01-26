import React, {Component} from 'react';
import {Button, Card, Container, Modal} from "react-bootstrap";
import '../CardContainer.css'
import SinglePropertyAddModal from "../Modals/SinglePropertyAddModal";
import ApiCalls from "../../api/apiCalls";

class Specialties extends Component {

    constructor(props) {
        super(props);
        this.state = {
            specialties: [],
            page: 0,
            maxPage: 0
        }
    }

    async componentDidMount() {
        const response = await ApiCalls.getSpecialties(this.state.page)
        if (response && response.ok) {
            this.setState(
                {
                    specialties: response.data,
                    page: this.state.page,
                    maxPage: response.headers.xMaxPage
                })
        }
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

    nextPage = async () => {
        const pag = this.state.page + 1
        const response = await ApiCalls.getSpecialties(pag);
        if (response && response.ok)
            this.setState({
                specialties: response.data,
                page: pag,
                maxPage: response.headers.xMaxPage
            })

    }
    prevPage = async () => {
        const pag = this.state.page - 1
        const response = await ApiCalls.getSpecialties(pag);
        if (response && response.ok)
            this.setState({
                specialties: response.data,
                page: pag,
                maxPage: response.headers.xMaxPage
            })

    }

    renderPrevButton() {
        if (this.state.page !== 0) {
            return <Button className="remove-button doc-button-color shadow-sm"
                           onClick={() => this.prevPage()}>Prev</Button>
        }
    }

    renderNextButton() {
        if (this.state.page < this.state.maxPage) {
            return <Button className="remove-button doc-button-color shadow-sm"
                    onClick={() => this.nextPage()}>Next</Button>
        }
    }

    render() {
        return (
            <div className="background">
                <SinglePropertyAddModal handleAdd={this.handleAdd} property="Specialty"/>
                <Container>
                    <div className="admin-info-container">
                        {this.state.specialties.map(specialty => {
                            return (
                                <Card className="mb-3 shadow" style={{color: "#000", width: '20rem', height: '7rem'}} key={specialty.name}>
                                    <Card.Body>
                                        <Card.Title>{specialty.name}</Card.Title>
                                    </Card.Body>
                                    <Button className="remove-button doc-button-color shadow-sm"
                                            onClick={() => this.deleteSpecialty(specialty.name)}>
                                        Delete
                                    </Button>
                                </Card>
                            )
                        })}
                    </div>
                </Container>
                {this.renderPrevButton()}
                {this.renderNextButton()}
            </div>
        )
    }
}

export default Specialties