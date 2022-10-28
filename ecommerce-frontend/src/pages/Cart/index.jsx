import React, { useEffect, useState } from 'react'
import CartTotal from '../../components/Cart/CartTotal';
import CartItems from '../../components/Cart/CartItems';
import HeadWrap from '../../components/HeadWrap/HeadWrap';
import CartService from '../../services/CartService';
import AuthService from '../../services/AuthService';

const Cart = () => {
    const [items, setItems] = useState([]);
    const [isUpdate, setUpdate] = useState(false);
    // const [checkedState, setCheckedState] = useState(
    //     new Array(items.length).fill(false)
    // );

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
    // const handleOnChange = (position) => {
    //     const updatedCheckedState = checkedState.map((item, index) =>
    //         index === position ? !item : item
    //     );
    //     console.log(updatedCheckedState);
    //     setCheckedState(updatedCheckedState);

    //     const totalPri = updatedCheckedState.reduce(
    //         (sum, currentState, index) => {
    //             console.log(currentState);
    //             if (currentState === true) {
    //                 return sum + items[index].price;
    //             }
    //             return sum;
    //         },
    //         1
    //     );
    //     console.log(totalPri);
    //     setTotal(totalPri);
    // };

    return (
        <>
            <HeadWrap />
            <section class="ftco-section ftco-cart">
                <div class="container">
                    <CartItems items={items} handleUpdate={handleUpdate} />
                    <CartTotal items={items} />
                </div>
            </section>
        </>
    )
}

export default Cart;