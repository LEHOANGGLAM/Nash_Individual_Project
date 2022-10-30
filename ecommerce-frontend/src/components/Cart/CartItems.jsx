import React, { useState } from 'react';
import PropTypes from 'prop-types'
import currencyFormat from '../Common/CurrencyFormat';
import CartService from '../../services/CartService';

CartItems.propTypes = {
    items: PropTypes.array,
    handleUpdate: PropTypes.func,

}

CartItems.defaultProps = {
    items: null,
    handleUpdate: null,

}

function CartItems(props) {
    const { items, handleUpdate } = props;


    const handleInputsQuantity = (e, item) => {
        if (e.target.value > 0) {
            item.quantity = e.target.value;
            CartService.updateCartItem(item.id, item);
            handleUpdate(true)
        }
    }
    const handlePlusOrMinusQuantity = (newQuantity, item) => {
        if (newQuantity > 0) {
            item.quantity = newQuantity;
            CartService.updateCartItem(item.id, item);
            handleUpdate(true)
        }
    }

    const handleDeleteCartItem = (id) => {
        CartService.deleteCartItem(id);
        handleUpdate(true)
    }


    return (
        <div class="row">
            <div class="col-md-12 ftco-animate fadeInUp ftco-animated">
                <div class="cart-list">
                    <table class="table">
                        <thead class="thead-primary">
                            <tr class="text-center">
                                {/* <th>&nbsp;</th> */}
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
                                            <button type="button" data-type="minus" data-field="" onClick={() => handlePlusOrMinusQuantity(item.quantity - 1, item)}
                                               style={{width: 30}}>
                                                <i class="ion-ios-remove" style={{color: 'black'}}></i>
                                            </button>
                                            <input type="number" name="quantity" class="quantity form-control input-number" onChange={(e) => handleInputsQuantity(e, item)}
                                                value={item.quantity} min="1" max="100" />
                                            <button type="button" data-type="plus" data-field="" onClick={() => handlePlusOrMinusQuantity(item.quantity + 1, item)}
                                                 style={{width: 30}}>
                                                <i class="ion-ios-add" style={{color: 'black'}}></i>
                                            </button>
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
