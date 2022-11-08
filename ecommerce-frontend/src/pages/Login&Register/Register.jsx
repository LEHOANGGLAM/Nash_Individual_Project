import React, { Component } from 'react';
import AuthService from '../../services/AuthService';
import ModalConfirm from '../../components/ModalConfirm';

class Register extends Component {
    constructor(props) {
        super(props)
        this.state = {
            fields: {},
            successful: false,
            message: "",

            showModal: false,
        }
        this.handleChange = this.handleChange.bind(this);
        this.handleRegister = this.handleRegister.bind(this);
    }

    handleShowModal = () => {
        this.setState({
            showModal: true,
        });
    }
    handleCloseModal = () => {  this.setState({
        showModal: false,
    }); }
    handleChange = (field, e) => {
        let fields = this.state.fields;
        fields[field] = e.target.value;
        this.setState({ fields });
    }

    handleValidation = () => {
        let formIsValid = true;
        if (this.state.fields["password"] !== this.state.fields["password2"]) {
            this.setState({
                successful: false,
                message: "Repeat password doesn't match"
            });
            formIsValid = false;
            return formIsValid;
        }

        return formIsValid;
    }

    handleRegister = async (e) => {
        e.preventDefault();

        this.setState({
            message: "",
            successful: false
        });

        console.log(this.state)
        if (this.handleValidation()) {
            await AuthService.register(
                this.state.fields["username"],
                this.state.fields["email"],
                this.state.fields["mobile"],
                this.state.fields["password"],
                ["user"],
            ).then(
                response => {
                    this.setState({
                        message: response.data.message,
                        successful: true
                    });
                    this.handleShowModal();
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
    }

    render() {
        return (
            <>
                <form action="#" class="" onSubmit={this.handleRegister}>
                    <div class="row align-items-end">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="firstname">Username: </label>
                                <input type="text" class="form-control" placeholder=""
                                    name="username" onChange={this.handleChange.bind(this, "username")} required />
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="lastname">Email:</label>
                                <input type="email" class="form-control" placeholder=""
                                    name="email" onChange={this.handleChange.bind(this, "email")} required />
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="lastname">Mobile phone:</label>
                                <input type="tel" pattern="[0-9]{10}" class="form-control" placeholder=""
                                    name="mobile" onChange={this.handleChange.bind(this, "mobile")} required />
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="lastname">Password</label>
                                <input type="password" class="form-control" placeholder=""
                                    name="password" onChange={this.handleChange.bind(this, "password")} required />
                            </div>
                        </div>

                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="lastname">Repeat Password</label>
                                <input type="password" class="form-control" placeholder=""
                                    name="password2" onChange={this.handleChange.bind(this, "password2")} required />
                            </div>
                        </div>
                    </div>
                    <p class="form-row">
                        <input type="submit" class="btn btn-primary py-3 px-4 col-md-12" name="login" value="Register" />
                    </p>
                    {this.state.message && (
                        <span style={this.state.successful ? { color: "green", justifyItems: 'center', display: 'grid', fontSize: 15 }
                            : { color: "red", justifyItems: 'center', display: 'grid', fontSize: 15 }}
                            role="alert"
                        >
                            {this.state.message}
                        </span>
                    )}
                </form>
                <ModalConfirm title={'Success'} handleCloseModal={this.handleCloseModal} showModal={this.state.showModal} link="/login" />
            </>
        );
    }
}

export default Register;