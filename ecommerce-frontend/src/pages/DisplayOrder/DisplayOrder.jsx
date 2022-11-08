import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import HeadWrap from '../../components/HeadWrap/HeadWrap';
import AuthService from '../../services/AuthService';
import OrderService from '../../services/OrderService';
import { Link } from 'react-router-dom';
import ModaRating from '../../components/ModalRating';
import ModalConfirm from '../../components/ModalConfirm';

function DisplayOrder(props) {
    const navigate = useNavigate();
    const [items, setItems] = useState([]);

    const [itemSelect, setItemSelect] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const [isComment, setIsComment] = useState(false);

    useEffect(() => {
        fetchOrder();
    }, [isComment])

    const fetchOrder = async () => {
        const user = AuthService.getCurrentUser();
        if (user) {
            OrderService.getOrderItemsNoRating(user.id).then((res) => {
                setItems(res.data);
            })
        }
        else {
            navigate("/login");
        }
    }


    const handleCloseModal = () => {
        setShowModal(false);
    }
    const handleOpenModal = (i) => {
        setItemSelect(i)
        setShowModal(true);
    }

    const handleCommented = () => {
        setIsComment(!isComment);
    }
    return (
        <>
            <HeadWrap />
            <section class="ftco-section ftco-cart">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12 ftco-animate fadeInUp ftco-animated">
                            <div class="cart-list">
                                <table class="table">
                                    <thead class="thead-primary">
                                        <tr class="text-center">
                                            {/* <th>&nbsp;</th> */}

                                            <th>NO</th>
                                            <th>Product</th>
                                            <th>Quantity</th>
                                            <th>Rating</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {items.map((item, index) =>
                                            <>
                                                <tr class="text-center" key={index} >
                                                    <td class="price" style={{ backgroundColor: '#FFFFFF' }}>{index + 1}</td>

                                                    <Link to={`/product-${item.productId.id}`} >
                                                        <td class="image-prod"><div class="img" style={{ backgroundImage: `url(${item.productId.imageCollection[0]?.link})` }}></div></td>
                                                        <td class="product-name">
                                                            <h3>{item.productId.title}</h3>
                                                            <div>Size: {item.sizeId.sizeName} &ensp;
                                                            </div>
                                                        </td>
                                                    </Link>


                                                    <td class="quantity">
                                                        <div class="input-group mb-3">

                                                            <input type="number" name="quantity" class="quantity form-control input-number"
                                                                value={item.quantity} min="1" max="100" disabled />

                                                        </div>
                                                    </td>

                                                    <td class="quantity">
                                                        {item.rating == null ?
                                                            < button class="btn btn-primary py-3 px-4" style={{ color: 'white', fontWeight: 'bold' }} onClick={() => handleOpenModal(item)}>Rating</button>
                                                            : <>
                                                                <Link to={`/product-${item.productId.id}`} >
                                                                    <button style={{ backgroundColor: 'lightblue', borderRadius: 50, fontWeight: 'bold', padding:10 }}>
                                                                        Buy again</button>
                                                                </Link>
                                                            </>

                                                        }
                                                    </td>
                                                </tr>
                                            </>
                                        )}
                                    </tbody>
                                </table>
                            </div>
                            <ModaRating item={itemSelect} handleCloseModal={handleCloseModal} showModal={showModal} handleCommented={handleCommented} />
                            <ModalConfirm title={'Success'} handleCloseModal={handleCommented} showModal={isComment} />
                        </div>
                    </div>
                </div>
            </section>
        </>
    );
}

export default DisplayOrder;