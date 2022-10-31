import axios from "axios";

const API_BASE_URL = `${process.env.REACT_APP_API_URL}/sizes`;

class CateService {
    getAllSizes(){
        return axios.get(API_BASE_URL);
    }
}

export default new CateService();