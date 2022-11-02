import axios from "axios";
import authHeader from './AuthHeader';

const API_BASE_URL = `${process.env.REACT_APP_API_URL}`;


class UserService {
    getUserById(id) {
        return axios.get(API_BASE_URL + '/users/' + id);
    }
    getAllUsers() {
        return axios.get(API_BASE_URL + '/users', { headers: authHeader() });
    }
    deleteUser(id) {
        return axios.delete(API_BASE_URL + '/users/'+ id, { headers: authHeader() });
    }





    //test
    getUserBoard() {
        return axios.get(API_BASE_URL + '/user', { headers: authHeader() });
    }

    getAdminBoard() {
        return axios.get(API_BASE_URL + '/admin', { headers: authHeader() });
    }
}

export default new UserService();