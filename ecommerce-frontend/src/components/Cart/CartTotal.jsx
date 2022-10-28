import React, { useEffect, useRef, useState } from 'react'
import currencyFormat from '../Common/CurrencyFormat';
import PropTypes from 'prop-types'

CartTotal.propTypes = {
    items: PropTypes.array,
}

CartTotal.defaultProps = {
    items: null
}

function CartTotal(props) {
    const { items } = props;
    const [totalPrice, setTotal] = useState(0);

    useEffect(() => {
        const total = items.reduce(
            (sum, value, index) => {               
                return sum + items[index].price;
            },
            0
        );
        setTotal(total);
    }, [items])

    return (
        <div class="row justify-content-start">
            <div class="col col-lg-5 col-md-6 mt-5 cart-wrap ftco-animate fadeInUp ftco-animated">
                <div class="cart-total mb-3">
                    <h3>Cart Totals</h3>
                    <p class="d-flex">
                        <span>Subtotal</span>
                        <span>{currencyFormat(totalPrice)} VND</span>
                    </p>
                    <p class="d-flex">
                        <span>Delivery</span>
                        <span>0.00 VND</span>
                    </p>
                    <p class="d-flex">
                        <span>Discount</span>
                        <span>0.00 VND</span>
                    </p>
                    <hr />
                    <p class="d-flex total-price">
                        <span>Total</span>
                        <span>{currencyFormat(totalPrice)} VND</span>
                    </p>
                </div>
                <p class="text-center"><a href="/checkout" class="btn btn-primary py-3 px-4">Proceed to Checkout</a></p>
            </div>
        </div>
    )
}

export default CartTotal;

