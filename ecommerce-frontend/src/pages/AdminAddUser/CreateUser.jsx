import React from 'react';
import { useParams } from 'react-router-dom';
import '../AdminAddProduct/Create.scss'
import { useState, useEffect } from "react";
import { useForm } from 'react-hook-form'
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import AuthService from '../../services/AuthService';

const schema = yup.object().shape({
    username: yup.string().required(),
    mobile: yup.string().required(),
    password: yup.string().required(),
    repeatPass: yup.string().oneOf([yup.ref("password")], "Password doesnt match").required(),
});

function CreateUser(props) {
    const { register, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(schema)
    });
    const { id } = useParams();
    const [err, setErr] = useState();
    const [successful, setSuccess] = useState();

    const handleAddUser = (data) => {
        AuthService.register(
            data.username,
            data.email,
            data.mobile,
            data.password,
            ["admin"],
        ).then(
            response => {
                setSuccess(true)
                setErr(response.data.message);
            },
            error => {
                const resMessage =
                    (error.response &&
                        error.response.data &&
                        error.response.data.message) ||
                    error.message ||
                    error.toString();

                setErr(resMessage);
                setSuccess(false);
            }
        );
    }
    return (
        <>
            <div class="hero-wrap hero-bread" style={{ padding: 20 }}>
            </div>

            <section class="ftco-section ftco-cart">
                <div className='container'>
                    <div className='new'>
                        <div class="newContainer">
                            <div className='top'>
                                <h2 style={{ color: 'lightgray', fontSize: 30 }}>Add user admin</h2>
                            </div>
                            <div className='bottom'>
                                {/* onSubmit={addProduct} */}
                                <form onSubmit={handleSubmit(handleAddUser)}>

                                    <div className='formInput'>
                                        <label>Username</label>
                                        <input type='text' name="username"  {...register('username', { required: true })}  />
                                        {errors.username && (
                                            <span role="alert" style={{ color: 'red' }}>
                                                This field is required
                                            </span>
                                        )}
                                    </div>
                                    <div className='formInput'>
                                        <label>Email</label>
                                        <input type='email' name="email"  {...register('email', { required: true })} />
                                        {errors.email && (
                                            <span role="alert" style={{ color: 'red' }}>
                                                This field is required
                                            </span>
                                        )}
                                    </div>
                                    <div className='formInput'>
                                        <label>Mobile</label>
                                        <input type='text' name="mobile"  {...register('mobile', { required: true })} />
                                        {errors.mobile && (
                                            <span role="alert" style={{ color: 'red' }}>
                                                This field is required
                                            </span>
                                        )}
                                    </div>
                                    <div className='formInput'>
                                        <label>Password</label>
                                        <input type='password' name="password" {...register('password', { required: true })} />
                                        {errors.password && (
                                            <span role="alert" style={{ color: 'red' }}>
                                                This field is required
                                            </span>
                                        )}
                                    </div>
                                    <div className='formInput'>
                                    </div>
                                    <div className='formInput'>
                                        <label>Repeat Password</label>
                                        <input type='password' name="repeatPass" {...register('repeatPass', { required: true })} />
                                        {errors.repeatPass && (
                                            <span role="alert" style={{ color: 'red' }}>
                                                {errors.repeatPass.message}
                                            </span>
                                        )}
                                    </div>
                                    {err && (
                                        <span style={successful ? { color: "green", marginLeft: 350, fontSize: 15 }
                                            : { color: "red", marginLeft: 350, fontSize: 15 }}
                                            role="alert"
                                        >
                                            {err}
                                        </span>
                                    )}
                                    <input type="submit" class="btn btn-primary py-3 px-4 col-md-12" name="submit" value="Submit" />
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

        </>
    );
}

export default CreateUser;