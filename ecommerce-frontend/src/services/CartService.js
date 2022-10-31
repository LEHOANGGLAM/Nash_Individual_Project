import axios from "axios";
import authHeader from './AuthHeader';

const API_BASE_URL = `${process.env.REACT_APP_API_URL}`;


class CateService {
    getCartItemsByUserId(id) {
        return axios.get(`${API_BASE_URL}/user/${id}/carts`);
    }

    addItemIntoCart(item, userId) {
        return axios.post(`${API_BASE_URL}/user/${userId}/carts`, item, { headers: authHeader() });
    }

    updateCartItem(id, item) {
        return axios.put(`${API_BASE_URL}/carts/${id}`, item, { headers: authHeader() });
    }

    deleteCartItem(id) {
        return axios.delete(`${API_BASE_URL}/carts/${id}`);
    }

    countItemsInCartByUserId(id) {
        return axios.get(`${API_BASE_URL}/user/${id}/carts/count`);
    }
}

export default new CateService();