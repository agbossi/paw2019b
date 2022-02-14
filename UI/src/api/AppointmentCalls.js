import api from "./index";
import * as cons from './Constants.js'

const getAvailableAppointments = async (license) => api.get(
    cons.APPOINTMENT_PATH + cons.AVAILABLE_PATH + '/' + license);

const makeAppointment = async (data) => api.post(
    cons.APPOINTMENT_PATH,
    data,
    {headers: {'X-AUTH-TOKEN': localStorage.getItem('token')}}
    )

export default {
    getAvailableAppointments,
    makeAppointment
}