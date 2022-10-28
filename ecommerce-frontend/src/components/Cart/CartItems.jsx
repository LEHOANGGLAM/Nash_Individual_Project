import React, { useState } from 'react';
import PropTypes from 'prop-types'
import currencyFormat from '../Common/CurrencyFormat';
import CartService from '../../services/CartService';

CartItems.propTypes = {
    items: PropTypes.array,
    //     checkedState: PropTypes.array,
    //     handlecheckedItems: PropTypes.func,
}

CartItems.defaultProps = {
    items: null,
    // checkedState: [],
    // handlecheckedItems: null,
}

function CartItems(props) {
    const { items } = props;


    const handleInputsQuantity = (e, productId, sizeId, userId) => {
        if (e.target.value != 0) {
            const cartItem = {
                quantity: e.target.value,
                productId: productId,
                sizeId: sizeId,
    
            }
            CartService.updateCartItem(cartItem)
        }
    }

    const handleDeleteCartItem = (id) => {
        CartService.deleteCartItem(id);
    }

    // const handleOnChange = (index) => {
    //     if (handlecheckedItems) {
    //         handlecheckedItems(index);
    //     }
    // }
    // console.log(checkedState);
    return (
        <div class="row">
            <div class="col-md-12 ftco-animate fadeInUp ftco-animated">
                <div class="cart-list">
                    <table class="table">
                        <thead class="thead-primary">
                            <tr class="text-center">
                                <th>&nbsp;</th>
                                <th>&nbsp;</th>
                                <th>&nbsp;</th>
                                <th>Product</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            {items.map((item, index) =>
                                <tr class="text-center" key={item.id} >
                                    {/* <input class="checkedItem " style={{ marginTop: 110 }}
                                        type="checkbox"
                                        id={`custom-checkbox-${index}`}
                                        checked={checkedState[index]}
                                        onChange={() => handleOnChange(index)}
                                    /> */}
                                    <td class="product-remove " onClick={() => handleDeleteCartItem(item.id)}><a><span class="ion-ios-close" ></span></a></td>

                                    <td class="image-prod"><div class="img" style={{ backgroundImage: `url(${item.productId.imageCollection[0]?.link})` }}></div></td>
                                    <td class="product-name">
                                        <h3>{item.productId.title}</h3>
                                        <div>Size: {item.sizeId.sizeName} &ensp;
                                        </div>
                                    </td>

                                    <td class="price">{currencyFormat(item.productId.price)} VND</td>

                                    <td class="quantity">
                                        <div class="input-group mb-3">
                                            <input type="number" name="quantity" class="quantity form-control input-number" onChange={(e) => handleInputsQuantity(e, item.productId.id, item.sizeId.id)}
                                                value={item.quantity} min="1" max="100" />
                                        </div>
                                    </td>

                                    <td class="total">{currencyFormat(item.price)} VND</td>
                                </tr>
                            )}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    )
}

export default CartItems;
