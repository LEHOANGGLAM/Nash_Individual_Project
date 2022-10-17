import React, { Component } from 'react';
import ProductService from '../../services/ProductService';
import HeadWrap from './HeadWrap';
import StarRatingFormat from '../Common/StarRatingFormat';
import currencyFormat from '../Common/CurrencyFormat';
import queryString from 'query-string'

class ShoppingList extends Component {
    constructor(props) {
        super(props)
        this.state = {
            products: [],
            totalPage: 0,
            currentPage: 0,

            filters: {
                page: 0,
                fromPrice: 0,
                toPrice: 9999999999,
                name: null,
                cateId: null,
            }
        }
    }

    componentDidMount() {
        this.loadProducts();
    }


    loadProducts() {
        let predicates = queryString.stringify(this.state.filters);
        ProductService.getAllProducts(predicates).then((res) => {
            this.setState({ products: res.data.listResponse, totalPage: res.data.totalPage })
        })
    }

    // handlePageChange(newPage){
    //     this.setState({
    //         filters:{
    //             page: newPage
    //         }
    //     })
    // }

    render() {
        var paging = [];
        // for (var i = 0; i < this.state.totalPage; i++) {
        //     paging.push(<li ><a ><button onClick={this.loadProducts(i)}>{i + 1}</button></a></li>);
        // }
        //console.log(this.state.filters.page);
        return (
            <>
                <HeadWrap />
                <section class="ftco-section bg-light">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-4 col-lg-2">
                                <div class="sidebar">
                                    <div class="sidebar-box-2">
                                        <h2 class="heading">Categories</h2>
                                        <div class="fancy-collapse-panel">
                                            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                                                <div class="panel panel-default">
                                                    <div class="panel-heading" role="tab" id="headingOne">
                                                        <h4 class="panel-title">
                                                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">Men's Shoes
                                                            </a>
                                                        </h4>
                                                    </div>
                                                    <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                                                        <div class="panel-body">
                                                            <ul>
                                                                <li><a href="#">Sport</a></li>
                                                                <li><a href="#">Casual</a></li>
                                                                <li><a href="#">Running</a></li>
                                                                <li><a href="#">Jordan</a></li>
                                                                <li><a href="#">Soccer</a></li>
                                                                <li><a href="#">Football</a></li>
                                                                <li><a href="#">Lifestyle</a></li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                    <div class="sidebar-box-2">
                                        <h2 class="heading">Price Range</h2>
                                        <form method="post" class="colorlib-form-2">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="guests">Price from:</label>
                                                        <div class="form-field">
                                                            <i class="icon icon-arrow-down3"></i>
                                                            <select name="people" id="people" class="form-control">
                                                                <option value="#">1</option>
                                                                <option value="#">200</option>
                                                                <option value="#">300</option>
                                                                <option value="#">400</option>
                                                                <option value="#">1000</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="guests">Price to:</label>
                                                        <div class="form-field">
                                                            <i class="icon icon-arrow-down3"></i>
                                                            <select name="people" id="people" class="form-control">
                                                                <option value="#">2000</option>
                                                                <option value="#">4000</option>
                                                                <option value="#">6000</option>
                                                                <option value="#">8000</option>
                                                                <option value="#">10000</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-8 col-lg-10 order-md-last">
                                <div class="row">
                                    {this.state.products.map((pro, index) =>
                                        <div class="col-sm-12 col-md-12 col-lg-4 ftco-animate d-flex fadeInUp ftco-animated" key={index}>
                                            <div class="product d-flex flex-column">
                                                <a href="#" class="img-prod"><img class="img-fluid" src={pro.imageCollection[0].link} alt="Colorlib Template" />
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
                                        </div>
                                    )}
                                </div>
                                <div class="row mt-5">
                                    <div class="col text-center">
                                        <div class="block-27">
                                            <ul >
                                                {/* <li ><a href={this.handlePageChange(2)}>&lt;</a></li>
                                                {paging}
                                                <li><a href={this.handlePageChange(3)}>&gt;</a></li> */}
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>


                        </div>
                    </div>
                </section>
            </>
        );
    }
}

export default ShoppingList;