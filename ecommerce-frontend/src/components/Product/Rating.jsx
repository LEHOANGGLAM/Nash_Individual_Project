import React, { useEffect, useState } from 'react';
import Moment from 'react-moment';
import PropTypes from 'prop-types'
import ProductService from '../../services/ProductService';
import Products from './Products';
import queryString from 'query-string';
import RatingService from '../../services/RatingService';
import StarRatings from 'react-star-ratings';
import parse from 'html-react-parser'

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
    const [ratings, setRatings] = useState([])

    useEffect(() => {
        fetchSimilarProducts(cateId);
        fetchRatings(product.id);
    }, [])

    const fetchSimilarProducts = async (cateId) => {
        const filters = {
            cateId: cateId,
        }
        let predicates = queryString.stringify(filters);
        ProductService.getProductsByPredicates(predicates).then((res) => {
            setOthers(res.data.listResponse.slice(0, 3))
        })
    }
    
    const fetchRatings = async (id) => {
        RatingService.getRatingsByProductId(id).then((res) => {
            setRatings(res.data)
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
                            <p>{parse(product.desciption)}</p>
                        </div>
                    </div>

                    <div class="tab-pane fade" id="v-pills-2" role="tabpanel" aria-labelledby="v-pills-day-2-tab">
                        <div class="row">
                            <div class="col-md-11" style={{width: 1000}}>
                                <div class="p-4">
                                    <h3 class="mb-4" style={{ marginRight: 50 }}>{ratings.length} Reviews</h3>
                                </div>
                                {
                                    ratings.map((rating, index) =>
                                        <div class="review" key={index} style={{marginLeft: 30}}>
                                            <div class="user-img" style={{ backgroundImage: `url(${rating.userId.avatarImage})`  }}></div>
                                            <div class="desc">
                                                <h4>
                                                    <span class="text-left">{rating.userId.firstName} {rating.userId.lastName}</span>
                                                    <p style={{color: "#b3b3b3", fontSize: 15}} > On <Moment date={rating.createdDate} format="YYYY/MM/DD"  /> </p>
                                                </h4>
                                                    <StarRatings starDimension="14px"
                                                        starSpacing="0"
                                                        rating={rating.rating}
                                                        starRatedColor="brown"
                                                        numberOfStars={5}
                                                        name='rating'
                                                    />
                                                <p>{rating.content}</p>
                                            </div>
                                        </div>
                                    )
                                }
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
