import React from 'react';
import AuthService from '../../services/AuthService';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';

function Login(props) {
    const navigate = useNavigate();
    const [fields, setFields] = useState({});
    const [message, setMessage] = useState('');

    const handleChange = (e) => {
        setFields({
            ...fields,
            [e.target.name]: e.target.value === '' ? undefined : e.target.value,
        });
    }

    const handleLogin = (e) => {
        e.preventDefault();
        console.log(fields["username"], fields["password"]);
        setMessage("")

        AuthService.login(fields["username"], fields["password"]).then(
            () => {
                navigate('/');
                window.location.reload();
            },
            error => {
                const resMessage =
                    (error.response &&
                        error.response.data &&
                        error.response.data.message) ||
                    error.message ||
                    error.toString();

                setMessage(resMessage);
            }
        );
    }

    return (
        <>
            <form action="#" class="" onSubmit={handleLogin}>
                <div class="row align-items-end">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label for="firstname">Username: </label>
                            <input type="text" class="form-control" name="username"
                                onChange={(e) => handleChange(e)} />
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="form-group">
                            <label for="lastname">Password</label>
                            <input type="password" class="form-control"  name="password"
                                onChange={(e) => handleChange(e)} />
                        </div>
                    </div>
                </div>
                <p class="form-row">
                    <input type="submit" class="btn btn-primary py-3 px-4 col-md-12" name="login" value="Login" />

                </p>
                {message && (
                    <span style={{ color: "red", justifyItems: 'center', display: 'grid', fontSize: 15 }}>{message}</span>
                )}
                {/* <label for="rememberme" class="rememberme col-md-12" style={{ margin: 10 }}>
                            <input name="rememberme" type="checkbox" id="rememberme" value="forever" /> Remember Me</label> */}
                <p class="lost_password" style={{ marginLeft: 10 }}>
                    <a href="#">Lost Your Password?</a>
                </p>
            </form>
        </>
    );

}

export default Login;