import React, { useState } from 'react';
import StarRatings from 'react-star-ratings';
import currencyFormat from '../Common/CurrencyFormat';
import PropTypes from 'prop-types'
import { Link } from 'react-router-dom';
import CartService from '../../services/CartService';
import AuthService from '../../services/AuthService';
import { Modal, ModalHeader } from 'reactstrap';

Products.propTypes = {
    products: PropTypes.array,
    handleOpenModal: PropTypes.func
}

Products.defaultProps = {
    products: null,
    handleOpenModal: null
}

function Products(props) {
    const { products, handleOpenModal } = props;

    const addToCart = (pro) => {
        const user = AuthService.getCurrentUser();
        const cartItem = {
            quantity: 1,
            productId: pro.id,
            sizeId: {
                id: pro.sizeCollection[0]?.id
            }
        }

        let mess;
        CartService.addItemIntoCart(cartItem, user.id).then((res) => {
            mess = res.data.code ? res.data.message : "Add product successful!"
            handleOpenModal(true, mess);
        }, (err) => {
            console.log(err);
        }
        )
    }

    return (
        <div class="row">
            {Object.keys(products).length !== 0 ?
                <>
                    {
                        products?.map((pro, index) =>
                            <div class="col-sm-12 col-md-12 col-lg-4 ftco-animate d-flex fadeInUp ftco-animated" key={index}>

                                <div class="product d-flex flex-column">
                                    <Link to={`/product-${pro.id}`} target='_parent'>
                                        <a href={`/product-${pro.id}`} class="img-prod"><img class="img-fluid" src={pro?.imageCollection[0]?.link} alt="Colorlib Template" />
                                            <div class="overlay"></div>
                                        </a>
                                    </Link>
                                    <div class="text py-3 pb-4 px-3">
                                        <div class="d-flex">
                                            <div class="cat">
                                                <span style={{ marginRight: 30 }}>Average Rating: {pro.averageRating.toFixed(1)}</span>
                                            </div>
                                            <div class="rating">
                                                <StarRatings starDimension="14px"
                                                    starSpacing="0"
                                                    rating={pro.averageRating}
                                                    starRatedColor="brown"
                                                    numberOfStars={5}
                                                    name='rating'
                                                />
                                            </div>
                                        </div>
                                        <h3><a href="/">{pro.title}</a></h3>
                                        <div class="pricing">
                                            <p class="price"><span>
                                                {currencyFormat(pro.price)} VND
                                            </span></p>
                                        </div>
                                        <p class="bottom-area d-flex px-3">
                                            <a onClick={() => addToCart(pro)} class="add-to-cart text-center py-2 mr-1" style={{ cursor: 'pointer' }}><span>Add to cart <i class="ion-ios-add ml-1"></i></span></a>
                                            <a href="/checkout" class="buy-now text-center py-2">Buy now<span><i class="ion-ios-cart ml-1"></i></span></a>
                                        </p>
                                    </div>
                                </div>

                            </div>
                        )
                    }
                </>
                : <h1>No products Found</h1>
            }
            {/* <div class="pricing">
                                <p class="price"><span class="mr-2 price-dc">$120.00</span><span class="price-sale">$80.00</span></p>
                            </div> */}
        </div>
    )
}


export default Products;