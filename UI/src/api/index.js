import axios from 'axios';
import { create } from 'apisauce';
import applyCaseMiddleware from 'axios-case-converter';

const api = applyCaseMiddleware(axios.create({
    baseURL: "http://localhost:8080"
}));

export default create({ axiosInstance: api });