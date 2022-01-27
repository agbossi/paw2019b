import api from "./index";

const PAGE_QUERY = 'page=';

const LOGIN_PATH = '/login';
const LOCATIONS_PATH = '/locations'
const SPECIALTIES_PATH = '/specialties';
const DOCTORS_PATH = '/doctors';
const ALL_PATH = '/all';

const getLocations = async (pag) => api.get(LOCATIONS_PATH + "?" + PAGE_QUERY + pag);
const addLocation = async (data) => api.post(
    LOCATIONS_PATH,
    data,
    {headers: {'X-AUTH-TOKEN': localStorage.getItem('token')}}
);

const getSpecialties = async (pag) => api.get(SPECIALTIES_PATH + "?" + PAGE_QUERY + pag);
const getAllSpecialties = async () => api.get(SPECIALTIES_PATH + ALL_PATH)

const getDoctors = async (pag) => api.get(DOCTORS_PATH + "?" + PAGE_QUERY + pag);
const addDoctor = async (data) => api.post(
    DOCTORS_PATH,
    data,
    {headers: {'X-AUTH-TOKEN': localStorage.getItem('token')}}
    );

const login = async (email, password) => {
    const params = new URLSearchParams();
    params.append('email', email);
    params.append('password', password);
    return api.post(LOGIN_PATH, params)
        .then(resp => {
            if(resp.status === 200) {
                localStorage.setItem('token', resp.headers.xAuthToken)
                localStorage.setItem('role', resp.headers.xRole)
            }

            return resp
        })
}

const logout = async () => {
    localStorage.removeItem('token');
    localStorage.removeItem('role')
}

export default {
    login,
    logout,
    getLocations,
    addLocation,
    getSpecialties,
    getAllSpecialties,
    getDoctors,
    addDoctor
}