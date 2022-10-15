import axios from "axios";
import authHeader from './AuthHeader';

const API_BASE_URL = `${process.env.REACT_APP_API_URL}/products`;


class ProductService {
    getAllProducts(){
        return axios.get(API_BASE_URL);
    }
}

export default new ProductService();