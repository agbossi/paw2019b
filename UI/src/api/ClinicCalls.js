import api from "./index";
import * as cons from './Constants.js'


const getClinics = async (pag) => api.get(cons.CLINICS_PATH + "?" + cons.PAGE_QUERY + pag);
const addClinic = async (data) => api.post(
    cons.CLINICS_PATH,
    data,
    {headers: {'X-AUTH-TOKEN': localStorage.getItem('token')}}
)
const editClinic = async (id, data) => api.put(
    cons.CLINICS_PATH + "/" + id,
    data,
    {headers: {'X-AUTH-TOKEN': localStorage.getItem('token')}}
    )

export default {
    getClinics,
    addClinic,
    editClinic
}