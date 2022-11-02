import axios from "axios";
import authHeader from './AuthHeader';

const API_BASE_URL = `${process.env.REACT_APP_API_URL}`;


class ProductService {
    getProductsByPredicates(query) {
        return axios.get(`${API_BASE_URL}/products/search?${query}`);
    }

    getAllProducts() {
        return axios.get(`${API_BASE_URL}/products`);
    }

    getProductById(id) {
        return axios.get(`${API_BASE_URL}/products/${id}`);
    }

    addProduct(product) {
        return axios.post(`${API_BASE_URL}/products`, product, { headers: authHeader() });
    }
}

export default new ProductService();