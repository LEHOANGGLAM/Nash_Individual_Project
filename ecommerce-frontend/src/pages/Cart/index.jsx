import React, { useEffect, useRef, useState } from 'react'
import CartTotal from '../../components/Cart/CartTotal';
import CartItems from '../../components/Cart/CartItems';
import HeadWrap from '../../components/HeadWrap/HeadWrap';

const Cart = () => {

    return (
        <>
            <HeadWrap />
            <section class="ftco-section ftco-cart">
                <div class="container">
                    <CartItems />
                    <CartTotal />
                </div>
            </section>
        </>
    )
}

export default Cart;