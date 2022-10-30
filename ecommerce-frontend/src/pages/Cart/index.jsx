import React, { useEffect, useState } from 'react'
import CartTotal from '../../components/Cart/CartTotal';
import CartItems from '../../components/Cart/CartItems';
import HeadWrap from '../../components/HeadWrap/HeadWrap';
import CartService from '../../services/CartService';
import AuthService from '../../services/AuthService';

const Cart = () => {
    const [items, setItems] = useState([]);
    const [isUpdate, setUpdate] = useState(false);

    useEffect(() => {
        fetchCartItem();
    }, [isUpdate])

    const fetchCartItem = async () => {
        const user = AuthService.getCurrentUser();
        CartService.getCartItemsByUserId(user.id).then((res) => {
            setItems(res.data);
            setUpdate(false)
        })
    }

    const handleUpdate = (boolean) => {
        setUpdate(boolean)
    }

    const handleCheckout = () => {
        if (items.length > 0) {
            localStorage.setItem("itemsCheckOut", JSON.stringify(items));
            window.location.pathname = ('/checkout');
        }
        else {
            console.log('Empty product to Checkout');
        }
    }

    return (
        <>
            <HeadWrap />
            <section class="ftco-section ftco-cart">
                <div class="container">
                    <CartItems items={items} handleUpdate={handleUpdate} />
                    <CartTotal items={items} handleCheckout={handleCheckout}/>
                </div>
            </section>
        </>
    )
}

export default Cart;