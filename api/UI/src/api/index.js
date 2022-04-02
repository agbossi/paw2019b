import axios from 'axios';
import { create } from 'apisauce';
import applyCaseMiddleware from 'axios-case-converter';
import {BASE_URL} from "../Constants";

const options = {
    preservedKeys: ['firstName', 'lastName', 'repeatPassword', 'newPassword',
        'phoneNumber', 'X-AUTH-TOKEN', 'consultPrice', 'profileImage', 'prepaidNumber']
}
const api = applyCaseMiddleware(axios.create({
    baseURL: BASE_URL
}), options);

api.interceptors.request.use(
    request => {
        request.headers['X-AUTH-TOKEN'] = localStorage.getItem('token')
        return request
    }, error => {
        return Promise.reject(error)
    }
)

api.interceptors.response.use(
    res => {
        return res
    },
    error => {
        if(error.response.status === 401) {
            localStorage.removeItem('token')
            localStorage.removeItem('role')
            localStorage.removeItem('license')
            localStorage.removeItem('firstName')
            localStorage.removeItem('email')
            localStorage.removeItem('lastName')
            localStorage.removeItem('specialty')
            localStorage.removeItem('phone')
            console.log('interceptor response')
            window.location.replace("http://pawserver.it.itba.edu.ar/paw-2019b-4/login")
        }
    }
)

export default create({ axiosInstance: api });