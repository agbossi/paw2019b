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

const getClinics = async (license, pag) =>
    api.get (cons.DOCTORS_PATH + "/" + license + cons.CLINICS_PATH + "?" + cons.PAGE_QUERY + pag)
const getAllClinics = async (license) =>
    api.get (cons.DOCTORS_PATH + "/" + license + cons.CLINICS_PATH + cons.ALL_PATH)
const getDocByEmail = async (email) => api.get (cons.DOCTORS_PATH + cons.EMAIL_PATH + "/" + email)
const addDoctorToClinic = async (data, license) => api.post(
    cons.DOCTORS_PATH + "/" + license + cons.CLINICS_PATH,
    data,
    {headers: {'X-AUTH-TOKEN': localStorage.getItem('token')}})

export default {
    getDoctorsAdmin,
    addDoctor,
    deleteDoctor,
    getClinics,
    getAllClinics,
    getDocByEmail,
    addDoctorToClinic
}