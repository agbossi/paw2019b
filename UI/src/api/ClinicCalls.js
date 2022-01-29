import api from "./index";
import * as cons from './Constants.js'
import {PREPAIDS_PATH} from "./Constants.js";


const getClinics = async (pag) => api.get(cons.CLINICS_PATH + "?" + cons.PAGE_QUERY + pag);
const getClinic = async (id) => api.get(cons.CLINICS_PATH + "/" + id)
const getClinicPrepaids = async (id) => api.get(cons.CLINICS_PATH + "/" + id + PREPAIDS_PATH)
const getAllClinicPrepaids = async (id) => api.get(cons.CLINICS_PATH + "/" + id + PREPAIDS_PATH + cons.ALL_PATH )
const addClinic = async (data) => api.post(
    cons.CLINICS_PATH,
    data,
    {headers: {'X-AUTH-TOKEN': localStorage.getItem('token')}}
)
const addClinicPrepaid = async (clinicId, prepaidId) => api.post(
    cons.CLINICS_PATH + "/" + clinicId + PREPAIDS_PATH + '/' + prepaidId,
    {},
    {headers: {'X-AUTH-TOKEN': localStorage.getItem('token')}})
const editClinic = async (id, data) => api.put(
    cons.CLINICS_PATH + "/" + id,
    data,
    {headers: {'X-AUTH-TOKEN': localStorage.getItem('token')}}
    )
const deleteClinic = async (id) => api.delete(
    cons.CLINICS_PATH + "/" + id,
    {},
    {headers: {'X-AUTH-TOKEN': localStorage.getItem('token')}})
const deleteClinicPrepaid = async (clinicId, prepaidId) => api.delete(
    cons.CLINICS_PATH + "/" + clinicId + PREPAIDS_PATH + "/" + prepaidId,
    {},
    {headers: {'X-AUTH-TOKEN': localStorage.getItem('token')}})
export default {
    getClinics,
    getClinic,
    getClinicPrepaids,
    getAllClinicPrepaids,
    addClinic,
    addClinicPrepaid,
    editClinic,
    deleteClinic,
    deleteClinicPrepaid
}