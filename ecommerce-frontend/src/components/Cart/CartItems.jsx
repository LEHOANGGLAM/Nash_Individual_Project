import React, { useEffect, useRef, useState } from 'react'

const CartItems = () => {

    return (
        <div class="row">
            <div class="col-md-12 ftco-animate fadeInUp ftco-animated">
                <div class="cart-list">
                    <table class="table">
                        <thead class="thead-primary">
                            <tr class="text-center">
                                <th>&nbsp;</th>
                                <th>&nbsp;</th>
                                <th>Product</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="text-center">
                                <td class="product-remove"><a href="#"><span class="ion-ios-close"></span></a></td>

                                <td class="image-prod"><div class="img" style={{ backgroundImage: "" }}></div></td>
                                <td class="product-name">
                                    <h3>Nike Free RN 2019 iD</h3>
                                    <p>Far far away, behind the word mountains, far from the countries</p>
                                </td>

                                <td class="price">$4.90</td>

                                <td class="quantity">
                                    <div class="input-group mb-3">
                                        <input type="text" name="quantity" class="quantity form-control input-number" value="1" min="1" max="100" />
                                    </div>
                                </td>

                                <td class="total">$4.90</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    )
}

export default CartItems;
