import React, { Component } from 'react';

class Register extends Component {
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
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="lastname">Email</label>
                                <input type="text" class="form-control" placeholder="" />
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="lastname">Repeat Password</label>
                                <input type="text" class="form-control" placeholder="" />
                            </div>
                        </div>
                    </div>
                    <p class="form-row">
                        <input type="submit" class="btn btn-primary py-3 px-4 col-md-12" name="login" value="Register" />
                    </p>
                </form>
            </>
        );
    }
}

export default Register;