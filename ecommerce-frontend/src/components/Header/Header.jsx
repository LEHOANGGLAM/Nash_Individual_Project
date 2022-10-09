import React, { Component } from 'react';

class Header extends Component {
    constructor(props) {
        super(props)
        this.state = {
            isHovering: false,
        }
        this.handleMouseOver = this.handleMouseOver.bind(this);
        this.handleMouseOut = this.handleMouseOut.bind(this);
    }

    componentDidMount() {

    }

    handleMouseOver = () => {
        this.setState({ isHovering: true })
    };

    handleMouseOut = () => {
        this.setState({ isHovering: false })
    };
    render() {
        return (
            <div>
                <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
                    <div class="container">
                        <a class="navbar-brand" href="index.html">Minishop</a>

                        <div class="collapse navbar-collapse" id="ftco-nav">
                            <ul class="navbar-nav ml-auto">
                                <li class="nav-item "><a href="index.html" class="nav-link">Home</a></li>
                                <li className={this.state.isHovering ? 'nav-item dropdown show' : 'nav-item dropdown'}
                                    onMouseOver={this.handleMouseOver}
                                    onMouseOut={this.handleMouseOut}>
                                    <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Catalog</a>
                                    <div 
                                        className={this.state.isHovering ? 'dropdown-menu show' : 'dropdown-menu'}
                                        onMouseOver={this.handleMouseOver}
                                        onMouseOut={this.handleMouseOut} aria-labelledby="dropdown04">
                                        <a class="dropdown-item" href="shop.html">Shop</a>
                                        <a class="dropdown-item" href="product-single.html">Single Product</a>
                                        <a class="dropdown-item" href="cart.html">Cart</a>
                                        <a class="dropdown-item" href="checkout.html">Checkout</a>
                                    </div>
                                </li>
                                <li class="nav-item"><a href="about.html" class="nav-link">About</a></li>
                                <li class="nav-item"><a href="blog.html" class="nav-link">Blog</a></li>
                                <li class="nav-item"><a href="contact.html" class="nav-link">Contact</a></li>

                                <li class="nav-item cta cta-colored"><a href="cart.html" class="nav-link"><span class="icon-shopping_cart"></span>[0]</a></li>
                                <li class="nav-item"><a href="/login" class="nav-link">login</a></li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>

        );
    }
}

export default Header;