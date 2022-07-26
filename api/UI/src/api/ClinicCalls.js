import api from "./index";
import * as cons from './Constants.js'
import {PREPAID_QUERY, PREPAIDS_PATH} from "./Constants.js";

// Clinic calls and clinic prepaids
const getClinics = async (pag) => api.get(cons.CLINICS_PATH + "?" + cons.PAGE_QUERY + pag);
const getAllClinics = async () => api.get(cons.CLINICS_PATH  + "?" + cons.MODE + "all");
const getClinic = async (id) => api.get(cons.CLINICS_PATH + "/" + id)
const getClinicPrepaids = async (id, pag) =>
    api.get(cons.CLINICS_PATH + "/" + id + PREPAIDS_PATH + "?" + cons.PAGE_QUERY + pag)
const getAllClinicPrepaids = async (id) => api.get(cons.CLINICS_PATH + "/" + id + PREPAIDS_PATH + "?" + cons.MODE + "all")
const addClinic = async (data) => api.post(cons.CLINICS_PATH, data)
const addClinicPrepaid = async (clinicId, data) => api.post(
    cons.CLINICS_PATH + "/" + clinicId + PREPAIDS_PATH, data)
const editClinic = async (id, data) => api.put(
    cons.CLINICS_PATH + "/" + id, data)
const deleteClinic = async (id) => api.delete(cons.CLINICS_PATH + "/" + id, {})
const deleteClinicPrepaid = async (clinicId, prepaidId) => api.delete(
    cons.CLINICS_PATH + "/" + clinicId + PREPAIDS_PATH + "?" + PREPAID_QUERY + prepaidId, {})
export default {
    getClinics,
    getAllClinics,
    getClinic,
    getClinicPrepaids,
    getAllClinicPrepaids,
    addClinic,
    addClinicPrepaid,
    editClinic,
    deleteClinic,
    deleteClinicPrepaid
}