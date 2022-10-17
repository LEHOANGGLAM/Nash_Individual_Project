import React, { Component } from 'react';
import './error404.css'

class PageNotFound extends Component {
    render() {
        return (
            <div class="error">
            
                <div class="container-floud">
                    <div class="col-xs-12 ground-color text-center">
                        <div class="container-error-404">
                            <div class="clip"><div class="shadow"><span class="digit thirdDigit"><h1 style={{ fontSize: 100}}> 4</h1></span></div></div>
                            <div class="clip"><div class="shadow"><span class="digit secondDigit"><h1 style={{ fontSize: 100}}> 0</h1></span></div></div>
                            <div class="clip"><div class="shadow"><span class="digit firstDigit"><h1 style={{ fontSize: 100}}> 4</h1></span></div></div>
                            <div class="msg">OH!<span class="triangle"></span></div>
                        </div>
                        <h2 class="h1">Sorry! Page not found</h2>
                        <h2 class="h1">  <a href='/' className='btn btn-primary' style={{fontSize: 20, marginBottom: 50}}>Go to HOME page</a></h2>
                      
                    </div>
                </div>
            </div>
        );
    }
}

export default PageNotFound;