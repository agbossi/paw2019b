import React from 'react';
import ReactDOM, {unmountComponentAtNode} from 'react-dom';
import App from '../App';
import {render} from "@testing-library/react";

let container = null

beforeEach(() => {
    // configurar un elemento del DOM como objetivo del renderizado
    container = document.createElement("div");
    document.body.appendChild(container);
});

afterEach(() => {
    // limpieza al salir
    unmountComponentAtNode(container);
    container.remove();
    container = null;
});

test('renders without crashing', () => {
    const div = document.createElement('div');
    ReactDOM.render(<App />, div);
    ReactDOM.unmountComponentAtNode(div);
});

test('authorization in app', () => {
    const component = render(<App />)
    console.log(component)
    //const onDeleteListHandler= component.find(childA).at(0).prop("onDeleteList");
})
