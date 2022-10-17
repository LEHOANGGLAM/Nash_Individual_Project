import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import bg from '../../images/bg_6.jpg'

class HeadWrap extends Component {
    constructor(props) {
        super(props)
        this.state = {
            name: this.props.match.params.name,
        }
    }

    componentDidMount(){
        
    }
    render() {
        return (
            <>
                <div class="hero-wrap hero-bread" style={{backgroundImage: `url(${bg})`}}>
                    <div class="container">
                        <div class="row no-gutters slider-text align-items-center justify-content-center">
                            <div class="col-md-9 ftco-animate text-center fadeInUp ftco-animated">
                                <p class="breadcrumbs"><span class="mr-2"><a href="index.html">Home</a></span> <span>Shop</span></p>
                                <h1 class="mb-0 bread">Shop</h1>
                            </div>
                        </div>
                    </div>
                </div>
            </>
        );
    }
}

export default withRouter(HeadWrap);