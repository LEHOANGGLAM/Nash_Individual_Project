import React, { Component } from 'react';

class Login extends Component {
    constructor(props) {
        super(props)
        this.state = {
            fields: {},
            errors: {},
            loading: false,
            message: "",
            toggleTab: 1,
        }
        this.onHandleChange = this.onHandleChange.bind(this);
      
    }
    componentDidMount() {

    }
    onHandleChange = (field, e) => {
        let fields = this.state.fields;
        fields[field] = e.target.value;
        this.setState({ fields });
        // console.log( fields[field], e.target.value)
    }
    render() {
        let tab = this.state.toggleTab;
        return (
            <div>
            <div id="titlebar" class="single">
                <div class="container">

                    <div class="sixteen columns">
                        <h2>My Account</h2>
                        <nav id="breadcrumbs">
                            <ul>
                                <li>You are here:</li>
                                <li><a href="#">Home</a></li>
                                <li>My Account</li>
                            </ul>
                        </nav>
                    </div>

                </div>
            </div>
            <div class="container">

                <div class="my-account">

                    <ul class="tabs-nav">
                        <li class={tab == 1 ? "active" : ""} ><a href="#tab1">Login</a></li>
                        <li class={tab == 1 ? "" : "active"}><a href="#tab2">Register</a></li>
                    </ul>

                    <div class="tabs-container">

                        <div class="tab-content" id="tab1" style={{}}>
                            <form method="post" class="login" onSubmit={this.handleLogin}
                            >

                                <p class="form-row form-row-wide">
                                    <label for="username">Username:
                                        <i class="ln ln-icon-Male"></i>
                                        <input type="text" class="input-text" name="username" id="username"
                                            onChange={this.onHandleChange.bind(this, "username")} />
                                    </label>
                                </p>

                                <p class="form-row form-row-wide">
                                    <label for="password">Password:
                                        <i class="ln ln-icon-Lock-2"></i>
                                        <input class="input-text" type="password" name="password" id="password"
                                            onChange={this.onHandleChange.bind(this, "password")} />
                                    </label>
                                </p>

                                <p class="form-row">
                                    <input type="submit" class="button border fw margin-top-10" name="login" value="Login" />
                                    {this.state.message && (
                                          <span style={{ color: "red", justifyItems: 'center', display: 'grid', fontSize: 15}}>{this.state.message}</span>
                                    )}
                                    <label for="rememberme" class="rememberme">
                                        <input name="rememberme" type="checkbox" id="rememberme" value="forever" /> Remember Me</label>
                                </p>

                                <p class="lost_password">
                                    <a href="#">Lost Your Password?</a>
                                </p>

                            </form>
                        </div>

                        {/* REGISTER */}
                        <div class="tab-content" id="tab2" style={{}}>
                         
                        </div>
                    </div>
                </div>
            </div>
        </div>
        );
    }
}

export default Login;