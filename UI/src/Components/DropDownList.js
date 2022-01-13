import {Dropdown} from "react-bootstrap";
import React, {useState} from "react";

function DropDownList(props) {

    const [selectedElement, setSelectedElement] = useState(props.selectedElement)

    return (
        <Dropdown>
            <Dropdown.Toggle  id={props.id} variant="secondary">
                {'Select ' + props.elementType}
            </Dropdown.Toggle>
            <Dropdown.Menu variant="dark">
                {props.iterable.map(element => {
                    if(element === selectedElement) {
                        return <Dropdown.Item
                            eventKey={element} active
                            onClick={() => {props.handleSelect(element)}}>
                            {element}
                        </Dropdown.Item>
                    } else {
                        return <Dropdown.Item eventKey={element}
                                              onClick={() => {props.handleSelect(element)}}>
                            {element}
                        </Dropdown.Item>
                    }
                })}
            </Dropdown.Menu>
        </Dropdown>
    )
}

export default DropDownList