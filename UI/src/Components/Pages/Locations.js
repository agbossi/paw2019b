import React, {Component} from 'react';
import {Button, Card, Container, Modal} from "react-bootstrap";
import '../CardContainer.css'
import LocationAddModal from "../Modals/LocationAddModal";

class Locations extends Component {

    constructor(props) {
        super(props);
        this.state = {
            locations: [],
        }
    }

    componentDidMount() {
        this.setState({
            locations: ['moron', 'moreno', 'belgrano', 'centro', 'liniers', 'san telmo', 'quilmes', 'almagro', 'batman']
        })
    }

    deleteLocation = (name) => {
        this.setState({
            locations: this.state.locations.filter(location => location !== name)
        })
    }

    handleAdd = (newLocation) => {
        this.setState({
            locations: [...this.state.locations, newLocation]
        })
    }

    render() {
        return (
            <>
                <LocationAddModal handleAdd={this.handleAdd}/>
                <Container>
                    <div className="admin-info-container">
                        {this.state.locations.map(location => {
                            return (
                                <Card className="mb-3" style={{color: "#000", width: '20rem', height: '7rem'}} key={location}>
                                    <Card.Body>
                                        <Card.Title>{location}</Card.Title>
                                    </Card.Body>
                                    <Button variant="danger" onClick={() => this.deleteLocation(location)}>
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

export default Locations