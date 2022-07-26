import React, {useEffect, useState} from 'react';
import {Form, FormControl, FormGroup, InputGroup, Button} from "react-bootstrap";
import DropDownList from "./DropDownList";
import LocationCalls from "../api/LocationCalls";
import SpecialtyCalls from "../api/SpecialtyCalls";
import PrepaidCalls from "../api/PrepaidCalls";
import {useTranslation} from "react-i18next";
import validation from "../utils/validationHelper";



function SearchBar(props) {
    const {t} = useTranslation()
    const [selectedSpecialty, setSelectedSpecialty] = useState(props.specialty === "null"? '-': props.specialty)
    const [selectedLocation, setSelectedLocation] = useState(props.location === "null"? '-': props.location)
    const [selectedPrepaid, setSelectedPrepaid] = useState(props.prepaid === "null"? '-': props.prepaid)
    const [firstName, setFirstName] = useState(props.firstName === "null"? '': props.firstName)
    const [lastName, setLastName] = useState(props.lastName === "null"? '': props.lastName)
    const [consultPrice, setConsultPrice] = useState(props.consultPrice === "null" || props.consultPrice  === "0"?
        '': props.consultPrice)
    const [locations, setLocations] = useState([])
    const [specialties, setSpecialties] = useState([])
    const [prepaids, setPrepaids] = useState([])

    const [firstNameErrors, setFirstNameErrors] = useState([])
    const [lastNameErrors, setLastNameErrors] = useState([])
    const [priceErrors, setPriceErrors] = useState([])
    const [invalidSearch, setInvalidSearch] = useState(false)


    const fetchLocations = async () => {
        const response = await LocationCalls.getAllLocations();
        if (response && response.ok) {
            const data = response.data
            data.push({name: '-'})
            setLocations(data);

        }
    }

    const fetchSpecialties = async () => {
        const response = await SpecialtyCalls.getAllSpecialties();
        if (response && response.ok) {
            const data = response.data
            data.push({name: '-'})
            setSpecialties(data);
        }

    }

    const fetchPrepaids = async () => {
        const response = await PrepaidCalls.getAllPrepaids();
        if (response && response.ok) {
            const data = response.data
            data.push({name: '-'})
            setPrepaids(data);
        }
    }

    useEffect(async () => {
        await fetchPrepaids();
        await fetchLocations();
        await fetchSpecialties();
    }, [])

    const onChange = (event) => {
        // eslint-disable-next-line default-case
        let error = false
        let errors = []
        switch (event.target.id) {
            case "firstName":
                setFirstName(event.target.value)
                error = error || validation.optionalAlpha(event.target.value, errors, "firstName", t)
                setFirstNameErrors(errors)
                break;
            case "lastName":
                setLastName(event.target.value)
                error = error || validation.optionalAlpha(event.target.value, errors, "lastName", t)
                setLastNameErrors(errors)
                break;
            case "consultPrice":
                setConsultPrice(event.target.value)
                error = error || validation.optionalNumeric(event.target.value, errors, "price", t)
                setPriceErrors(errors)
                break;
        }
        setInvalidSearch(error)
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

    const clearFilters = () => {
        setSelectedLocation('-')
        setSelectedPrepaid('-')
        setSelectedSpecialty('-')
        setConsultPrice('')
        setFirstName('')
        setLastName('')

        setInvalidSearch(false)
        setPriceErrors([])
        setFirstNameErrors([])
        setLastNameErrors([])
    }

    return (
        <>
            <div className="w3-sidebar w3-bar-block" style={{width: '100%'}}>
                <Form>
                    <div className="list-group-item list-group-item-action">
                        <FormGroup className="mb-3" controlId="location">
                                    <Form.Label>{t("FORM.location")} {selectedLocation}</Form.Label>
                                    <DropDownList iterable={locations.map(loc => loc.name)}
                                                  selectedElement=''
                                                  handleSelect={handleSelectLocation}
                                                  elementType={t("FORM.selectLocation")}
                                                  id='location'/>
                                </FormGroup>
                    </div>

                    <div className="list-group-item list-group-item-action">
                        <FormGroup className="mb-3" controlId="specialty">
                                    <Form.Label>{t("FORM.specialty")} {selectedSpecialty}</Form.Label>
                                    <DropDownList iterable={specialties.map(spe => spe.name)}
                                                  selectedElement=''
                                                  handleSelect={handleSelectSpecialty}
                                                  elementType={t("FORM.selectSpecialty")}
                                                  id='specialty'/>
                                </FormGroup>
                    </div>

                    <div className="list-group-item list-group-item-action">
                        <FormGroup className="mb-3" controlId="prepaid">
                                    <Form.Label>{t("ADMIN.prepaid")}: {selectedPrepaid}</Form.Label>
                                    <DropDownList iterable={prepaids.map(pre => pre.name)}
                                                  selectedElement=''
                                                  handleSelect={handleSelectPrepaid}
                                                  elementType={t("FORM.selectPrepaid")}
                                                  id='prepaid'/>
                                </FormGroup>
                    </div>

                    <div className="list-group-item list-group-item-action">
                        <FormGroup className="mb-3" controlId="firstName">
                                    <Form.Label>{t("FORM.firstName")}</Form.Label>
                                    <Form.Control placeholder={t("FORM.enterFirstName")}
                                                  value={firstName}
                                                  onChange={onChange}/>
                        </FormGroup>
                        {firstNameErrors.length !== 0 && (
                            <div className="form-group">
                                <div className="alert alert-danger" role="alert">
                                    <ul id='firstNameErrors'>
                                        {firstNameErrors.map((error, index) => {
                                            return (
                                                <li key={index}>{error}</li>
                                            )
                                        })}
                                    </ul>
                                </div>
                            </div>
                        )}
                    </div>

                    <div className="list-group-item list-group-item-action">
                        <Form.Group className="mb-3" controlId="lastName">
                                    <Form.Label>{t("FORM.lastName")}</Form.Label>
                                    <Form.Control placeholder={t("FORM.enterLastName")}
                                                  value={lastName}
                                                  onChange={onChange}/>
                        </Form.Group>
                        {lastNameErrors.length !== 0 && (
                            <div className="form-group">
                                <div className="alert alert-danger" role="alert">
                                    <ul id='lastNameErrors'>
                                        {lastNameErrors.map((error, index) => {
                                            return (
                                                <li key={index}>{error}</li>
                                            )
                                        })}
                                    </ul>
                                </div>
                            </div>
                        )}
                    </div>

                    <div className="list-group-item list-group-item-action">
                        <FormGroup controlId="consultPrice">
                            <Form.Label>{t("FORM.maxPrice")}</Form.Label>
                            <InputGroup className="mb-3">
                                <InputGroup.Text>$</InputGroup.Text>
                                <FormControl aria-label="Amount (to the nearest dollar)"
                                             value={consultPrice}
                                             placeholder={t("FORM.enterConsultPrice")}
                                             onChange={onChange}/>
                                <InputGroup.Text>.00</InputGroup.Text>
                            </InputGroup>
                        </FormGroup>
                        {priceErrors.length !== 0 && (
                            <div className="form-group">
                                <div className="alert alert-danger" role="alert">
                                    <ul id='priceErrors'>
                                        {priceErrors.map((error, index) => {
                                            return (
                                                <li key={index}>{error}</li>
                                            )
                                        })}
                                    </ul>
                                </div>
                            </div>
                        )}
                    </div>
                    <div className="list-group-item list-group-item-action">
                        <Button className="doc-button-color" id='clearButton' onClick={() => clearFilters()}>
                            {t("FORM.clear")}
                        </Button>
                        <br/>
                        <Button disabled={invalidSearch}
                                id='submitButton'
                                className="doc-button-color" onClick={() => props.handleSearch({
                                firstName: firstName,
                                lastName: lastName,
                                location: selectedLocation === '-'? null : selectedLocation,
                                specialty: selectedSpecialty === '-'? null : selectedSpecialty,
                                prepaid: selectedPrepaid === '-'? null : selectedPrepaid,
                                consultPrice: consultPrice
                            }, 0
                        )}>
                            {t("FORM.search")}
                        </Button>
                    </div>
                </Form>
            </div>
        </>
    )
}

export default SearchBar