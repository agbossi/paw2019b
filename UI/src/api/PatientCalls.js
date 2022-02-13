import api from "./index";
import {PATIENT_PATH} from "./Constants.js";

const getFavoriteDoctors = async (id) => api.get(PATIENT_PATH + "/" + id)

export default {
    getFavoriteDoctors
}