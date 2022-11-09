import PropTypes from 'prop-types'
import StarRatings from 'react-star-ratings';
import CartService from '../../services/CartService';
import currencyFormat from '../Common/CurrencyFormat';
import AuthService from '../../services/AuthService';
import { useState } from 'react';
import { Modal, ModalHeader } from 'reactstrap';
import { useNavigate } from 'react-router-dom';
import parse from 'html-react-parser'

ProductInfo.propTypes = {
    product: PropTypes.object,
}

ProductInfo.defaultProps = {
    product: null,
}


function ProductInfo(props) {
    const navigate = useNavigate();
    const { product } = props;
    const [quantity, setQuantity] = useState(1);
    const [sizeId, setSizeId] = useState(
        product.sizeCollection[0] ? product.sizeCollection[0]?.id : null
    );

    //handle POPUP MODEL
    const [isOpen, setOpen] = useState(false);
    const [message, setMessage] = useState();

    const handleCheckout = () => {
        if (product) {
            const item = [
                {
                    price: quantity * product.price,
                    quantity: quantity,
                    productId: product,
                    sizeId: {
                        id: sizeId
                    },
                }
            ];
            //console.log(item);
            localStorage.clear();
            localStorage.setItem("itemsCheckOut", JSON.stringify(item));
            navigate('/checkout');
        }
        else {
            console.log('Empty product to Checkout');
        }
    }

    const addToCart = (pro) => {
        const user = AuthService.getCurrentUser();
        if (user) {
            const cartItem = {
                quantity: quantity,
                productId: pro.id,
                sizeId: {
                    id: sizeId
                },
            }
            CartService.addItemIntoCart(cartItem, user.id).then((res) => {
                res.data.code ? setMessage(res.data.message) : setMessage("Add product successful!")
            }, (err) => {
                const resMessage =
                    (err.response &&
                        err.response.data &&
                        err.response.data.message) ||
                    err.message ||
                    err.toString();
                setMessage(resMessage);
                console.log(err);
            }
            )

            //handle POPUP MODEL
            setOpen(true);
        } else {
            window.location.pathname = "/login"
        }
    }

    const handleInputsQuantity = (e) => {
        if (e.target.value > 0) {
            setQuantity(e.target.value)
        }
    }

    const handleMinusQuantity = (newQuantity) => {
        if (newQuantity > 0) {
            setQuantity(newQuantity)
        }
    }

    const handleSizeChange = (e) => {
        setSizeId(e.target.value)
    }
    return (
        <>
            <div class="col-lg-6 product-details pl-md-5 ftco-animate fadeInUp ftco-animated">
                <h3>{product.title}</h3>
                <div class="rating d-flex">
                    <div class="text-left mr-4">
                        <a href="/" class="mr-2">{product.averageRating?.toFixed(2)}</a>
                        <StarRatings starDimension="14px"
                            starSpacing="0"
                            rating={product.averageRating}
                            starRatedColor="brown"
                            numberOfStars={5}
                            name='rating'
                        />
                    </div>
                    <p class="text-left mr-4">
                        <a href="#" class="mr-2" style={{ color: "#000" }}>{product.numberRating} <span style={{ color: "#bbb" }}>Rating</span></a>
                    </p>
                    <p class="text-left">
                        <a href="#" class="mr-2" style={{ color: "#000" }}>{product.numberSold} <span style={{ color: "#bbb" }}>Sold</span></a>
                    </p>
                </div>
                <p class="price"><span>{currencyFormat(product.price)} VND</span></p>

                <p style={{ height: 400 }}>{parse(product.desciption)}</p>

                <div class="row mt-4">
                    <div class="col-md-6">
                        <div class="form-group d-flex">
                            <div class="select-wrap">
                                <div class="icon"><span class="ion-ios-arrow-down"></span></div>
                                <select name="" id="" class="form-control" style={{ width: 150 }}
                                    value={sizeId} onChange={(e) => handleSizeChange(e)}>
                                    {product?.sizeCollection?.map(size =>
                                        <option value={size.id} key={size.id}>{size.sizeName}</option>
                                    )}
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="w-100"></div>
                    <div class="input-group col-md-6 d-flex mb-3">
                        <span class="input-group-btn mr-2">
                            <button type="button" class="quantity-left-minus btn" data-type="minus" data-field="" onClick={() => handleMinusQuantity(quantity - 1)} >
                                <i class="ion-ios-remove"></i>
                            </button>
                        </span>
                        <input type="number" id="quantity" name="quantity" class="quantity form-control input-number"
                            value={quantity} min="1" max="100" onChange={(e) => handleInputsQuantity(e)} />
                        <span class="input-group-btn ml-2">
                            <button type="button" class="quantity-right-plus btn" data-type="plus" data-field="" onClick={() => setQuantity(quantity + 1)} >
                                <i class="ion-ios-add"></i>
                            </button>
                        </span>
                    </div>
                    <div class="w-100"></div>
                    <div class="col-md-12">
                        <p style={{ color: "#000" }}>{product.quantity} piece available</p>
                    </div>
                </div>
                <p><a class="btn btn-black py-3 px-5 mr-2" onClick={() => addToCart(product)}>Add to Cart</a><a onClick={handleCheckout} class="btn btn-primary py-3 px-5">Buy now</a></p>
                <Modal isOpen={isOpen} toggle={() => setOpen(!isOpen)} size='lg' style={{ top: '35%' }}>
                    <ModalHeader toggle={() => setOpen(!isOpen)}>
                        {message}
                    </ModalHeader>
                </Modal>
            </div>
        </>
    )
}

export default ProductInfo;