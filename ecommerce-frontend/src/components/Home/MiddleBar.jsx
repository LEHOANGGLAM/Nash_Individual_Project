import React, { Component } from 'react';

class MiddleBar extends Component {
    render() {
        return (
            <>
                <section class="ftco-section ftco-no-pt ftco-no-pb">
                    <div class="container">
                        <div class="row no-gutters ftco-services">
                            <div class="col-lg-4 text-center d-flex align-self-stretch ftco-animate fadeInUp ftco-animated">
                                <div class="media block-6 services p-4 py-md-5">
                                    <div class="icon d-flex justify-content-center align-items-center mb-4">
                                        <span class="flaticon-bag"></span>
                                    </div>
                                    <div class="media-body">
                                        <h3 class="heading">Free Shipping</h3>
                                        <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 text-center d-flex align-self-stretch ftco-animate fadeInUp ftco-animated">
                                <div class="media block-6 services p-4 py-md-5">
                                    <div class="icon d-flex justify-content-center align-items-center mb-4">
                                        <span class="flaticon-customer-service"></span>
                                    </div>
                                    <div class="media-body">
                                        <h3 class="heading">Support Customer</h3>
                                        <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 text-center d-flex align-self-stretch ftco-animate fadeInUp ftco-animated">
                                <div class="media block-6 services p-4 py-md-5">
                                    <div class="icon d-flex justify-content-center align-items-center mb-4">
                                        <span class="flaticon-payment-security"></span>
                                    </div>
                                    <div class="media-body">
                                        <h3 class="heading">Secure Payments</h3>
                                        <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </>
        );
    }
}

export default MiddleBar;