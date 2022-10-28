import axios from "axios";
import Cookies from 'universal-cookie';

const API_BASE_URL = `${process.env.REACT_APP_API_URL}/auth`;
const cookies = new Cookies();

class AuthService {
    login(username, password) {
        return axios.post(API_BASE_URL + "/login", {
            username, password
        }).then(res => {
            if (res.data.accessToken) {
                cookies.set("user", JSON.stringify(res.data));
                //localStorage.setItem("user", JSON.stringify(res.data));
            }
            return res.data;
        })
    }

    getCurrentUser() {
        return cookies.get('user');
        //return  JSON.parse(localStorage.getItem('user'));
    }

    logout() {
        cookies.remove('user')
    }

    register(username, email, mobile, password, role) {
        return axios.post(API_BASE_URL + "/signup", {
            username, email, mobile, password, role
        })
    }
}

export default new AuthService();