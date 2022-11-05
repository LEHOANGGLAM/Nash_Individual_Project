import React from 'react';
import AuthService from '../services/AuthService';
import { Navigate } from 'react-router-dom';
import PageNotFound from '../pages/Error/PageNotFound';

const UserPrivateRoute = ({ children }) => {
    const user = AuthService.getCurrentUser();
    //console.log(user);

    if (user && user.roles.includes("user") ) {
        return <>{children}</>
    } else {

        return <PageNotFound />
    }
}

export default UserPrivateRoute;