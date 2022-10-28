import React, { useEffect, useState } from 'react'
import CartTotal from '../../components/Cart/CartTotal';
import CartItems from '../../components/Cart/CartItems';
import HeadWrap from '../../components/HeadWrap/HeadWrap';
import CartService from '../../services/CartService';
import AuthService from '../../services/AuthService';

const Cart = () => {
    const [items, setItems] = useState([]);
    const [user, setCurrentUser] = useState([]);

    // const [checkedState, setCheckedState] = useState(
    //     new Array(items.length).fill(false)
    // );

    useEffect(() => {
        fetchCurrentUser()
    }, [])

    useEffect(() => {
        fetchCartItem(user.id);
    }, [user])
    //}, [user, items])

    const fetchCartItem = async (id) => {
        CartService.getCartItemsByUserId(id).then((res) => {
            setItems(res.data)
        })
    }
    const fetchCurrentUser = async () => {
        AuthService.getCurrentUser().then((res) => {
            setCurrentUser(res.data)
        })
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
                    <CartItems items={items} />
                    <CartTotal items={items} />
                </div>
            </section>
        </>
    )
}

export default Cart;