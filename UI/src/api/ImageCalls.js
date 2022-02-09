import api from "./index";
import * as cons from './Constants.js'

const uploadImage = async (license, img) => api.post(cons.DOCTORS_PATH + "/" + license + cons.IMAGE_PATH,
    img,
    {headers: {
            'X-AUTH-TOKEN': localStorage.getItem('token'),
            'Content-Type': 'multipart/form-data'
    }}
);
const getImage = async (license) => api.get(
    cons.DOCTORS_PATH + "/" + license + cons.IMAGE_PATH,
    {},
    {headers: {'Accept': '*'}}
    )

export default {
    uploadImage,
    getImage
}