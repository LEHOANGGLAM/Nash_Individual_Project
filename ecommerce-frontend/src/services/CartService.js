import axios from "axios";

const API_BASE_URL = `${process.env.REACT_APP_API_URL}/carts`;


class CateService {
    getCartItemsByUserId(id){
        return axios.get(`${API_BASE_URL }/user/${id}`);
    }
}

export default new CateService();