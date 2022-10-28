import axios from "axios";

const API_BASE_URL = `${process.env.REACT_APP_API_URL}`;


class ProductService {
    getProductsByPredicates(query){
        return axios.get(`${API_BASE_URL}/products/search?${query}`);
    }
    getProductById(id){
        return axios.get(`${API_BASE_URL}/product/${id}`);
    }
}

export default new ProductService();