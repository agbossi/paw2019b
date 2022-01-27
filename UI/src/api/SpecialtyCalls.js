import api from "./index";
import * as cons from './Constants.js'

const getSpecialties = async (pag) => api.get(cons.SPECIALTIES_PATH + "?" + cons.PAGE_QUERY + pag);
const getAllSpecialties = async () => api.get(cons.SPECIALTIES_PATH + cons.ALL_PATH)

export default {
    getSpecialties,
    getAllSpecialties
}
