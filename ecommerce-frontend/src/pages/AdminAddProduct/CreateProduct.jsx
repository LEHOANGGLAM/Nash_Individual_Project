import React from 'react';
import './Create.scss'
import DriveFolderUploadOutlinedIcon from "@mui/icons-material/DriveFolderUploadOutlined";
import { useState } from "react";
import TextEditor from '../../components/TextEditor/TextEditor';
import MultipleSelectChip from '../../components/MultipleSelectChip/MultipleSelectChip';
import { useEffect } from 'react';
import CateService from '../../services/CateService';
import SizeService from '../../services/SizeService';
import ProductService from '../../services/ProductService';
import { useForm } from 'react-hook-form'
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import axios from "axios";
import LoadingIcon from '../../components/Loading';
import ModalConfirm from '../../components/ModalConfirm';
import { useParams } from 'react-router-dom';
import parse from 'html-react-parser'

const schema = yup.object().shape({
    title: yup.string().required(),
    quantity: yup.number().positive().integer().required("Quantity of product must postive"),
    price: yup.number().positive().integer().required("Price of product must postive"),
});

function CreateProduct(props) {
    const { register, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(schema)
    });

    const [sizes, setSizes] = useState([]);
    const [categories, setCategories] = useState([]);

    const [imageSelect, setImageSelect] = useState([]);
    const [selectSizes, setSelectSizes] = useState([]);
    const [selectCate, setCateSelect] = useState("1");
    const [text, setText] = useState("");
    const [err, setErr] = useState();

    //handleModal
    const [showModal, setShowModal] = useState(false);
    const handleShowModal = () => { setShowModal(true); }
    const handleCloseModal = () => { setShowModal(false); }

    //handle loading
    const [isLoading, setLoading] = useState(true);

    //handleUpdateProductIfExist
    const { id } = useParams();
    const [pro, setPro] = useState([]);
    const [images, setImages] = useState([]);

    useEffect(() => {
        fetchCategories();
        fetchSizes();

        if (id) {
            ProductService.getProductById(id).then(res => {
                setPro(res.data)
                setCateSelect(res.data?.categoryCollection[0]?.id)
                setText(res.data.desciption)
                setSelectSizes(res.data.sizeCollection)

                const temp = []
                res.data?.imageCollection.forEach(i => {
                    temp.push(i.link)
                })
                setImages(temp)
            }, err => {
                console.log(err);
            })

        }

        setLoading(false);
    }, [])


    const handleImageChange = (e) => {
        setImageSelect([]);
        const files = Array.from(e.target.files);
        console.log(files);
        files.forEach(file => {
            if (file.size === 0 || file.type.split("/")[0] !== "image" || file.size > 1048576)
                setErr("File invalid")
            setErr();
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onloadend = () => {
                setImageSelect(oldArray => [...oldArray, reader.result])
            }
        })
    }
    const handleCateChange = (e) => {
        setCateSelect(e.target.value)
    }
    const handleSizeChange = (newSizes) => {
        setSelectSizes(newSizes)
    }
    const handleTextEditorChange = (newText) => {
        setText(newText)
    }



    const fetchCategories = async () => {
        CateService.getAllCates().then((res) => {
            setCategories(res.data)
        })
    }

    const fetchSizes = async () => {
        SizeService.getAllSizes().then((res) => {
            setSizes(res.data)
        })
    }

    const addProduct = async (data) => {
        if (selectSizes.length > 0 && text.length > 1 && imageSelect != null) {
            setLoading(true)

            if (!imageSelect) throw Error("No file is chosen");

            const formData = new FormData();
            let imageLink = []
            for (let i = 0; i < imageSelect.length; i++) {
                formData.append("file", imageSelect[i]);
                formData.append("upload_preset", "oqlxv53w");
                let result = await axios.post("https://api.cloudinary.com/v1_1/dmstiyczr/image/upload", formData);
                imageLink.push(String(result?.data?.url))
            }

            // console.log(imageLink);
            const newPro = {
                price: Number(data.price),
                quantity: Number(data.quantity),
                title: String(data.title),
                desciption: String(text),
                categoryIds: [
                    selectCate
                ],
                sizeIds: selectSizes,
                images: imageLink
            }
            //console.log(newPro);
            if (id) {
                newPro.id = id;
                imageLink = imageLink.concat(images)
                newPro.images = imageLink;
                //  console.log(imageLink);
                ProductService.updateProduct(id, newPro).then((res) => {
                    console.log("update thanh cong");

                    setLoading(false)
                    if (isLoading == false)
                        handleShowModal();
                }, (err) => {
                    setLoading(false)
                    console.log("update fail");
                })
            } else {
                ProductService.addProduct(newPro).then((res) => {
                    console.log("thanhcong");

                    setLoading(false)
                    if (isLoading == false)
                        handleShowModal();
                }, (err) => {
                    setLoading(false)
                    console.log("fail");
                })
            }
        }
        else {
            alert("Please enter all fields");
        }
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
                            {isLoading ? (<LoadingIcon />) :
                                <div className='bottom'>
                                    {/* onSubmit={addProduct} */}
                                    <form onSubmit={handleSubmit(addProduct)}>
                                        <div className="formInput">

                                            <label htmlFor="file">
                                                Image: <DriveFolderUploadOutlinedIcon className="icon" style={{ cursor: 'pointer' }} />

                                            </label>
                                            <input type='file' id='file' name="images" onChange={handleImageChange} multiple />
                                            {images.map((item, index) =>
                                                <div key={index} >
                                                    <div style={{ display: 'flex', wordBreak: 'break-word' }}><a href={item} target='blank' style={{ color: 'black' }}>{item}</a>
                                                        <p class="product-remove" onClick={() => setImages(images.filter(i => i !== item))} style={{ marginLeft: 20, width: 1 }}><a >
                                                            <span class="ion-ios-close"></span></a></p>
                                                    </div>
                                                </div>
                                            )}

                                            {err && (
                                                <span role="alert" style={{ color: 'red' }}>
                                                    {err}
                                                </span>
                                            )}
                                        </div>
                                        <div className='formInput'>
                                            <label>Name</label>
                                            <input type='text' name="title"  {...register('title', { required: true })} defaultValue={pro ? pro.title : ""} />
                                            {errors.title && (
                                                <span role="alert" style={{ color: 'red' }}>
                                                    This field is required
                                                </span>
                                            )}
                                        </div>
                                        <div className='formInput'>
                                            <label>Price</label>
                                            <input type='number' name="price" {...register('price', { required: true })} defaultValue={pro ? pro.price : ""} />
                                            {errors.price && (
                                                <span role="alert" style={{ color: 'red' }}>
                                                    {errors.price.message}
                                                </span>
                                            )}
                                        </div>
                                        <div className='formInput'>
                                            <label>Stock</label>
                                            <input type='number' name="quantity" {...register('quantity', { required: true })} defaultValue={pro ? pro.quantity : ""} />
                                            {errors.quantity && (
                                                <span role="alert" style={{ color: 'red' }}>
                                                    {errors.quantity.message}
                                                </span>
                                            )}
                                        </div>
                                        <div className='formInput'>
                                            <label>Size</label>
                                            <div>
                                                <MultipleSelectChip data={sizes} handleDataChange={handleSizeChange} />
                                            </div>
                                        </div>
                                        <div className='formInput'>
                                            <label>Category</label>
                                            <div>
                                                <select class="form-control" style={{ width: 150, marginTop: 20, borderRadius: 10 }}
                                                    value={selectCate} onChange={(e) => handleCateChange(e)}>
                                                    {categories?.map(cate =>
                                                        <option value={cate.id} key={cate.id}>{cate.title}</option>
                                                    )}
                                                </select>
                                            </div>
                                        </div>
                                        <div className='oneline' >
                                            <label className='lab'>Description</label>
                                            <div >
                                                <TextEditor text={text} setText={handleTextEditorChange} />

                                            </div>
                                        </div>
                                        <input type="submit" class="btn btn-primary py-3 px-4 col-md-12" name="submit" value="Submit" />
                                    </form>
                                </div>
                            }

                        </div>
                    </div>
                </div>
            </section >
            <ModalConfirm title={'Add Product success'} handleCloseModal={handleCloseModal} showModal={showModal} link="/admin-products" />
        </>
    );
}

export default CreateProduct;