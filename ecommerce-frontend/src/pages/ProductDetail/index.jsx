import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import HeadWrap from '../../components/HeadWrap/HeadWrap';
import ProductService from '../../services/ProductService';
import StarRatingFormat from '../../components/Common/StarRatingFormat';
import currencyFormat from '../../components/Common/CurrencyFormat';

ProductDetail.propTypes = {

}

ProductDetail.defaultProps = {

}


function ProductDetail(props) {
    const { productId } = useParams();
    const [product, setProduct] = useState([]);
    const [sizes, setSizes] = useState();
    console.log(product.sizeCollection);


    useEffect(() => {
        fetchProduct(productId);
      
    }, [])

    const fetchProduct = async (productId) => {
        await ProductService.getProductById(productId).then((res) => {
            setProduct(res.data)
        })
        setSizes(...product.sizeCollection)
    }

    return (
        <>
            <HeadWrap />
            <section class="ftco-section">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-6 mb-5 ftco-animate fadeInUp ftco-animated">
                            <a href="images/product-1.png" class="image-popup prod-img-bg"><img src="images/product-1.png" class="img-fluid" alt="Colorlib Template" /></a>
                        </div>
                        <div class="col-lg-6 product-details pl-md-5 ftco-animate fadeInUp ftco-animated">
                            <h3>Nike Free RN 2019 iD</h3>
                            <div class="rating d-flex">
                                <p class="text-left mr-4">
                                    <a href="/" class="mr-2">{product.averageRating}</a>
                                    <StarRatingFormat number={product.averageRating} />

                                </p>
                                <p class="text-left mr-4">
                                    <a href="#" class="mr-2" style={{ color: "#000" }}>{product.numberRating} <span style={{ color: "#bbb" }}>Rating</span></a>
                                </p>
                                <p class="text-left">
                                    <a href="#" class="mr-2" style={{ color: "#000" }}>{product.numberSold} <span style={{ color: "#bbb" }}>Sold</span></a>
                                </p>
                            </div>
                            <p class="price"><span> $100</span></p>

                            <p>{product.desciption}</p>

                            <div class="row mt-4">
                                <div class="col-md-6">
                                    <div class="form-group d-flex">
                                        <div class="select-wrap">
                                            <div class="icon"><span class="ion-ios-arrow-down"></span></div>
                                            <select name="" id="" class="form-control">
                                                {/* {product.sizeCollection.map(size =>
                                                    <option value={size.id}>{size.sizeName}</option>
                                                )} */}

                                                <option value="">Medium</option>
                                                <option value="">Large</option>
                                                <option value="">Extra Large</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="w-100"></div>
                                <div class="input-group col-md-6 d-flex mb-3">
                                    <span class="input-group-btn mr-2">
                                        <button type="button" class="quantity-left-minus btn" data-type="minus" data-field="">
                                            <i class="ion-ios-remove"></i>
                                        </button>
                                    </span>
                                    <input type="number" id="quantity" name="quantity" class="quantity form-control input-number" defaultValue={1} min="1" max="100" />
                                    <span class="input-group-btn ml-2">
                                        <button type="button" class="quantity-right-plus btn" data-type="plus" data-field="">
                                            <i class="ion-ios-add"></i>
                                        </button>
                                    </span>
                                </div>
                                <div class="w-100"></div>
                                <div class="col-md-12">
                                    <p style={{ color: "#000" }}>{product.quantity} piece available</p>
                                </div>
                            </div>
                            <p><a href="/" class="btn btn-black py-3 px-5 mr-2">Add to Cart</a><a href="/" class="btn btn-primary py-3 px-5">Buy now</a></p>
                        </div>
                    </div>
                </div>
            </section>
        </>
    )
}


export default ProductDetail;

