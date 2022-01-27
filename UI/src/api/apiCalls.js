import api from "./index";

const PAGE_QUERY = 'page=';
const UNAVAILABLE_QUERY = 'includeUnavailables='

const LOGIN_PATH = '/login';
const LOCATIONS_PATH = '/locations'
const SPECIALTIES_PATH = '/specialties';
const DOCTORS_PATH = '/doctors';
const CLINICS_PATH = 'clinics';
const ALL_PATH = '/all';

const getLocations = async (pag) => api.get(LOCATIONS_PATH + "?" + PAGE_QUERY + pag);
const getAllLocations = async () => api.get(LOCATIONS_PATH + ALL_PATH)
const addLocation = async (data) => api.post(
    LOCATIONS_PATH,
    data,
    {headers: {'X-AUTH-TOKEN': localStorage.getItem('token')}}
);

const getSpecialties = async (pag) => api.get(SPECIALTIES_PATH + "?" + PAGE_QUERY + pag);
const getAllSpecialties = async () => api.get(SPECIALTIES_PATH + ALL_PATH)

const getClinics = async (pag) => api.get(CLINICS_PATH+ "?" + PAGE_QUERY + pag);

const getDoctorsAdmin = async (pag) => api.get(
    DOCTORS_PATH + ALL_PATH + "?" + PAGE_QUERY + pag);
const addDoctor = async (data) => api.post(
    DOCTORS_PATH,
    data,
    {headers: {'X-AUTH-TOKEN': localStorage.getItem('token')}}
    );
const deleteDoctor = async (license) => api.delete(
    DOCTORS_PATH + '/' + license,
    {},
    {headers: {'X-AUTH-TOKEN': localStorage.getItem('token')}})

const login = async (email, password) => {
    const params = new URLSearchParams();
    params.append('email', email);
    params.append('password', password);
    return api.post(LOGIN_PATH, params)
        .then(resp => {
            if(resp.status === 200) {
                console.log(resp.headers)
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
    getAllLocations,
    addLocation,
    getSpecialties,
    getAllSpecialties,
    getClinics,
    getDoctorsAdmin,
    deleteDoctor,
    addDoctor
}