import React from 'react';
import { Button, Modal } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import './index.css';

const ModalConfirm = ({ title, handleCloseModal, showModal, link }) => {
    const navigate = useNavigate();

    return (
        <Modal show={showModal} onHide={handleCloseModal} size="lg" backdrop='static' keyboard={false}>
            <Modal.Header closeButton>
                <Modal.Title>Confirm</Modal.Title>
            </Modal.Header>
            <Modal.Body>{title}</Modal.Body>
            <Modal.Footer>

                {
                    link ? <Button variant="secondary" onClick={()=>navigate(link)}>
                        Back
                    </Button>
                        : <Button variant="secondary" onClick={handleCloseModal}>
                            Back
                        </Button>
                }
            </Modal.Footer>
        </Modal>

    );
}

export default ModalConfirm;