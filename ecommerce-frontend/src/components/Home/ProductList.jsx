import React, { Component } from 'react';
import ProductService from '../../services/ProductService';
import StarRatingFormat from '../Common/StarRatingFormat';
import currencyFormat from '../Common/CurrencyFormat';

class ProductList extends Component {
    constructor(props) {
        super(props)
        this.state = {
            products: [],
        }

    }
    componentDidMount() {
        ProductService.getAllProducts().then((res) => {
            this.setState({ products: res.data.listResponse })
        })
    }

    render() {
        // console.log(this.state.products[0]);
        return (
            <>
                <section class="ftco-section bg-light">
                    <div class="container">
                        <div class="row justify-content-center mb-3 pb-3">
                            <div class="col-md-12 heading-section text-center ftco-animate fadeInUp ftco-animated">
                                <h2 class="mb-4">New Shoes Arrival</h2>
                                <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia</p>
                            </div>
                        </div>
                    </div>
                    <div class="container">
                        <div class="row">
                            {this.state.products.map(
                                (pro, index) =>
                                    (index < 8) ?
                                        <div class="col-sm-12 col-md-6 col-lg-3 ftco-animate d-flex fadeInUp ftco-animated" key={index}>
                                            <div class="product d-flex flex-column">
                                                <a href="#" class="img-prod"><img class="img-fluid" src={pro.imageCollection[0].link} alt="Colorlib Template" />
                                                    <div class="overlay"></div>
                                                </a>
                                                <div class="text py-3 pb-4 px-2">
                                                    <div class="d-flex">
                                                        <div class="cat">
                                                            <span style={{ marginRight: 20 }}>Average Rating: {pro.averageRating.toFixed(1)}</span>
                                                        </div>
                                                        <div class="rating">
                                                            <p class="text-right mb-0">
                                                                <StarRatingFormat number={pro.averageRating} />
                                                            </p>
                                                        </div>
                                                    </div>
                                                    <h3><a href="#">{pro.title}</a></h3>
                                                    <div class="pricing">
                                                        <p class="price"><span>
                                                            {currencyFormat(pro.price)} VND
                                                        </span></p>
                                                    </div>
                                                    <p class="bottom-area d-flex px-3">
                                                        <a href="#" class="add-to-cart text-center py-2 mr-1"><span>Add to cart <i class="ion-ios-add ml-1"></i></span></a>
                                                        <a href="#" class="buy-now text-center py-2">Buy now<span><i class="ion-ios-cart ml-1"></i></span></a>
                                                    </p>
                                                </div>
                                            </div>
                                        </div> : null

                            )}

                            {/* <div class="pricing">
                                <p class="price"><span class="mr-2 price-dc">$120.00</span><span class="price-sale">$80.00</span></p>
                            </div> */}
                        </div>
                    </div>
                </section>
            </>
        );
    }
}

export default ProductList;