import React, { Component } from 'react';
import AuthService from '../../../services/AuthService';
import { withRouter } from 'react-router-dom';

class Login extends Component {
    constructor(props) {
        super(props)
        this.state = {
            fields: {},
            errors: {},
            loading: false,
            message: "",
        }
        this.handleChange = this.handleChange.bind(this);
        this.handleLogin = this.handleLogin.bind(this);
    }
    componentDidMount() {

    }
    handleChange = (field, e) => {
        let fields = this.state.fields;
        fields[field] = e.target.value;
        this.setState({ fields });
    }
    handleLogin(e) {
        e.preventDefault();

        this.setState({
            message: "",
            loading: true
        });


        AuthService.login(this.state.fields["username"], this.state.fields["password"]).then(
            () => {
                this.props.history.push('/');
                window.location.reload();
            },
            error => {
                const resMessage =
                (error.response &&
                    error.response.data &&
                    error.response.data.message) ||
                error.message ||
                error.toString();

            this.setState({
                successful: false,
                message: resMessage
            });
            }
        );
    }
    render() {
        return (
            <>
                <form action="#" class="" onSubmit={this.handleLogin}>
                    <div class="row align-items-end">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="firstname">Username: </label>
                                <input type="text" class="form-control" placeholder="" name="username"
                                    onChange={this.handleChange.bind(this, "username")} />
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="lastname">Password</label>
                                <input type="password" class="form-control" placeholder="" name="password"
                                    onChange={this.handleChange.bind(this, "password")} />
                            </div>
                        </div>
                    </div>
                    <p class="form-row">
                        <input type="submit" class="btn btn-primary py-3 px-4 col-md-12" name="login" value="Login" />

                    </p>
                    {this.state.message && (
                        <span style={{ color: "red", justifyItems: 'center', display: 'grid', fontSize: 15 }}>{this.state.message}</span>
                    )}
                    {/* <label for="rememberme" class="rememberme col-md-12" style={{ margin: 10 }}>
                            <input name="rememberme" type="checkbox" id="rememberme" value="forever" /> Remember Me</label> */}
                    <p class="lost_password" style={{ marginLeft: 10 }}>
                        <a href="#">Lost Your Password?</a>
                    </p>
                </form>
            </>
        );
    }
}

export default withRouter(Login);;