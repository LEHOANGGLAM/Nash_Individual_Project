import axios from "axios";
import authHeader from './AuthHeader';

const API_BASE_URL = `${process.env.REACT_APP_API_URL}/carts`;


class CateService {
    getCartItemsByUserId(id) {
        //console.log(`/user/${id}`);
        return axios.get(`${API_BASE_URL}/user/${id}`);

    }


    addItemIntoCart(item) {
        return axios.put(`${API_BASE_URL}`, item, { headers: authHeader() });
    }

    updateCartItem(cartItem) {
        return axios.put(`${API_BASE_URL}`, cartItem, { headers: authHeader() });
    }

    deleteCartItem(id) {
        return axios.delete(`${API_BASE_URL}/${id}`);
    }
}

export default new CateService();