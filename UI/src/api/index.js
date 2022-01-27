import axios from 'axios';
import { create } from 'apisauce';
import applyCaseMiddleware from 'axios-case-converter';

const options = {
    preservedKeys: ['firstName', 'lastName', 'repeatPassword', 'phoneNumber', 'X-AUTH-TOKEN']
}
const api = applyCaseMiddleware(axios.create({
    baseURL: "http://localhost:8080"
}), options);

export default create({ axiosInstance: api });