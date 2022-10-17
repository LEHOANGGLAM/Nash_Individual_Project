import React, { Component } from "react";
import OwlCarousel from 'react-owl-carousel';

export default class SimpleSlider extends Component {
  render() {
    return (
      <div>
        <section id="home-section" class="hero">
          <div class="home-slider owl-carousel owl-loaded owl-drag">
            <div class="owl-stage-outer">
              <OwlCarousel className='owl-stage' dots items={1} autoplay loop autoplayTimeout={3000}>
                <div class="item " style={{ width: 1519.2 }}>
                  <div class="slider-item js-fullheight" >
                    <div class="overlay"></div>
                    <div class="container-fluid p-0">
                      <div class="row d-md-flex no-gutters slider-text align-items-center justify-content-end" data-scrollax-parent="true">
                        <img class="one-third order-md-last img-fluid" src={require('../../images/bg_1.png')} alt="" />
                        <div class="one-forth d-flex align-items-center ftco-animate fadeInUp ftco-animated" data-scrollax=" properties: { translateY: '70%' }" style={{ transform: ' translateZ(0px) translateY(53.4053%)' }}>
                          <div class="text">
                            <span class="subheading">#New Arrival</span>
                            <div class="horizontal">
                              <h1 class="mb-4 mt-3">Shoes Collection 2022</h1>
                              <p class="mb-4">A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country.</p>
                              <p>
                                <a href="#" class="btn-custom">Discover Now</a>
                              </p>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="item " style={{ width: 1519.2 }}>
                  <div class="slider-item js-fullheight" >
                    <div class="overlay"></div>
                    <div class="container-fluid p-0">
                      <div class="row d-flex no-gutters slider-text align-items-center justify-content-end" data-scrollax-parent="true">
                        <img class="one-third order-md-last img-fluid" src={require('../../images/bg_2.png')} alt="" />
                        <div class="one-forth d-flex align-items-center ftco-animate fadeInUp ftco-animated" data-scrollax=" properties: { translateY: '70%' }" style={{ transform: ' translateZ(0px) translateY(53.4053%)' }}>
                          <div class="text">
                            <span class="subheading">#New Arrival</span>
                            <div class="horizontal">
                              <h1 class="mb-4 mt-3">New Shoes Winter Collection</h1>
                              <p class="mb-4">A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country.</p>
                              <p>
                                <a href="#" class="btn-custom">Discover Now</a>
                              </p>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>

              </OwlCarousel>
            </div>
{/* 
            <div class="custom-nav owl-nav">
              <button role="presentation" className="prev" style={{borderRadius: '50%', width: 40, height: 40 ,margin: 10}}>
                <span class="ion-md-arrow-back"></span>
              </button>
              <button role="presentation" class="owl-next" style={{borderRadius: '50%', width: 40, height: 40 ,margin: 10}}>
                <span class="ion-md-arrow-forward"></span>
              </button>
            </div> */}

          </div>
        </section>
      </div>
    );
  }
}