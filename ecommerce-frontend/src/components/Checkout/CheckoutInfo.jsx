import React, { useEffect, useState } from 'react'
import AuthService from '../../services/AuthService';
import OrderService from '../../services/OrderService';
import UserService from '../../services/UserService';
import currencyFormat from '../Common/CurrencyFormat';
import { Modal, ModalHeader } from 'reactstrap';

function CheckoutInfo() {
    const [currentUser, setUser] = useState([]);
    const [items, setItems] = useState([]);
    const [address, setAddress] = useState('');
    const [paymentMethod, setPaymentMethod] = useState("cod");
    const [totalPrice, setTotal] = useState(0);

    //check accept the terms and conditions
    const [isCheck, setCheck] = useState(false);
    //handle POPUP MODEL
    const [isOpen, setOpen] = useState(false);
    const [message, setMessage] = useState();
    useEffect(() => {
        fetchUserInfo();

        //get productItems
        let tempItems;
        if (localStorage.getItem("itemsCheckOut") !== null) {
            tempItems = JSON.parse(localStorage.getItem("itemsCheckOut"))
            setItems(tempItems);
        }

        if (tempItems) {
            const total = tempItems.reduce(
                (sum, value, index) => {
                    return sum + tempItems[index].price;
                },
                0
            );
            setTotal(total);
        }
    }, [])

    const fetchUserInfo = async () => {
        const user = AuthService.getCurrentUser();
        if(user){
            UserService.getUserById(user.id).then((res) => {
                setUser(res.data);
                setAddress(res.data.address)
            })
        }else{
            window.location.pathname = "/login"
        }
        
    }

    const createOrder = () => {
        if (currentUser) {
            if (isCheck) {
                //map to OrderItemResquestDTo
                let tempList = [];
                for (let i = 0; i < items.length; i++) {
                    tempList.push({
                        quantity: items[i]?.quantity,
                        productId: items[i]?.productId.id,
                        sizeId: items[i]?.sizeId.id,
                    });
                }

                //create orderInput
                const order = {
                    address: address,
                    paymentMethod: paymentMethod,
                    orderItems: tempList

                }
                console.log(order);
                OrderService.createOrder(order, currentUser.id).then((res) => {
                    setMessage("Order Sucessful!")
                }, (err) => {
                    setMessage(err.message);
                })

                localStorage.removeItem("itemsCheckOut");

                //handle POPUP MODEL
                setOpen(true);

            } else {
                //handle POPUP MODEL
                setOpen(true);
                setMessage("Please accept the terms and conditions")
            }
        } 
    }

    const handleAddressChange = (e) => {
        setAddress(e.target.value)
    }
    const handlePayMethod = (event) => {
        setPaymentMethod(event.target.value);
        console.log(event.target.value);
    }

    return (
        <>
            <section class="ftco-section">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-xl-10 ftco-animate fadeInUp ftco-animated">
                            <form action="#" class="billing-form">
                                <h3 class="mb-4 billing-heading">Billing Details</h3>
                                <div class="row align-items-end">
                                    <div class="col-md-6">
                                        <div class="form-group" style={{ color: 'black' }}>
                                            <label for="firstname">Firt Name</label>
                                            <input type="text" class="form-control" value={currentUser.firstName} disabled />
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="lastname">Last Name</label>
                                            <input type="text" class="form-control" value={currentUser.lastName} disabled />
                                        </div>
                                    </div>

                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label for="streetaddress">Street Address</label>
                                            <input type="text" class="form-control" value={address} onChange={handleAddressChange} />
                                        </div>
                                    </div>


                                    <div class="w-100"></div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="phone">Phone</label>
                                            <input type="text" class="form-control" value={currentUser.mobile} disabled />
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="emailaddress">Email Address</label>
                                            <input type="text" class="form-control" value={currentUser.email} disabled />
                                        </div>
                                    </div>

                                </div>
                            </form>


                            {items.length > 0 ?
                                <div class="row mt-5 pt-3 d-flex">
                                    <div class="col-md-6 d-flex">
                                        <div class="cart-detail cart-total bg-light p-3 p-md-4">
                                            <h3 class="billing-heading mb-4">Cart Total</h3>
                                            {items.map((item, index) =>
                                                <p class="d-flex" key={index}>
                                                    <span>{item.productId.title} - Size: {item.sizeId.sizeName} Quantity: {item.quantity}</span>
                                                    <span>{currencyFormat(item.price)} VND</span>
                                                </p>
                                            )}

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
                                    </div>
                                    <div class="col-md-6">
                                        <div class="cart-detail bg-light p-3 p-md-4">
                                            <h3 class="billing-heading mb-4">Payment Method</h3>
                                            <div onChange={handlePayMethod}>
                                                <div class="form-group">
                                                    <div class="col-md-12">
                                                        <div class="radio">
                                                            <label><input type="radio" name="optradio" class="mr-2" value="cod" checked /> COD</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-md-12">
                                                        <div class="radio">
                                                            <label><input type="radio" name="optradio" class="mr-2" value="banking" /> Banking</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-md-12">
                                                    <div class="checkbox">
                                                        <label><input type="checkbox" value={isCheck} onClick={() => setCheck(!isCheck)} class="mr-2" /> I have read and accept the terms and conditions</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <p><a onClick={createOrder} class="btn btn-primary py-3 px-4">Place an order</a></p>
                                        </div>
                                    </div>
                                </div>
                                : <h1 style={{ marginLeft: '40%' }}> NO ITEMS</h1>
                            }

                        </div>
                    </div>
                </div>
            </section>
            <Modal isOpen={isOpen} toggle={() => setOpen(!isOpen)} size='lg' style={{ top: '35%' }}>
                <ModalHeader toggle={() => setOpen(!isOpen)}>
                    {message}
                </ModalHeader>
            </Modal>
        </>
    )
}

export default CheckoutInfo;