import React, { useEffect, useRef, useState } from 'react'

const CartTotal = () => {
    const [items, setProducts] = useState([]);
   
    useEffect(() => {
    
    }, [])

    return (
        <div class="row justify-content-start">
            <div class="col col-lg-5 col-md-6 mt-5 cart-wrap ftco-animate fadeInUp ftco-animated">
                <div class="cart-total mb-3">
                    <h3>Cart Totals</h3>
                    <p class="d-flex">
                        <span>Subtotal</span>
                        <span>$20.60</span>
                    </p>
                    <p class="d-flex">
                        <span>Delivery</span>
                        <span>$0.00</span>
                    </p>
                    <p class="d-flex">
                        <span>Discount</span>
                        <span>$3.00</span>
                    </p>
                    <hr />
                    <p class="d-flex total-price">
                        <span>Total</span>
                        <span>$17.60</span>
                    </p>
                </div>
                <p class="text-center"><a href="checkout.html" class="btn btn-primary py-3 px-4">Proceed to Checkout</a></p>
            </div>
        </div>
    )
}

export default CartTotal;

