import api from "./index";
import * as cons from './Constants.js'

const getDoctorsAdmin = async (pag) => api.get(
    cons.DOCTORS_PATH + cons.ALL_PATH + "?" + cons.PAGE_QUERY + pag);
const addDoctor = async (data) => api.post(
    cons.DOCTORS_PATH,
    data,
    {headers: {'X-AUTH-TOKEN': localStorage.getItem('token')}}
);
const deleteDoctor = async (license) => api.delete(
    cons.DOCTORS_PATH + '/' + license,
    {},
    {headers: {'X-AUTH-TOKEN': localStorage.getItem('token')}})

export default {
    getDoctorsAdmin,
    addDoctor,
    deleteDoctor
}