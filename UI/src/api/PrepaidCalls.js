import api from "./index";
import * as cons from './Constants.js'

const getAllPrepaids = async () => api.get(cons.PREPAIDS_PATH + cons.ALL_PATH)

export default {
    getAllPrepaids
}

