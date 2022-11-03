import axios from "axios";


const API_BASE_URL = `${process.env.REACT_APP_API_URL}`;


class RatingService {
    getRatingsByProductId(id){
        return axios.get(`${API_BASE_URL}/product/${id}/ratings`);
    }

    addRating(rating){
        return axios.post(`${API_BASE_URL}/ratings`, rating);
    }
}

export default new RatingService();