

const isPresent = (value, errors, message) => {
    let is = true
    if(!value) {
        is = false
        errors.push(message)
    }
    return is
}

const checkDigits = (value, errors, message) => {
    let isDigit = true
    if(!/^\d+$/.test(value)) {
        isDigit = false
        errors.push(message)
    }
    return isDigit
}

const checkAlphaNumeric = (value, errors, message) => {
    let isAlphaNum = true
    if(!/^[a-zA-ZÀ-ÿ0-9\s]{1,40}$/.test(value)) {
        errors.push(message)
        isAlphaNum = false
    }
    return isAlphaNum
}

const checkAlpha = (value, errors, message) => {
    let isAlpha = true
    if(!/^[a-zA-ZÀ-ÿ\s]{1,40}$/.test(value)) {
        errors.push(message)
        isAlpha = false
    }
    return isAlpha
}

const requiredAlpha = (value, errors, field, t) => {
    return !isPresent(value, errors, t("FORM." + field) + "  " + t("errors.required"))
        || !checkAlpha(value, errors, t("FORM." + field) + "  " + t("errors.alphabetic"))
}

const requiredNumeric = (value, errors, field, t) => {
    return !isPresent(value, errors, t("FORM." + field) + "  " + t("errors.required"))
        || !checkDigits(value, errors, t("FORM." + field) + "  " + t("errors.numeric"))
}

const requiredAlphaNumeric = (value, errors, field, t) => {
    return !isPresent(value, errors, t("FORM." + field) + "  " + t("errors.required"))
        || !checkAlphaNumeric(value, errors, t("FORM." + field) + "  " + t("errors.alphanumeric"))
}

const optionalNumeric = (value, errors, field, t) => {
    if(value !== '') {
        return !checkDigits(value, errors, t("FORM." + field) + "  " + t("errors.numeric"))
    }
    return false
}

const optionalAlpha = (value, errors, field, t) => {
    console.log('value in optional alpa: ' + value)
    if (value !== '') {
        return !checkAlpha(value, errors, t("FORM." + field) + "  " + t("errors.alphabetic"))
    }
    return false
}

const checkLength = (value, errors, field, min, max, t) => {
    let validLength = true
    if(value.length < min) {
        errors.push(t("FORM." + field) + "  " + t("errors.short"))
        validLength = false
    } else if(value.length > max) {
        errors.push(t("FORM." + field) + "  " + t("errors.long"))
        validLength = false
    }
    return validLength
}

const requiredLength = (value, errors, field, min, max, t) => {
    return !isPresent(value, errors, t("FORM." + field) + "  " + t("errors.required"))
        || !checkLength(value, errors, field, min, max, t)
}

const checkLengthExact = (value, errors, field, exact, t) => {
    let validLength = true
    if(value.length !== exact) {
        errors.push(t("FORM." + field) + "  " + t("errors.invalidLength") + " " + exact + " " + t("characters"))
        validLength = false
    }
    return validLength
}

const requiredEmail = (value, errors, field, t) => {
    return !isPresent(value, errors, t("FORM." + field) + "  " + t("errors.required"))
        || !validateEmail(value, errors, t("errors.invalidEmail"))
}

const passwordMatch = (value, errors, repeatPassword, t) => {
    let match = true
    if(repeatPassword !== value) {
        errors.push([t("errors.passwordMismatch")])
        match = false
    }
    console.log('pass ' + value)
    console.log('repeat pass ' + repeatPassword)
    console.log('match ' + match)
    return match
}

const checkEmail = (values, errors, message) => {
    let isEmail = true
    if(!/^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/.test(values.email)) {
        isEmail = false
        errors.push(message)
    }
    return isEmail
}

const validateEmail = (email, errors, message) => {
    let isEmail = String(email)
        .toLowerCase()
        .match(
            /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        );
    if(!isEmail)
        errors.push(message)
    return isEmail
};

export default {
    isPresent,
    checkAlpha,
    requiredAlpha,
    requiredNumeric,
    requiredEmail,
    checkLength,
    checkLengthExact,
    requiredLength,
    passwordMatch,
    optionalNumeric,
    optionalAlpha,
    requiredAlphaNumeric
}