import axios from "axios";
import authHeader from './AuthHeader';


const API_BASE_URL = `${process.env.REACT_APP_API_URL}/products/search`;


class ProductService {
    getAllProducts(query){
        return axios.get(`${API_BASE_URL}?${query}`);
    }
}

export default new ProductService();