import React from 'react';
import './Create.scss'
import DriveFolderUploadOutlinedIcon from "@mui/icons-material/DriveFolderUploadOutlined";
import { useState } from "react";
import TextEditor from '../../components/TextEditor/TextEditor';
import MultipleSelectChip from '../../components/MultipleSelectChip/MultipleSelectChip';
import { useEffect } from 'react';
import CateService from '../../services/CateService';


function CreateProduct(props) {
    const [sizes, setSizes] = useState([]);
    const [categories, setCategories] = useState([]);
    const [file, setFile] = useState('');
    const [text, setText] = useState('');

    const handleCateChange = (newCate) => {
        setCategories(newCate)
    }
    const handleTextEditorChange = (newText) => {
        setText(newText)
    }
    console.log(text);
    console.log(file);

    useEffect(() => {
        fetchCategories();
    }, [])

    const fetchCategories = async () => {
        CateService.getAllCates().then((res) => {
            setCategories(res.data)
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
                                <h2 style={{ color: 'lightgray', fontSize: 30 }}>Add Product</h2>
                            </div>
                            <div className='bottom'>

                                <form>
                                    <div className="formInput">
                                        <img src={file ? URL.createObjectURL(file) : 'https://icon-library.com/images/no-image-icon/no-image-icon-0.jpg'} />
                                        <label htmlFor="file">
                                            Image: <DriveFolderUploadOutlinedIcon className="icon" style={{ cursor: 'pointer' }} />
                                        </label>
                                        <input type='file' id='file' onChange={(e) => setFile(e.target.files[0])} style={{ display: 'none', }} />
                                    </div>
                                    <div className='formInput'>
                                        <label>Name</label>
                                        <input type='text' />
                                    </div>
                                    <div className='formInput'>
                                        <label>Price</label>
                                        <input type='number' />
                                    </div>
                                    <div className='formInput'>
                                        <label>Stock</label>
                                        <input type='text' />
                                    </div>
                                    <div className='formInput'>
                                        <label>Size</label>
                                        <div>
                                            <MultipleSelectChip />
                                        </div>
                                    </div>
                                    <div className='formInput'>
                                        <label>Category</label>
                                        <div >
                                            <MultipleSelectChip data={categories} handleDataChange={handleCateChange}/>
                                        </div>
                                    </div>
                                    <div className='oneline'>
                                        <label>Description</label>
                                        <div >
                                            <TextEditor text={text} setText={handleTextEditorChange} />
                                        </div>
                                    </div>
                                    <button className='button'>Submit</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

        </>
    );
}

export default CreateProduct;