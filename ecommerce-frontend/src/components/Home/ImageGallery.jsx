import React, { Component } from 'react';

import image1 from '../../images/gallery-1.jpg'
import image2 from '../../images/gallery-2.jpg'
import image3 from '../../images/gallery-3.jpg'
import image4 from '../../images/gallery-4.jpg'
import image5 from '../../images/gallery-5.jpg'
import image6 from '../../images/gallery-6.jpg'

class ImageGallery extends Component {
    render() {
        return (
            <>
                <section class="ftco-gallery">
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-md-8 heading-section text-center mb-4 ftco-animate fadeInUp ftco-animated">
                                <h2 class="mb-4">Follow Us On Instagram</h2>
                                <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in</p>
                            </div>
                        </div>
                    </div>
                    <div class="container-fluid px-0">
                        <div class="row no-gutters">
                            <div class="col-md-4 col-lg-2 ftco-animate fadeInUp ftco-animated">
                                <a href={image1} class="gallery image-popup img d-flex align-items-center" style={{backgroundImage: `url(${image1})` }}>
                                    <div class="icon mb-4 d-flex align-items-center justify-content-center">
                                        <span class="icon-instagram"></span>
                                    </div>
                                </a>
                            </div>
                            <div class="col-md-4 col-lg-2 ftco-animate fadeInUp ftco-animated">
                                <a href={image2} class="gallery image-popup img d-flex align-items-center" style={{backgroundImage: `url(${image2})` }}>
                                    <div class="icon mb-4 d-flex align-items-center justify-content-center">
                                        <span class="icon-instagram"></span>
                                    </div>
                                </a>
                            </div>
                            <div class="col-md-4 col-lg-2 ftco-animate fadeInUp ftco-animated">
                                <a href={image3} class="gallery image-popup img d-flex align-items-center" style={{backgroundImage: `url(${image3})` }}>
                                    <div class="icon mb-4 d-flex align-items-center justify-content-center">
                                        <span class="icon-instagram"></span>
                                    </div>
                                </a>
                            </div>
                            <div class="col-md-4 col-lg-2 ftco-animate fadeInUp ftco-animated">
                                <a href={image4} class="gallery image-popup img d-flex align-items-center" style={{backgroundImage: `url(${image4})` }}>
                                    <div class="icon mb-4 d-flex align-items-center justify-content-center">
                                        <span class="icon-instagram"></span>
                                    </div>
                                </a>
                            </div>
                            <div class="col-md-4 col-lg-2 ftco-animate fadeInUp ftco-animated">
                                <a href={image5} class="gallery image-popup img d-flex align-items-center" style={{backgroundImage: `url(${image5})` }}>
                                    <div class="icon mb-4 d-flex align-items-center justify-content-center">
                                        <span class="icon-instagram"></span>
                                    </div>
                                </a>
                            </div>
                            <div class="col-md-4 col-lg-2 ftco-animate fadeInUp ftco-animated">
                                <a href={image6} class="gallery image-popup img d-flex align-items-center" style={{backgroundImage: `url(${image6})` }}>
                                    <div class="icon mb-4 d-flex align-items-center justify-content-center">
                                        <span class="icon-instagram"></span>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </section>
            </>
        );
    }
}

export default ImageGallery;