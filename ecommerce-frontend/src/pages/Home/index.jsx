import React, { useEffect, useState } from 'react';
import SimpleSlider from '../../components/Home/SimpleSlider';
import ProductService from '../../services/ProductService';
import MiddleBar from '../../components/Home/MiddleBar';
import ImageGallery from '../../components/Home/ImageGallery';
import Products from '../../components/Product/Products';



const Home = () => {
    const [products, setOthers] = useState([])

    useEffect(() => {
        fetchProducts();
    }, [])

    const fetchProducts = async () => {
        ProductService.getProductsByPredicates().then((res) => {
            setOthers(res.data.listResponse.slice(0, 6))
        })
    }

    return (
        <div>
            <SimpleSlider />
            <MiddleBar />
            <section class="ftco-section bg-light">
                <div class="container">
                    <div class="row justify-content-center mb-3 pb-3">
                        <div class="col-md-12 heading-section text-center ftco-animate fadeInUp ftco-animated">
                            <h2 class="mb-4">New Shoes Arrival</h2>
                            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia</p>
                        </div>
                    </div>
                </div>
                <div class="container">
                    <Products products={products} />
                </div>
            </section>
            <ImageGallery />
        </div>
    )
}

export default Home;