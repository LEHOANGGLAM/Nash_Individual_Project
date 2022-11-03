import React, { useEffect, useState } from 'react'
import StarRatings from 'react-star-ratings';
import { Button, Modal } from 'react-bootstrap';
import './index.css';
import RatingService from '../../services/RatingService';
import AuthService from '../../services/AuthService';

const ModalRating = ({ item, handleCloseModal, showModal, handleCommented }) => {
    const [rating, setRating] = useState(5);
    const [comment, setComment] = useState("");

    const handleChangeRating = (rating) => {
        setRating(rating);
    }
    const handleComment = (e) => {
        let { value } = e.target;
        setComment(value);
    }
    const handleReview = async () => {
        // console.log(rating);
        // console.log(comment);

        const user = AuthService.getCurrentUser();
        if (user) {
            let data = {
                userId: user.id,
                orderItemId: item.id,
                content: comment,
                rating: rating
            };
            await RatingService.addRating(data)
                .then((res) => {
                    setRating(5)
                    setComment('')

                    handleCommented()
                    handleCloseModal()
                 console.log("success")
                }, (err) => {
    
                    //console.log(err.response.data.message)
                })
        } else {
            //navigate("/login")
            window.location.pathname = "/login"
        }
    }



    return (
        <Modal show={showModal} onHide={handleCloseModal}>
            <Modal.Header closeButton>
                <Modal.Title>{item?.productId?.title}</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <div>
                    Your Rating
                    <StarRatings
                        rating={rating}
                        starRatedColor='#ffce3d'
                        numberOfStars={5}
                        starDimension="16px"
                        starSpacing="2px"
                        changeRating={handleChangeRating}
                    />
                </div>
                <div>
                    Write your feel about this product
                    <textarea cols="30" rows="10" onChange={handleComment} placeholder='I feel...' style={{ padding: 10 }}></textarea>
                
                </div>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={handleCloseModal}>
                    Back
                </Button>
                <Button variant="primary" onClick={handleReview}>
                    Submit
                </Button>
            </Modal.Footer>
        </Modal>

    );
}

export default ModalRating;