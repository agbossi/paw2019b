import React, {Component} from 'react';
import {Button, Card, Container} from "react-bootstrap";
import '../CardContainer.css'
import SinglePropertyAddModal from "../Modals/SinglePropertyAddModal";
import ApiCalls from "../../api/apiCalls"

class Locations extends Component {

    constructor(props) {
        super(props);
        this.state = {
            locations: [],
            page: 0,
            maxPage: 0
        }
    }

    async componentDidMount() {
        const response = await ApiCalls.getLocations(this.state.page)
        if (response && response.ok)
            this.setState(
                {
                    locations: response.data,
                    page: this.state.page,
                    maxPage: response.headers.xMaxPage
                })
    }

    deleteLocation = (name) => {
        this.setState({
            locations: this.state.locations.filter(location => location.name !== name)
        })
    }

    handleAdd = async (newLocation) => {
        const response = await ApiCalls.addLocation(newLocation);
        if (response && response.ok)
            this.setState({
                locations: [...this.state.locations, newLocation],
                page: this.state.page,
                maxPage: this.state.maxPage
            })
        else
            console.error("could not add: ", response.status)
    }

    nextPage = async () => {
        const pag = this.state.page + 1
        const response = await ApiCalls.getLocations(pag);
        if (response && response.ok)
            this.setState({
                locations: response.data,
                page: pag,
                maxPage: response.headers.xMaxPage
            })

    }
    prevPage = async () => {
        const pag = this.state.page - 1
        const response = await ApiCalls.getLocations(pag);
        if (response && response.ok)
            this.setState({
                locations: response.data,
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
                <SinglePropertyAddModal handleAdd={this.handleAdd} property="Location"/>
                <Container>
                    <div className="admin-info-container">
                        {this.state.locations.map(location => {
                            return (
                                <Card className="mb-3 shadow"
                                      style={{color: "#000", width: '20rem', height: '7rem'}} key={location.name}>
                                    <Card.Body>
                                        <Card.Title>{location.name}</Card.Title>
                                    </Card.Body>
                                    <Button className="remove-button doc-button-color shadow-sm"
                                            onClick={() => this.deleteLocation(location.name)}>
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

export default Locations