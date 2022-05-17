import {Dropdown, DropdownButton} from "react-bootstrap";
import React, {useState} from "react";
import './DropDownList.css'

function DropDownList(props) {

    const [selectedElement, setSelectedElement] = useState(props.selectedElement)

    return (
        <DropdownButton title={selectedElement || props.elementType} id={props.id} variant='secondary'>
            <Dropdown.Item id={'ddl' + props.id + 'None'}>-</Dropdown.Item>
            {props.iterable.map(element => {
                if(element === selectedElement) {
                    return <Dropdown.Item
                        id={'ddl' + props.id + element}
                        eventKey={element} active
                        onClick={() => {
                            setSelectedElement(element)
                            props.handleSelect(element)
                        }}>
                        {element}
                    </Dropdown.Item>
                } else {
                    return <Dropdown.Item eventKey={element}
                                          onClick={() => {props.handleSelect(element)}}>
                        {element}
                    </Dropdown.Item>
                }
            })}
        </DropdownButton>
    )
}

export default DropDownList