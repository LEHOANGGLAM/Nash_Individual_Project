import PropTypes from 'prop-types'
import StarRatings from 'react-star-ratings';
import CartService from '../../services/CartService';
import currencyFormat from '../Common/CurrencyFormat';

ProductInfo.propTypes = {
    product: PropTypes.object,
}

ProductInfo.defaultProps = {
    product: null,
}


function ProductInfo(props) {
    const { product } = props;

    const addToCart = (item) => {
        const cartItem = {
            // quantity: e.target.value,
            // productId: productId,
            // sizeId: sizeId,
            // userId: userId
        }
        CartService.addItemIntoCart(item).then((res) => {

        }, (err) => {

        }
        )
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

                <p>{product.desciption}</p>

                <div class="row mt-4">
                    <div class="col-md-6">
                        <div class="form-group d-flex">
                            <div class="select-wrap">
                                <div class="icon"><span class="ion-ios-arrow-down"></span></div>
                                <select name="" id="" class="form-control" style={{ width: 150 }}>
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
                            <button type="button" class="quantity-left-minus btn" data-type="minus" data-field="">
                                <i class="ion-ios-remove"></i>
                            </button>
                        </span>
                        <input type="number" id="quantity" name="quantity" class="quantity form-control input-number" defaultValue={1} min="1" max="100" />
                        <span class="input-group-btn ml-2">
                            <button type="button" class="quantity-right-plus btn" data-type="plus" data-field="">
                                <i class="ion-ios-add"></i>
                            </button>
                        </span>
                    </div>
                    <div class="w-100"></div>
                    <div class="col-md-12">
                        <p style={{ color: "#000" }}>{product.quantity} piece available</p>
                    </div>
                </div>
                <p><a class="btn btn-black py-3 px-5 mr-2" onClick={()=>addToCart}>Add to Cart</a><a href="/checkout" class="btn btn-primary py-3 px-5">Buy now</a></p>
            </div>
        </>
    )
}

export default ProductInfo;