import React, {useState} from 'react';
import {Form, FormControl, FormGroup, InputGroup, Button} from "react-bootstrap";
import DropDownList from "./DropDownList";

function SearchBar(props) {

    const [selectedSpecialty, setSelectedSpecialty] = useState('')
    const [selectedLocation, setSelectedLocation] = useState('')
    const [selectedPrepaid, setSelectedPrepaid] = useState('')
    const [firstName, setFirstName] = useState('')
    const [lastName, setLastName] = useState('')
    const [consultPrice, setConsultPrice] = useState(0)


    const onChange = (event) => {
        // eslint-disable-next-line default-case
        switch (event.target.id) {
            case "firstName":
                setFirstName(event.target.value)
                break;
            case "lastName":
                setLastName(event.target.value)
                break;
            case "consultPrice":
                setConsultPrice(event.target.value)
                break;
        }
    }

    const handleSelectSpecialty = (specialty) => {
        setSelectedSpecialty(specialty)
    }

    const handleSelectLocation = (location) => {
        setSelectedLocation(location)
    }

    const handleSelectPrepaid = (prepaid) => {
        setSelectedPrepaid(prepaid)
    }

    return (
        <>
            <div className="w3-sidebar w3-bar-block" style={{width: '100%'}}>
                <Form>
                    <div className="list-group-item list-group-item-action">
                        <FormGroup className="mb-3" controlId="location">
                                    <Form.Label>Location</Form.Label>
                                    <DropDownList iterable={props.locations}
                                                  selectedElement=''
                                                  handleSelect={handleSelectLocation}
                                                  elementType='Location'
                                                  id='location'/>
                                </FormGroup>
                    </div>

                    <div className="list-group-item list-group-item-action">
                        <FormGroup className="mb-3" controlId="specialty">
                                    <Form.Label>Specialty</Form.Label>
                                    <DropDownList iterable={props.specialties}
                                                  selectedElement=''
                                                  handleSelect={handleSelectSpecialty}
                                                  elementType='Specialty'
                                                  id='specialty'/>
                                </FormGroup>
                    </div>

                    <div className="list-group-item list-group-item-action">
                        <FormGroup className="mb-3" controlId="prepaid">
                                    <Form.Label>Prepaid</Form.Label>
                                    <DropDownList iterable={props.prepaids}
                                                  selectedElement=''
                                                  handleSelect={handleSelectPrepaid}
                                                  elementType='Prepaid'
                                                  id='prepaid'/>
                                </FormGroup>
                    </div>

                    <div className="list-group-item list-group-item-action">
                        <FormGroup className="mb-3" controlId="firstName">
                                    <Form.Label>First Name</Form.Label>
                                    <Form.Control placeholder="Enter first name" onChange={onChange}/>
                                </FormGroup>
                    </div>

                    <div className="list-group-item list-group-item-action">
                        <Form.Group className="mb-3" controlId="lastName">
                                    <Form.Label>Last Name</Form.Label>
                                    <Form.Control placeholder="Enter last name" onChange={onChange}/>
                                </Form.Group>
                    </div>

                    <div className="list-group-item list-group-item-action">
                        <FormGroup controlId="consultPrice">
                            <Form.Label>Consult Price</Form.Label>
                            <InputGroup className="mb-3">
                                <InputGroup.Text>$</InputGroup.Text>
                                <FormControl aria-label="Amount (to the nearest dollar)" />
                                <InputGroup.Text>.00</InputGroup.Text>
                            </InputGroup>
                        </FormGroup>
                    </div>
                    <div className="list-group-item list-group-item-action">
                        <Button variant="dark" onClick={() => props.handleSearch({
                                firstName: firstName,
                                lastName: lastName,
                                location: selectedLocation,
                                specialty: selectedSpecialty,
                                prepaid: selectedPrepaid,
                                consultPrice: consultPrice
                            }
                        )}>
                            Search
                        </Button>
                    </div>
                </Form>
            </div>
        </>
    )
}

export default SearchBar