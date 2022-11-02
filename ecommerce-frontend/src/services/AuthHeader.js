import Cookies from 'universal-cookie';
const cookies = new Cookies();

export default function authHeader(){
    const user = cookies.get('user');
   // console.log(user.accessToken);
    if(user && user.accessToken){
        return{ Authorization: 'Bearer ' + user.accessToken};
    }else{
        return {};
    }
}
