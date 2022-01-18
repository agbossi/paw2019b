import React, {Component} from 'react';
import {Button, Card, Container, Modal} from "react-bootstrap";
import '../CardContainer.css'
import SinglePropertyAddModal from "../Modals/SinglePropertyAddModal";

class Prepaids extends Component {

    constructor(props) {
        super(props);
        this.state = {
            prepaids: []
        }
    }

    componentDidMount() {
        this.setState({
            prepaids: ['OSDE', 'Simeco']
        })
    }

    deletePrepaids = (name) => {
        this.setState({
            prepaids: this.state.prepaids.filter(prepaid => prepaid !== name)
        })
    }

    handleAdd = (newPrepaid) => {
        this.setState({
            prepaids: [...this.state.prepaids, newPrepaid]
        })
    }

    render() {
        return (
            <div className="background">
                <SinglePropertyAddModal handleAdd={this.handleAdd} property={"Prepaid"}/>
                <Container>
                    <div className="admin-info-container">
                        {this.state.prepaids.map((prepaid, index) => {
                            return (
                                <Card className="mb-3 shadow"
                                      style={{color: "#000", width: '20rem', height: '7rem'}}
                                      key={prepaid}>
                                    <Card.Body>
                                        <Card.Title>{prepaid}</Card.Title>
                                    </Card.Body>
                                    <Button className="remove-button doc-button-color shadow-sm"
                                            onClick={() => this.deletePrepaids(prepaid)}>
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

export default Prepaids