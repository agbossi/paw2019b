import {unmountComponentAtNode} from "react-dom";
import SearchBar from "../Components/SearchBar";
import '@testing-library/jest-dom/extend-expect';
import "@testing-library/dom";
import {act, render} from "@testing-library/react";
import React from "react";
import LanguageDetector from "i18next-browser-languagedetector";
import i18n from "i18next";
import {TRANSLATION_ES} from "../i18n/translations-es";
import {TRANSLATION_EN} from "../i18n/translations-en";
import {initReactI18next} from "react-i18next";
import {fireEvent} from "@testing-library/dom";

i18n
    .use(LanguageDetector)
    .use(initReactI18next)
    .init({
        resources: {
            en: {
                translation: TRANSLATION_EN
            },
            es: {
                translation: TRANSLATION_ES
            }
        }
    });
const mockedUsedNavigate = jest.fn();

jest.mock('react-router-dom', () => ({
    ...jest.requireActual('react-router-dom'),
    useNavigate: () => mockedUsedNavigate,
}));

let container = null;

function checkRender(component, field) {
    let text = i18n.t('FORM.firstName')
    let label = component.getByText(text)
    let control = component.getByPlaceholderText(i18n.t('FORM.enter' + field.charAt(0).toUpperCase() + field.slice(1)))
    expect(label).not.toBeNull()
    expect(label).not.toBeUndefined()
    expect(control).not.toBeNull()
    expect(control).not.toBeUndefined()
}

function checkRenderDdl(btnId, itemId) {
    let control = document.querySelector(btnId)
    act(() => {
        fireEvent.click(control)
    })
    let ddl = document.querySelector(itemId)
    expect(control).not.toBeNull()
    expect(control).not.toBeUndefined()
    expect(ddl).not.toBeNull()
    expect(ddl).not.toBeUndefined()
}

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

