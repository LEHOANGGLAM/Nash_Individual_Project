import React from 'react';
import { Button, Modal } from 'react-bootstrap';

const ModalYesNo = ({ title, handleCloseModal, showModal, item }) => {
  

    return (
        <Modal show={showModal} onHide={handleCloseModal} size="lg" backdrop='static' keyboard={false}>
            <Modal.Header closeButton>
                <Modal.Title>Confirm</Modal.Title>
            </Modal.Header>
            <Modal.Body>{title}</Modal.Body>
            <Modal.Footer>

                <Button variant="secondary" onClick={handleCloseModal}>
                    Yes
                </Button>
                <Button variant="secondary" onClick={handleCloseModal}>
                    No
                </Button>
            </Modal.Footer>
        </Modal>
//      <Modal isOpen={isOpenPopUp} toggle={() => setOpen(!isOpenPopUp)} size='lg' style={{ top: '35%' }}>
//      <ModalHeader toggle={() => setOpen(!isOpenPopUp)}>
//          Are you sure to delete this item?
//          <button style={{ margin: 5 }} onClick={() => handleDeleteCartItem(item.id)}>Yes</button>
//          <button style={{ margin: 5 }} onClick={() => setOpen(false)}>No</button>
//      </ModalHeader>
//  </Modal>
    );
}

export default ModalYesNo;