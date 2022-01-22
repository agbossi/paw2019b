import api from "./index";
const LOCATIONS_PATH = '/locations';

const getLocations = async () => api.get(LOCATIONS_PATH);

export default {
    getLocations
}