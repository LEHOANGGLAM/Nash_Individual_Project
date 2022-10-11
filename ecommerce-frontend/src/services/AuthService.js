import axios from "axios";

const API_BASE_URL = `${process.env.REACT_APP_API_URL}/auth`;

class AuthService {
    login(username, password) {
        return axios.post(API_BASE_URL + "/login", {
            username, password
        }).then(res => {
            if (res.data.accessToken) {
                localStorage.setItem("user", JSON.stringify(res.data));
            }
            return res.data;
        })
    }

    getCurrentUser() {
        return JSON.parse(localStorage.getItem('user'));
    }

    logout() {
        localStorage.clear();
    }

    register(username, email, password, role) {
        return axios.post(API_BASE_URL + "/signup", {
            username, email, password, role
        })
    }
}

export default new AuthService();