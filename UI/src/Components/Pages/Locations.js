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
        }
    }

    async componentDidMount() {
        // this.setState({
        //     locations: ['moron', 'moreno', 'belgrano', 'centro', 'liniers', 'san telmo', 'quilmes', 'almagro', 'batman']
        // })
        // })
        const response = await ApiCalls.getLocations()
        if (response && response.ok)
            this.setState({locations: response.data})
    }

    deleteLocation = (name) => {
        this.setState({
            locations: this.state.locations.filter(location => location.name !== name)
        })
    }

    handleAdd = (newLocation) => {
        this.setState({
            locations: [...this.state.locations, newLocation]
        })
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
            </div>
        )
    }
}

export default Locations