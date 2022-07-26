import api from "./index";
import {FAVORITE_PATH, LICENSE_QUERY, PATIENT_PATH} from "./Constants.js";
import * as cons from "./Constants";

const getFavoriteDoctors = async (id, pag) => api.get(PATIENT_PATH + "/" + id + FAVORITE_PATH + "?" + cons.PAGE_QUERY + pag,
    {})
const deleteFavoriteDoctor = async (id, license) => api.delete(
    PATIENT_PATH + "/" + id + FAVORITE_PATH + "?" + LICENSE_QUERY + license, {})
const isFavorite = async (id, license) => api.get(PATIENT_PATH + "/" + id + FAVORITE_PATH + "?" + cons.MODE + "isFav" + "&" + cons.LICENSE_QUERY + license,
    {})
const addFavoriteDoctor = async (id, data) => api.post(
    PATIENT_PATH + "/" + id + FAVORITE_PATH, data)

const getProfile = async (id) => api.get(PATIENT_PATH + "/" + id,{})

const updateProfile = async (data, id) => api.put(PATIENT_PATH + "/" + id, data)

export default {
    getFavoriteDoctors,
    deleteFavoriteDoctor,
    isFavorite,
    addFavoriteDoctor,
    updateProfile,
    getProfile
}