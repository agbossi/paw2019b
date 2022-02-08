import axios from 'axios';
import { create } from 'apisauce';
import applyCaseMiddleware from 'axios-case-converter';

const options = {
    preservedKeys: ['firstName', 'lastName', 'repeatPassword', 'newPassword',
        'phoneNumber', 'X-AUTH-TOKEN', 'consultPrice', 'profileImage']
}
const api = applyCaseMiddleware(axios.create({
    baseURL: "http://localhost:8080"
}), options);

export default create({ axiosInstance: api });