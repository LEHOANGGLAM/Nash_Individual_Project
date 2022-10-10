import React, { Component } from 'react';

import SimpleSlider from './SimpleSlider';
import ProductList from './ProductList';
import MiddleBar from './MiddleBar';
import ImageGallery from './ImageGallery';

class Home extends Component {
    render() {
        return (
            <>
                <SimpleSlider />
                <MiddleBar />
                <ProductList />
                <ImageGallery />
            </>
        );
    }
}

export default Home;