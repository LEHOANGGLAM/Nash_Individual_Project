import React from 'react';
import { useParams } from 'react-router-dom';
import { useState, useEffect } from "react";
import { useForm } from 'react-hook-form'
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import CateService from '../../services/CateService';
import ModalConfirm from '../../components/ModalConfirm';

const schema = yup.object().shape({
    title: yup.string().required(),
    content: yup.string().required(),
});

function CreateCate(props) {

    const { register, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(schema)
    });


    const { id } = useParams();
    const [cate, setCate] = useState([]);

    //handleModal
    const [showModal, setShowModal] = useState(false);
    const handleShowModal = () => { setShowModal(true); }
    const handleCloseModal = () => { setShowModal(false); }

    useEffect(() => {
        if (id) {
            CateService.getCateById(id).then(res => {
                setCate(res.data)
            }, err => {
                console.log(err);
            })
        }
    }, [])

    const handleAddCate = (data) => {
        let cate = {
            title: data.title,
            content: data.content
        }
        CateService.addCate(cate).then(res => {
            console.log("thanh cong");
            handleShowModal()
        }, err => {
            console.log("fail");
        })

   
    }

    const handleUpdateCate = (data) => {
        let newCate = {
            id: id,
            title: data.title,
            content: data.content
        }
        CateService.updateCate(id, newCate).then(res => {
            console.log("update thanh cong");
            handleShowModal()
        }, err => {
            console.log("update fail");
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
                                <form onSubmit={id ? handleSubmit(handleUpdateCate) : handleSubmit(handleAddCate)}>

                                    <div className='formInput' style={{ width: 400 }}>
                                        <label>Name</label>
                                        <input type='text' name="title"  {...register('title', { required: true })} defaultValue={cate ? cate.title : ""} />
                                        {errors.title && (
                                            <span role="alert" style={{ color: 'red' }}>
                                                This field is required
                                            </span>
                                        )}
                                    </div>
                                    <div className='formInput' style={{ width: 400 }}>
                                        <label>Content</label>
                                        <input type='text' name="content"  {...register('content', { required: true })} defaultValue={cate ? cate.content : ""} />
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
            <ModalConfirm title={'Add Category success'} handleCloseModal={handleCloseModal} showModal={showModal} link="/admin-categories" />
        </>
    );
}

export default CreateCate;