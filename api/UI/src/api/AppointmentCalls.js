import api from "./index";
import * as cons from './Constants.js'

const makeAppointment = async (data) => api.post(cons.APPOINTMENT_PATH, data)

const getAppointment = async (email, page) => api.get(
    cons.APPOINTMENT_PATH + '/' + email + "?" + cons.PAGE_QUERY + page,
    {}
)

const deleteAppointment = async (email, license, clinic, year, month, day, time) => api.delete(
    cons.APPOINTMENT_PATH + '/' + email + '?' + deleteQueryParams(license, clinic, year, month, day, time),
    {})

const deleteQueryParams = (license, clinic, year, month, day, time) => {
    return cons.LICENSE_QUERY + license + '&' + cons.CLINIC_QUERY + clinic + '&' + cons.YEAR_QUERY + year + '&' +
        cons.MONTH_QUERY + month + '&' + cons.DAY_QUERY + day + '&' + cons.TIME_QUERY + time;
}
export default {
    makeAppointment,
    getAppointment,
    deleteAppointment
}