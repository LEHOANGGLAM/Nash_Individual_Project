import React from 'react';
import StarRatingFormat from '../Common/StarRatingFormat';
import currencyFormat from '../Common/CurrencyFormat';
import PropTypes from 'prop-types'
import { Link } from 'react-router-dom';

Products.propTypes = {
    products: PropTypes.array,
}

Products.defaultProps = {
    products: null
}

function Products(props) {
    const { products } = props;

    return (
        <div class="row">
            {products.map((pro, index) =>

                <div class="col-sm-12 col-md-12 col-lg-4 ftco-animate d-flex fadeInUp ftco-animated" key={index}>
                    <Link to={`/product/${pro.id}`} >
                        <div class="product d-flex flex-column">
                            <a href="/" class="img-prod"><img class="img-fluid" src={pro.imageCollection[0].link} alt="Colorlib Template" />
                                <div class="overlay"></div>
                            </a>
                            <div class="text py-3 pb-4 px-3">
                                <div class="d-flex">
                                    <div class="cat">
                                        <span style={{ marginRight: 30 }}>Average Rating: {pro.averageRating.toFixed(1)}</span>
                                    </div>
                                    <div class="rating">
                                        <p class="text-right mb-0">
                                            <StarRatingFormat number={pro.averageRating} />
                                        </p>
                                    </div>
                                </div>
                                <h3><a href="/">{pro.title}</a></h3>
                                <div class="pricing">
                                    <p class="price"><span>
                                        {currencyFormat(pro.price)} VND
                                    </span></p>
                                </div>
                                <p class="bottom-area d-flex px-3">
                                    <a href="/" class="add-to-cart text-center py-2 mr-1"><span>Add to cart <i class="ion-ios-add ml-1"></i></span></a>
                                    <a href="/" class="buy-now text-center py-2">Buy now<span><i class="ion-ios-cart ml-1"></i></span></a>
                                </p>
                            </div>
                        </div>
                    </Link>
                </div>

            )}
        </div>
    )
}


export default Products;