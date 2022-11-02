import axios from "axios";
import authHeader from './AuthHeader';

const API_BASE_URL = `${process.env.REACT_APP_API_URL}/categories`;


class CateService {
    getAllCates() {
        return axios.get(API_BASE_URL);
    }

    addCate(cate) {
        return axios.post(API_BASE_URL, cate, { headers: authHeader() });
    }
    deleteCate(id) {
        return axios.delete(API_BASE_URL + '/' + id, { headers: authHeader() });
    }
}

export default new CateService();