import axios from "axios";
import authHeader from './AuthHeader';

const API_BASE_URL = `${process.env.REACT_APP_API_URL}/products`;


class ProductService {
    getProductsByPredicates(query) {
        return axios.get(`${API_BASE_URL}/search?${query}`);
    }

    getAllProducts() {
        return axios.get(`${API_BASE_URL}`);
    }

    getProductById(id) {
        return axios.get(`${API_BASE_URL}/${id}`);
    }

    addProduct(product) {
        return axios.post(`${API_BASE_URL}`, product, { headers: authHeader() });
    }
    updateProduct(id, product) {
        return axios.put(`${API_BASE_URL}/${id}`, product, { headers: authHeader() });
    }
    deleteProduct(id) {
        return axios.delete(`${API_BASE_URL}/${id}`, { headers: authHeader() });
    }
}

export default new ProductService();