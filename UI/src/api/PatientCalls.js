import api from "./index";
import {PATIENT_PATH} from "./Constants.js";

const getFavoriteDoctors = async (id) => api.get(PATIENT_PATH + "/" + id)
const deleteFavoriteDoctor = async (id, license) => api.delete(PATIENT_PATH + "/" + id + "?" + license)

export default {
    getFavoriteDoctors,
    deleteFavoriteDoctor
}