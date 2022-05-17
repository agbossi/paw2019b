import {render} from "@testing-library/react";
import '@testing-library/jest-dom/extend-expect';
import React from "react";
import { unmountComponentAtNode } from "react-dom";
import SignUp from "../Components/Pages/SignUp";
import i18n from "i18next";
import {fireEvent} from "@testing-library/dom";

import { initReactI18next } from "react-i18next";
import LanguageDetector from "i18next-browser-languagedetector";
import {TRANSLATION_ES} from "../i18n/translations-es";
import {TRANSLATION_EN} from "../i18n/translations-en";

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

const invalidFormInput = {
    firstName: '1234',
    lastName: '1234',
    email: '1234',
    password: '1234',
    repeatPassword: '123',
    document: 'doc',
    prepaidNumber: 'o'
}

const validFormInput = {
    firstName: 'asdf',
    lastName: 'asdf',
    email: 'asdf@asdf.com',
    password: '12345678',
    repeatPassword: '12345678',
    document: '12345678',
    prepaidNumber: '12345678'
}

const fields = ['firstName', 'lastName', 'prepaidNumber', 'document', 'email', 'password', 'repeatPassword']


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

describe('signup tests', () => {
    test('renders content', () => {
        let label;
        let control;
        const component = render(<SignUp />)
        fields.forEach(field => {
            label = component.getByText(i18n.t('FORM.' + field))
            control = component.getByPlaceholderText(i18n.t('FORM.enter' + field.charAt(0).toUpperCase() + field.slice(1)))
            expect(label).not.toBeNull()
            expect(label).not.toBeUndefined()
            expect(label).toHaveTextContent(i18n.t('FORM.' + field))
            expect(control).not.toBeNull()
            expect(control).not.toBeUndefined()
            expect(control.getAttribute("placeholder")).toBe(i18n.t('FORM.enter' + field.charAt(0).toUpperCase() + field.slice(1)))
        })
        const btn = document.querySelector('#submitButton')
        expect(btn).toHaveTextContent(i18n.t('FORM.signUp'))
        expect(btn).toBeDisabled()
    })

    test('field error messages render', () => {
        let control;
        const component = render(<SignUp />)
        fields.forEach(field => {
            control = component.getByPlaceholderText(i18n.t('FORM.enter' + field.charAt(0).toUpperCase() + field.slice(1)))
            fireEvent.change(control, {target: {value: invalidFormInput[field]}})
            const errorList = document.querySelector('#' + field + 'Errors')
            expect(errorList.children.length).toBeGreaterThan(0)
        })
        const btn = document.querySelector('#submitButton')
        expect(btn).toBeDisabled()
    })

    test('firstName field errors are correct', () => {
        const component = render(<SignUp />)
        let firstNameControl = component.getByPlaceholderText(i18n.t('FORM.enterFirstName'))
        fireEvent.change(firstNameControl, {target: {value: invalidFormInput['firstName']}})

        let list = document.querySelector("#firstNameErrors")
        let msg = list.children.item(0).textContent
        expect(msg).toBe(i18n.t("FORM.firstName") + "  " + i18n.t("errors.alphabetic"))
        fireEvent.change(firstNameControl, {target: {value: ''}})

        msg = list.children.item(0).textContent
        expect(msg).toBe(i18n.t("FORM.firstName") + "  " + i18n.t("errors.required"))
        fireEvent.change(firstNameControl, {target: {value: validFormInput['firstName']}})

        list = document.querySelector("#firstNameErrors")
        expect(list).toBeNull()

        const btn = document.querySelector('#submitButton')
        expect(btn).toBeDisabled()
    })

    test('lastName field errors are correct', () => {
        const component = render(<SignUp />)
        let lastNameControl = component.getByPlaceholderText(i18n.t('FORM.enterLastName'))
        fireEvent.change(lastNameControl, {target: {value: invalidFormInput['lastName']}})

        let list = document.querySelector("#lastNameErrors")
        let msg = list.children.item(0).textContent
        expect(msg).toBe(i18n.t("FORM.lastName") + "  " + i18n.t("errors.alphabetic"))

        fireEvent.change(lastNameControl, {target: {value: ''}})
        msg = list.children.item(0).textContent
        expect(msg).toBe(i18n.t("FORM.lastName") + "  " + i18n.t("errors.required"))

        fireEvent.change(lastNameControl, {target: {value: validFormInput['lastName']}})
        list = document.querySelector("#lastNameErrors")
        expect(list).toBeNull()
        const btn = document.querySelector('#submitButton')
        expect(btn).toBeDisabled()
    })

    test('document errors are correct', () => {
        let charConstraint = 8
        const component = render(<SignUp />)
        let documentControl = component.getByPlaceholderText(i18n.t('FORM.enterDocument'))
        fireEvent.change(documentControl, {target: {value: invalidFormInput['document']}})

        let list = document.querySelector("#documentErrors")
        let msg = list.children.item(0).textContent
        expect(msg).toBe(i18n.t("FORM.document") + "  " + i18n.t("errors.numeric"))

        fireEvent.change(documentControl, {target: {value: ''}})
        msg = list.children.item(0).textContent
        expect(msg).toBe(i18n.t("FORM.document") + "  " + i18n.t("errors.required"))

        fireEvent.change(documentControl, {target: {value: '1'}})
        msg = list.children.item(0).textContent
        expect(msg).toBe(i18n.t("FORM.document") + "  " + i18n.t("errors.invalidLength") + " " + charConstraint + " " + i18n.t("characters"))

        fireEvent.change(documentControl, {target: {value: validFormInput['document']}})
        list = document.querySelector("#documentErrors")
        expect(list).toBeNull()
        const btn = document.querySelector('#submitButton')
        expect(btn).toBeDisabled()
    })

    test('email errors are correct', () => {
        const component = render(<SignUp />)
        let emailControl = component.getByPlaceholderText(i18n.t('FORM.enterEmail'))
        fireEvent.change(emailControl, {target: {value: invalidFormInput['email']}})

        let list = document.querySelector("#emailErrors")
        let msg = list.children.item(0).textContent
        expect(msg).toBe(i18n.t("errors.invalidEmail"))

        fireEvent.change(emailControl, {target: {value: ''}})
        msg = list.children.item(0).textContent
        expect(msg).toBe(i18n.t("FORM.email") + "  " + i18n.t("errors.required"))

        fireEvent.change(emailControl, {target: {value: validFormInput['email']}})
        list = document.querySelector("#emailErrors")
        expect(list).toBeNull()

        const btn = document.querySelector('#submitButton')
        expect(btn).toBeDisabled()
    })

    test('password errors are correct', () => {
        const component = render(<SignUp />)
        let passwordControl = component.getByPlaceholderText(i18n.t('FORM.enterPassword'))
        fireEvent.change(passwordControl, {target: {value: invalidFormInput['password']}})

        let list = document.querySelector("#passwordErrors")
        let msg = list.children.item(0).textContent
        expect(msg).toBe(i18n.t("FORM.password") + "  " + i18n.t("errors.short"))
        fireEvent.change(passwordControl, {target: {value: '000000000000000000000'}})
        msg = list.children.item(0).textContent
        expect(msg).toBe(i18n.t("FORM.password") + "  " + i18n.t("errors.long"))

        fireEvent.change(passwordControl, {target: {value: ''}})
        msg = list.children.item(0).textContent
        expect(msg).toBe(i18n.t("FORM.password") + "  " + i18n.t("errors.required"))

        fireEvent.change(passwordControl, {target: {value: validFormInput['password']}})
        msg = list.children.item(0).textContent
        expect(msg).toBe(i18n.t("errors.passwordMismatch"))

        let repeatPasswordControl = component.getByPlaceholderText(i18n.t('FORM.enterRepeatPassword'))
        fireEvent.change(repeatPasswordControl, {target: {value: invalidFormInput['repeatPassword']}})

        let repeatList = document.querySelector("#repeatPasswordErrors")
        msg = repeatList.children.item(0).textContent
        expect(msg).toBe(i18n.t("errors.passwordMismatch"))

        fireEvent.change(repeatPasswordControl, {target: {value: validFormInput['password']}})
        fireEvent.change(passwordControl, {target: {value: validFormInput['repeatPassword']}})

        repeatList = document.querySelector("#repeatPasswordErrors")
        expect(repeatList).toBeNull()

        const btn = document.querySelector('#submitButton')
        expect(btn).toBeDisabled()
    })

    test('prepaid number errors are correct', () => {
        const component = render(<SignUp />)
        let prepaidNumberControl = component.getByPlaceholderText(i18n.t('FORM.enterPrepaidNumber'))
        fireEvent.change(prepaidNumberControl, {target: {value: invalidFormInput['prepaidNumber']}})

        let list = document.querySelector("#prepaidNumberErrors")
        let msg = list.children.item(0).textContent
        expect(msg).toBe(i18n.t("FORM.prepaidNumber") + "  " + i18n.t("errors.numeric"))

        fireEvent.change(prepaidNumberControl, {target: {value: ''}})
        msg = list.children.item(0).textContent
        expect(msg).toBe(i18n.t("FORM.prepaidNumber") + "  " + i18n.t("errors.required"))

        fireEvent.change(prepaidNumberControl, {target: {value: '1234'}})
        msg = list.children.item(0).textContent
        expect(msg).toBe(i18n.t("FORM.prepaidNumber") + "  " + i18n.t("errors.short"))
        fireEvent.change(prepaidNumberControl, {target: {value: '000000000000000000000'}})
        msg = list.children.item(0).textContent
        expect(msg).toBe(i18n.t("FORM.prepaidNumber") + "  " + i18n.t("errors.long"))

        fireEvent.change(prepaidNumberControl, {target: {value: validFormInput['prepaidNumber']}})
        list = document.querySelector("#prepaidNumberErrors")
        expect(list).toBeNull()

        const btn = document.querySelector('#submitButton')
        expect(btn).toBeDisabled()
    })
})



