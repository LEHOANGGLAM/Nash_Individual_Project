import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import PropTypes from 'prop-types'
import ProductService from '../../services/ProductService';
import Products from './Products';
import queryString from 'query-string';

Rating.propTypes = {
    product: PropTypes.object,
    cateId: PropTypes.string,
}

Rating.defaultProps = {
    product: null,
    cateId: null,
}

function Rating(props) {
    const { product } = props;
    const { cateId } = props;
    const [otherProducts, setOthers] = useState([])


    useEffect(() => {
        fetchProducts(cateId);
    }, [])

    const fetchProducts = async (cateId) => {
        const filters = {
            cateId: cateId,
        }
        let predicates = queryString.stringify(filters);
        ProductService.getProductsByPredicates(predicates).then((res) => {
            setOthers(res.data.listResponse.slice(0, 3))
        })
    }

    return (
        <div class="row mt-5">
            <div class="col-md-12 nav-link-wrap">
                <div class="nav nav-pills d-flex text-center" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                    <a class="nav-link ftco-animate active mr-lg-1 fadeInUp ftco-animated" id="v-pills-1-tab" data-toggle="pill" href="#v-pills-1" role="tab" aria-controls="v-pills-1" aria-selected="true">Manufacturer</a>

                    <a class="nav-link ftco-animate mr-lg-1 fadeInUp ftco-animated" id="v-pills-2-tab" data-toggle="pill" href="#v-pills-2" role="tab" aria-controls="v-pills-2" aria-selected="false">Reviews</a>

                    <a class="nav-link ftco-animate fadeInUp ftco-animated" id="v-pills-3-tab" data-toggle="pill" href="#v-pills-3" role="tab" aria-controls="v-pills-3" aria-selected="false">Similar Products</a>

                </div>
            </div>
            <div class="col-md-12 tab-wrap">
                <div class="tab-content bg-light" id="v-pills-tabContent">

                    <div class="tab-pane fade show active" id="v-pills-1" role="tabpanel" aria-labelledby="day-1-tab">
                        <div class="p-4">
                            <h3 class="mb-4">Manufacturer by {product.title}</h3>
                            <p>{product.desciption}</p>
                        </div>
                    </div>

                    <div class="tab-pane fade" id="v-pills-2" role="tabpanel" aria-labelledby="v-pills-day-2-tab">
                        <div class="row">
                            <div class="col-md-12" >
                                <div class="p-4">
                                    <h3 class="mb-4" style={{ marginRight: 50 }}>23 Reviews</h3>
                                </div>
                                <div class="review">
                                    <div class="user-img" style={{ backgroundImage: `url(images/person_2.jpg)` }}></div>
                                    <div class="desc">
                                        <h4>
                                            <span class="text-left">Jacob Webb</span>
                                            <span class="text-right">14 March 2018</span>
                                        </h4>
                                        <p class="star">
                                            <span>
                                                <i class="ion-ios-star-outline"></i>
                                                <i class="ion-ios-star-outline"></i>
                                                <i class="ion-ios-star-outline"></i>
                                                <i class="ion-ios-star-outline"></i>
                                                <i class="ion-ios-star-outline"></i>
                                            </span>
                                            <span class="text-right"><a href="#" class="reply"><i class="icon-reply"></i></a></span>
                                        </p>
                                        <p>When she reached the first hills of the Italic Mountains, she had a last view back on the skyline of her hometown Bookmarksgrov</p>
                                    </div>
                                </div>
                                
                            </div>
                        </div>
                    </div>

                    <div class="tab-pane fade" id="v-pills-3" role="tabpanel" aria-labelledby="v-pills-day-3-tab">
                        <div class="row" style={{ marginLeft: 120 }}>

                            <h3 class="mb-4">Similar  Products</h3>
                            <div class="col-md-12 col-lg-11 order-md-last">
                                {Object.keys(otherProducts).length !== 0 ?
                                    <Products products={otherProducts} />
                                    : <>No Product found</>
                                }
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Rating;
