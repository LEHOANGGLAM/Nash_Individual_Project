import axios from "axios";
import authHeader from './AuthHeader';

const API_BASE_URL = `${process.env.REACT_APP_API_URL}`;

class OrderService {
 
    createOrder(order, userId) {
        return axios.post(`${API_BASE_URL}/user/${userId}/orders`, order, { headers: authHeader() });
    }


    //orderItem
    getOrderItemsNoRating(userId) {
        return axios.get(`${API_BASE_URL}/user/${userId}/orderitems`, { headers: authHeader() });
    }
}

export default new OrderService();