import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import CartTotal from '../../components/Cart/CartTotal';
import CartItems from '../../components/Cart/CartItems';
import HeadWrap from '../../components/HeadWrap/HeadWrap';
import CartService from '../../services/CartService';
import AuthService from '../../services/AuthService';

const Cart = () => {
    const navigate = useNavigate();
    const [items, setItems] = useState([]);
    const [isUpdate, setUpdate] = useState(false);

    useEffect(() => {
        localStorage.clear();
        fetchCartItem();
    }, [isUpdate])

    const fetchCartItem = async () => {
        const user = AuthService.getCurrentUser();
        if (user) {
            CartService.getCartItemsByUserId(user.id).then((res) => {
                setItems(res.data);
                setUpdate(false)
            })
        }
        else {
            navigate("/login");
        }
    }

    const handleUpdate = (boolean) => {
        setUpdate(boolean)
    }

    const handleCheckout = () => {
        if (items.length > 0) {
            //localStorage.clear();
            localStorage.setItem("itemsCheckOut", JSON.stringify(items));
            navigate('/checkout');
        }
        else {
            console.log('Empty product to Checkout');
        }
    }

    return (
        <>
            <HeadWrap />
            <section class="ftco-section ftco-cart">
                <div class="row">
                    <CartItems items={items} handleUpdate={handleUpdate} />
                    <CartTotal items={items} handleCheckout={handleCheckout} />
                </div>
            </section>
        </>
    )
}

export default Cart;