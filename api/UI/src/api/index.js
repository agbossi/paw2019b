import axios from 'axios';
import { create } from 'apisauce';
import applyCaseMiddleware from 'axios-case-converter';
import {BASE_URL} from "../Constants";
import ApiCalls from  "./apiCalls";

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
    },
    error => {
        console.log(error)
    }
)

api.interceptors.response.use(
    res => {
        return res
    },
    error => {
        if (error.response) {
            // Request made and server responded
            if (error.response.status === 401) {
                ApiCalls.logout()
                window.location.replace("http://pawserver.it.itba.edu.ar/paw-2019b-4/login")
            }
        }
        return Promise.reject(error);
    }
)

export default create({ axiosInstance: api });