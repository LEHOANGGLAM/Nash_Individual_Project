import React, { Component } from 'react';
import AuthService from '../../services/AuthService';
import CateService from '../../services/CateService';
import UserService from '../../services/UserService';


class NavBar extends Component {
    constructor(props) {
        super(props)
        this.state = {
            showUser: false,
            showAdmin: false,
            currentUser: undefined,
            isHovering: false,

            cates: [],
        }
        this.handleMouseOver = this.handleMouseOver.bind(this);
        this.handleMouseOut = this.handleMouseOut.bind(this);
        this.logOut = this.logOut.bind(this);
    }

    handleMouseOver = () => {
        this.setState({ isHovering: true })
    };

    handleMouseOut = () => {
        this.setState({ isHovering: false })
    };

    componentDidMount() {
        const user = AuthService.getCurrentUser();
        if (user) {
            UserService.getUserById(user.id).then((res) => {
                this.setState({
                    currentUser: res.data,
                    showUser: user.roles.includes("user"),
                    showAdmin: user.roles.includes("admin"),
                });
            })
        }

        CateService.getAllCates().then((res) => {
            this.setState({
                cates: res.data,
            });
        })
    }

    logOut() {
        AuthService.logout();
        this.setState({
            showUser: false,
            showAdmin: false,
            currentUser: undefined,
        });
        window.location.pathname = `/login`;
    }

    render() {
        const { currentUser, showAdmin, showUser } = this.state;
        const isHovering = this.state.isHovering;
        return (
            <div>
                <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
                    <div class="container">
                        <a class="navbar-brand" href="/">Minishop</a>
                        <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="oi oi-menu"></span> Menu
                        </button>
                        <div class="collapse navbar-collapse" id="ftco-nav">
                            <ul class="navbar-nav ml-auto">
                                <li class="nav-item "><a href="/" class="nav-link">Home</a></li>
                                {showUser && (
                                    <>
                                        <li className={isHovering ? 'nav-item dropdown show' : 'nav-item dropdown'}
                                            onMouseOver={this.handleMouseOver}
                                            onMouseOut={this.handleMouseOut}>
                                            <a class="nav-link dropdown-toggle" href="/products" id="dropdown04"
                                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Catalog</a>
                                            <div
                                                className={isHovering ? 'dropdown-menu show' : 'dropdown-menu'}
                                                onMouseOver={this.handleMouseOver}
                                                onMouseOut={this.handleMouseOut} aria-labelledby="dropdown04">
                                                {this.state.cates.map((cate, index) =>
                                                    <a class="dropdown-item" href="/products" key={index}>{cate.title}</a>
                                                )}

                                            </div>
                                        </li>
                                        <li class="nav-item"><a href="" class="nav-link">About</a></li>
                                        <li class="nav-item"><a href="" class="nav-link">Blog</a></li>
                                        <li class="nav-item"><a href="" class="nav-link">Contact</a></li>

                                        <li class="nav-item cta cta-colored"><a href="" class="nav-link"><span class="icon-shopping_cart"></span>[0]</a></li>

                                    </>
                                )}
                                {showAdmin && (
                                    <>


                                    </>
                                )}
                                {currentUser == undefined ? (
                                    <>
                                        <li class="nav-item"><a href="/login" class="nav-link">Login & Sign Up</a></li>
                                    </>

                                ) : (
                                    <>
                                        <li className='nav-item dropdown'>
                                            <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown"
                                                aria-haspopup="true" aria-expanded="false">

                                                {(currentUser.avatarImage === null || currentUser.avatarImage === '') ? (
                                                    <img src={require('../../images/default_ava.jpg')} alt=""
                                                        style={{ width: 25, height: 25, display: 'inline-block', borderRadius: '50%', verticalAlign: 'middle', marginRight: 10 }} />
                                                ) : (
                                                    <img src={currentUser.avatarImage} alt=""
                                                        style={{ width: 30, height: 25, display: 'inline-block', borderRadius: '50%', verticalAlign: 'middle', marginRight: 10 }} />
                                                )}

                                                Hi, {currentUser.firstName} {currentUser.lastName}</a>
                                            <div className='dropdown-menu' aria-labelledby="dropdown04">
                                                <a class="dropdown-item" href="/">My Profile</a>
                                                <a class="dropdown-item" onClick={this.logOut}>Log Out</a>
                                            </div>
                                        </li>
                                    </>
                                )}

                            </ul>
                        </div>
                    </div>
                </nav>
            </div>

        );
    }
}

export default NavBar;