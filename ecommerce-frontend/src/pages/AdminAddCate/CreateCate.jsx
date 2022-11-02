import React from 'react';
import { useParams } from 'react-router-dom';
import { useState, useEffect } from "react";
import { useForm } from 'react-hook-form'
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import CateService from '../../services/CateService';

const schema = yup.object().shape({
    title: yup.string().required(),
    content: yup.string().required(),
});

function CreateCate(props) {
    const { register, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(schema)
    });
 

    const handleAddCate = (data) => {
        let cate = {
            title: data.title,
            content: data.content
        }
        CateService.addCate(cate).then(res => {
            console.log("thanh cong");
        }, err => {
            console.log("fail");
        })
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
                                <h2 style={{ color: 'lightgray', fontSize: 30 }}>Add Category</h2>
                            </div>
                            <div className='bottom'>
                                <form onSubmit={handleSubmit(handleAddCate)}>

                                    <div className='formInput' style={{ width: 400 }}>
                                        <label>Name</label>
                                        <input type='text' name="title"  {...register('title', { required: true })} />
                                        {errors.title && (
                                            <span role="alert" style={{ color: 'red' }}>
                                                This field is required
                                            </span>
                                        )}
                                    </div>
                                    <div className='formInput' style={{ width: 400 }}>
                                        <label>Content</label>
                                        <input type='text' name="content"  {...register('content', { required: true })} />
                                        {errors.content && (
                                            <span role="alert" style={{ color: 'red' }}>
                                                This field is required
                                            </span>
                                        )}
                                    </div>
                                 
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

export default CreateCate;