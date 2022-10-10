import React, { Component } from 'react';
import NavBar from './NavBar';
import TopBar from './TopBar';

class Header extends Component {
    render() {
        return (
            <>
                <TopBar />
                <NavBar />
            </>
        );
    }
}

export default Header;