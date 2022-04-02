import api from "./index";
import * as cons from './Constants.js'

const getAllPrepaids = async () => api.get(cons.PREPAIDS_PATH + "?" + cons.MODE + "all")
const getPrepaids = async (pag) => api.get(cons.PREPAIDS_PATH + "?" + cons.PAGE_QUERY + pag)
const deletePrepaid = async (name) => api.delete(cons.PREPAIDS_PATH + "/" + name, {})
const addPrepaid = async (data) => api.post(cons.PREPAIDS_PATH, data);

export default {
    getAllPrepaids,
    getPrepaids,
    deletePrepaid,
    addPrepaid
}

