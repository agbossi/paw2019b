import api from "./index";
import * as cons from './Constants.js'
import DoctorCalls from "./DoctorCalls";
import PatientCalls from "./PatientCalls";

const handleInformation = async () => {
    if(localStorage.getItem('token') === null
        || localStorage.getItem('token') === undefined || localStorage.getItem('firstName') !== null)
        return
    const email = localStorage.getItem('email')
    switch (localStorage.getItem('role')) {
        case "ROLE_DOCTOR":
            DoctorCalls.getDocByEmail(email).then(resp => {
                if (resp && resp.ok) {
                    let doctor = resp.data[0]
                    localStorage.setItem('license', doctor.license)
                    localStorage.setItem('firstName', doctor.firstName)
                    localStorage.setItem('lastName', doctor.lastName)
                    localStorage.setItem('specialty', doctor.specialty)
                    localStorage.setItem('phone', doctor.phoneNumber)
                }
            })
            break;
        case "ROLE_USER":
            PatientCalls.getProfile(email).then(resp => {
                if (resp && resp.ok) {
                    localStorage.setItem('firstName', resp.data.firstName)
                    localStorage.setItem('lastName', resp.data.lastName)
                    localStorage.setItem('prepaid', resp.data.prepaid)
                    localStorage.setItem('prepaidNumber', resp.data.prepaidNumber)
                }
            })
            break;
    }
}

const login = async (email, password) => {
    localStorage.setItem('email', email)
    const params = new URLSearchParams();
    params.append('email', email);
    params.append('password', password);
    return api.post(cons.LOGIN_PATH, params)
        .then(resp => {
            if(resp.status === 200) {
                localStorage.setItem('token', resp.headers.xAuthToken)
                localStorage.setItem('role', resp.headers.xRole)
                localStorage.setItem('email', email)
            }

            return resp
        })
}

const signUp = async (data) => {
    return api.post(cons.PATIENT_PATH, data)
}

const logout = async () => {
    localStorage.removeItem('token');
    localStorage.removeItem('role')
    localStorage.removeItem('email')
    localStorage.removeItem('license')
    localStorage.removeItem('firstName')
    localStorage.removeItem('lastName')
    localStorage.removeItem('path')
    localStorage.removeItem('specialty')
    localStorage.removeItem('phone')
}

export default {
    login,
    signUp,
    logout,
    handleInformation
}