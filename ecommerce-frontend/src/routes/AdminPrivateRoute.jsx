import React from 'react';
import AuthService from '../services/AuthService';
import { Navigate } from 'react-router-dom';
import PageNotFound from '../pages/Error/PageNotFound';

const AdminPrivateRoute = ({ children }) => {
    const user = AuthService.getCurrentUser();
   // console.log(user);

    if (user && user.roles.includes("admin")) {
      
        return <>{children}</>
    } else {
      
        return <PageNotFound />
    }
}

export default AdminPrivateRoute;