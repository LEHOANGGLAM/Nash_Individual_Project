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
    
    }
    render() {
        return (
            <>
                <form action="#" class="" >
                    <div class="row align-items-end">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="firstname">Username: </label>
                                <input type="text" class="form-control" placeholder="" />
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="lastname">Password</label>
                                <input type="text" class="form-control" placeholder="" />
                            </div>
                        </div>
                    </div>
                    <p class="form-row">
                        <input type="submit" class="btn btn-primary py-3 px-4 col-md-12" name="login" value="Login" />
                        {this.state.message && (
                            <span style={{ color: "red", justifyItems: 'center', display: 'grid', fontSize: 15 }}>{this.state.message}</span>
                        )}
                        <label for="rememberme" class="rememberme col-md-12" style={{ margin: 10 }}>
                            <input name="rememberme" type="checkbox" id="rememberme" value="forever" /> Remember Me</label>
                        <p class="lost_password" style={{ marginLeft: 10 }}>
                            <a href="#">Lost Your Password?</a>
                        </p>
                    </p>
                </form>
            </>
        );
    }
}

export default Login;