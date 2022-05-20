import validation from '../utils/validationHelper'
import i18n from "i18next";


describe('validation functions', () => {
    test('is present detects elements', () => {
        const basicInputs = ['a', '0', 1.0, '$']
        let errors = []
        let test = 'test error'
        basicInputs.forEach(input => {
            let result = validation.isPresent(input, errors, test)
            expect(result).toBeTruthy()
            expect(errors.length).toBe(0)
        })
    })

    test('is present is false when no element', () => {
        const borderInputs = ['', 0, NaN, null, undefined]
        let errors = []
        let test = 'test error'
        borderInputs.forEach(input => {
            let result = validation.isPresent(input, errors, test)
            expect(result).toBeFalsy()
            expect(errors.length).toBe(1)
            expect(errors[0]).toEqual(test)
            errors = []
        })
    })

    test('checkDigits and optionalNumeric detects numbers', () => {
        const inputs = ['1', '999', 0, 1.0]
        let errors = []
        let test = 'test error'
        inputs.forEach(input => {
            let result = validation.checkDigits(input, errors, test)
            expect(result).toBeTruthy()
            expect(errors.length).toBe(0)

            result = validation.optionalNumeric(input, errors, test, i18n.t)
            expect(result).toBeFalsy()
            expect(errors.length).toBe(0)
        })

        let result = validation.optionalAlpha('', errors, test, i18n.t)
        expect(result).toBeFalsy()
        expect(errors.length).toBe(0)
    })

    test('checkDigits and optionalNumeric returns false when not a number', () => {
        const borderInputs = ['a', '10a1', ' ', NaN, null, undefined]
        let errors = []
        let test = 'test error'
        borderInputs.forEach(input => {
            let result = validation.checkDigits(input, errors, test)
            expect(result).toBeFalsy()
            expect(errors.length).toBe(1)
            expect(errors[0]).toEqual(test)
            errors = []
        })

        let result = validation.checkDigits('', errors, test)
        expect(result).toBeFalsy()
        expect(errors.length).toBe(1)

        result = validation.optionalNumeric('', errors, test, i18n.t)
        expect(result).toBeTruthy()
        expect(errors.length).toBe(0)
    })

    test('checkAlphanumeric detects alphanumeric', () => {
        const inputs = ['a101', '1', '999', 0, 1.0, 'abá9', 'as df']
        let errors = []
        let test = 'test error'
        inputs.forEach(input => {
            let result = validation.checkAlphaNumeric(input, errors, test)
            expect(result).toBeTruthy()
            expect(errors.length).toBe(0)
        })
    })

    test('checkAlphanumeric returns false when not alphanumeric', () => {
        const borderInputs = ['', '$', null, undefined]
        let errors = []
        let test = 'test error'
        borderInputs.forEach(input => {
            let result = validation.checkAlphaNumeric(input, errors, test)
            expect(result).toBeFalsy()
            expect(errors.length).toBe(1)
            expect(errors[0]).toEqual(test)
            errors = []
        })
    })

    test('checkAlpha and optionalAlpha detects when its alpha', () => {
        const inputs = ['abá', 'as df', 'a']
        let errors = []
        let test = 'test error'
        inputs.forEach(input => {
            let result = validation.checkAlpha(input, errors, test)
            expect(result).toBeTruthy()
            expect(errors.length).toBe(0)

            result = validation.optionalAlpha(input, errors, test, i18n.t)
            expect(result).toBeFalsy()
            expect(errors.length).toBe(0)
        })
        let result = validation.optionalAlpha('', errors, test, i18n.t)
        expect(result).toBeFalsy()
        expect(errors.length).toBe(0)
    })

    test('checkAlpha and optionalAlpha returns false when not alpha', () => {
        const borderInputs = ['$', null, undefined, 1, 1.0, '1']
        let errors = []
        let test = 'test error'
        borderInputs.forEach(input => {
            let result = validation.checkAlpha(input, errors, test)
            expect(result).toBeFalsy()
            expect(errors.length).toBe(1)
            expect(errors[0]).toEqual(test)
            errors = []
        })
        let result = validation.checkAlpha('', errors, test)
        expect(result).toBeFalsy()
        expect(errors.length).toBe(1)

        result = validation.optionalAlpha('', errors, test, i18n.t)
        expect(result).toBeTruthy()
        expect(errors.length).toBe(0)
    })

    test('requiredAlpha condition does not activate', () => {
        const inputs = ['abá', 'as df', 'a']
        let errors = []
        let test = 'test error'
        inputs.forEach(input => {
            let result = validation.requiredAlpha(input, errors, test, i18n.t)
            expect(result).toBeFalsy()
            expect(errors.length).toBe(0)
        })
    })

    test('requiredAlpha condition activates if not alpha or not present', () => {
        const notAlpha = ['$', 1.0, '1', '', null, undefined, 0]
        let errors = []
        let test = 'test error'
        notAlpha.forEach(input => {
            let result = validation.requiredAlpha(input, errors, test, i18n.t)
            expect(result).toBeTruthy()
            expect(errors.length).toBe(1)
            errors = []
        })
    })

    test('requiredNumeric condition does not activate', () => {
        const inputs = ['1', '999', '0', 1.0]
        let errors = []
        let test = 'test error'
        inputs.forEach(input => {
            let result = validation.requiredNumeric(input, errors, test, i18n.t)
            expect(result).toBeFalsy()
            expect(errors.length).toBe(0)
        })
    })

    test('requiredNumeric condition activates if not numeric or not present', () => {
        const inputs = ['10a1', 'a', ' ', NaN, '', null, undefined]
        let errors = []
        let test = 'test error'
        inputs.forEach(input => {
            let result = validation.requiredNumeric(input, errors, test, i18n.t)
            expect(result).toBeTruthy()
            expect(errors.length).toBe(1)
            errors = []
        })
    })

    test('requiredAlphanumeric condition does not activate', () => {
        const inputs = ['1', '999', '0', 1.0, 'abá', 'as df', 'a']
        let errors = []
        let test = 'test error'
        inputs.forEach(input => {
            let result = validation.requiredAlphaNumeric(input, errors, test, i18n.t)
            expect(result).toBeFalsy()
            expect(errors.length).toBe(0)
        })
    })

    test('requiredAlphanumeric condition activates if not Alphanumeric or not present', () => {
        const inputs = ['$', '', null, undefined]
        let errors = []
        let test = 'test error'
        inputs.forEach(input => {
            let result = validation.requiredAlphaNumeric(input, errors, test, i18n.t)
            expect(result).toBeTruthy()
            expect(errors.length).toBe(1)
            errors = []
        })
    })

    test('check length validation', () => {
        let errors = []
        let result;
        result = validation.checkLength('a', errors, 'test', 1, 8, i18n.t)
        expect(result).toBeTruthy()
        expect(errors.length).toBe(0)

        result = validation.checkLength('aa', errors, 'test', 3, 5, i18n.t)
        expect(result).toBeFalsy()
        expect(errors.length).toBe(1)
    })

    test('email validation passes valid emails', () => {
        let errors = []
        let test = 'test error'
        let inputs = ['admin@doctorsearch.com', 'abossi@itba.edu.ar']
        let result;
        inputs.forEach(input => {
            result = validation.requiredEmail(input, errors, test, i18n.t)
            expect(result).toBeFalsy()
            expect(errors.length).toBe(0)
        })
    })

    test('email validation detects invalid emails', () => {
        let errors = []
        let test = 'test error'
        let inputs = ['a', 1, null, undefined, 'asdf@asdf', 'a.com', '']
        let result;
        inputs.forEach(input => {
            result = validation.requiredEmail(input, errors, test, i18n.t)
            expect(result).toBeTruthy()
            expect(errors.length).toBe(1)
            errors = []
        })
    })
})