describe('searchBar tests', () => {

    test('searchbar renders', () => {
        let label;
        let control;
        const handleSearch = jest.fn();

        const component = render(<SearchBar handleSearch={handleSearch}
                                            location=''
                                            specialty=''
                                            firstName=''
                                            lastName=''
                                            prepaid=''
                                            consultPrice=''/>)

        checkRender(component, 'firstName')
        checkRender(component, 'lastName')
        label = component.getByText(i18n.t('FORM.maxPrice'))
        control = component.getByPlaceholderText(i18n.t('FORM.enterConsultPrice'))
        expect(label).not.toBeNull()
        expect(label).not.toBeUndefined()
        expect(control).not.toBeNull()
        expect(control).not.toBeUndefined()
        checkRenderDdl('#location', '#ddllocationNone')
        checkRenderDdl('#specialty', '#ddlspecialtyNone')
        checkRenderDdl('#prepaid', '#ddlprepaidNone')

        let btn = document.querySelector('#submitButton')
        expect(btn).not.toBeNull()
        expect(btn).not.toBeUndefined()
        expect(btn).toHaveTextContent(i18n.t('FORM.search'))
        expect(btn).toBeEnabled()
        btn = document.querySelector('#clearButton')
        expect(btn).not.toBeNull()
        expect(btn).not.toBeUndefined()
        expect(btn).toHaveTextContent(i18n.t('FORM.clear'))
        expect(btn).toBeEnabled()
    })

    test('clear filters clears search fields', () => {
        let testInput = 'test'
        const handleSearch = jest.fn();

        const component = render(<SearchBar handleSearch={handleSearch}
                                            location=''
                                            specialty=''
                                            firstName=''
                                            lastName=''
                                            prepaid=''
                                            consultPrice=''/>)
        let firstNameControl = component.getByPlaceholderText(i18n.t('FORM.enterFirstName'))
        fireEvent.change(firstNameControl, {target: {value: testInput}})
        let lastNameControl = component.getByPlaceholderText(i18n.t('FORM.enterLastName'))
        fireEvent.change(lastNameControl, {target: {value: testInput}})
        let priceControl = component.getByPlaceholderText(i18n.t('FORM.enterConsultPrice'))
        fireEvent.change(priceControl, {target: {value: testInput}})

        expect(firstNameControl.value).toEqual(testInput)
        expect(lastNameControl.value).toEqual(testInput)
        expect(priceControl.value).toEqual(testInput)

        let clearBtn = document.querySelector('#clearButton')
        fireEvent.click(clearBtn)

        expect(firstNameControl.value).toEqual('')
        expect(lastNameControl.value).toEqual('')
        expect(priceControl.value).toEqual('')
    })

    test('clear filters reestablishes errors', () => {
        const handleSearch = jest.fn();

        const component = render(<SearchBar handleSearch={handleSearch}
                                            location=''
                                            specialty=''
                                            firstName=''
                                            lastName=''
                                            prepaid=''
                                            consultPrice=''/>)
        let firstNameControl = component.getByPlaceholderText(i18n.t('FORM.enterFirstName'))
        fireEvent.change(firstNameControl, {target: {value: '1234'}})
        let lastNameControl = component.getByPlaceholderText(i18n.t('FORM.enterLastName'))
        fireEvent.change(lastNameControl, {target: {value: '1234'}})
        let priceControl = component.getByPlaceholderText(i18n.t('FORM.enterConsultPrice'))
        fireEvent.change(priceControl, {target: {value: 'test'}})

        let priceErrorList = document.querySelector('#priceErrors')
        expect(priceErrorList.children.length).toBeGreaterThan(0)
        let firstNameErrorList = document.querySelector('#lastNameErrors')
        expect(firstNameErrorList.children.length).toBeGreaterThan(0)
        let lastNameErrorList = document.querySelector('#firstNameErrors')
        expect(lastNameErrorList.children.length).toBeGreaterThan(0)

        let clearBtn = document.querySelector('#clearButton')
        fireEvent.click(clearBtn)
        lastNameErrorList = document.querySelector('#firstNameErrors')
        firstNameErrorList = document.querySelector('#lastNameErrors')
        priceErrorList = document.querySelector('#priceErrors')
        expect(priceErrorList).toBeNull()
        expect(firstNameErrorList).toBeNull()
        expect(lastNameErrorList).toBeNull()
    })

    test('firstName field errors are correct', () => {
        const handleSearch = jest.fn();

        const component = render(<SearchBar handleSearch={handleSearch}
                                            location=''
                                            specialty=''
                                            firstName=''
                                            lastName=''
                                            prepaid=''
                                            consultPrice=''/>)
        let firstNameControl = component.getByPlaceholderText(i18n.t('FORM.enterFirstName'))
        fireEvent.change(firstNameControl, {target: {value: '1234'}})

        let list = document.querySelector("#firstNameErrors")
        let msg = list.children.item(0).textContent
        expect(msg).toBe(i18n.t("FORM.firstName") + "  " + i18n.t("errors.alphabetic"))

        const btn = document.querySelector('#submitButton')
        expect(btn).toBeDisabled()

        fireEvent.change(firstNameControl, {target: {value: 'asdf'}})

        list = document.querySelector("#firstNameErrors")
        expect(list).toBeNull()
        expect(btn).toBeEnabled()
    })

    test('lastName field errors are correct', () => {
        const handleSearch = jest.fn();

        const component = render(<SearchBar handleSearch={handleSearch}
                                            location=''
                                            specialty=''
                                            firstName=''
                                            lastName=''
                                            prepaid=''
                                            consultPrice=''/>)
        let lastNameControl = component.getByPlaceholderText(i18n.t('FORM.enterLastName'))
        fireEvent.change(lastNameControl, {target: {value: '1234'}})

        let list = document.querySelector("#lastNameErrors")
        let msg = list.children.item(0).textContent
        expect(msg).toBe(i18n.t("FORM.lastName") + "  " + i18n.t("errors.alphabetic"))

        const btn = document.querySelector('#submitButton')
        expect(btn).toBeDisabled()

        fireEvent.change(lastNameControl, {target: {value: 'asdf'}})
        list = document.querySelector("#lastNameErrors")
        expect(list).toBeNull()
        expect(btn).toBeEnabled()
    })

    test('consult price field errors are correct', () => {
        const handleSearch = jest.fn();

        const component = render(<SearchBar handleSearch={handleSearch}
                                            location=''
                                            specialty=''
                                            firstName=''
                                            lastName=''
                                            prepaid=''
                                            consultPrice=''/>)
        let priceControl = component.getByPlaceholderText(i18n.t('FORM.enterConsultPrice'))
        fireEvent.change(priceControl, {target: {value: 'asdf'}})

        let list = document.querySelector("#priceErrors")
        let msg = list.children.item(0).textContent
        expect(msg).toBe(i18n.t("FORM.price") + "  " + i18n.t("errors.numeric"))

        const btn = document.querySelector('#submitButton')
        expect(btn).toBeDisabled()

        fireEvent.change(priceControl, {target: {value: '123'}})
        list = document.querySelector("#lastNameErrors")
        expect(list).toBeNull()
        expect(btn).toBeEnabled()
    })
})

