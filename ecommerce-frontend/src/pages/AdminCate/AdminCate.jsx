import * as React from 'react';
import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import Datatable from '../../components/Datatable/datatable';
import CateService from '../../services/CateService';
import { Button, Modal } from 'react-bootstrap';

function AdminCate(props) {

    const columns = [
        { field: 'id', headerName: 'ID', width: 70 },
        { field: 'title', headerName: 'Name', width: 450 },
        {
            field: 'active', headerName: 'Status', width: 200,
            renderCell: (params) => {
                return (
                    <div className={`cellWithStatus ${params.row.active ? "publish" : "draft"}`}>
                        {params.row.active ? "Active" : "Block"}
                    </div>
                )
            }
        },
        {
            field: 'action', headerName: 'Action', width: 300,
            renderCell: (params) => {
                return (
                    <div className='cellAction'>
                        <div className='viewButton' ><Link to={`/admin-categories-new${params.row.id}`}>View</Link></div>
                        <div className='deleteButton' onClick={() => handleShowModal(params.row.id)}>Delete</div>
                    </div>
                )

            }
        }
    ];

    const [cates, setCates] = useState([]);
    const [isUpdated, setUpdate] = useState(false);
    const handleUpdate = () => {
        setUpdate(!isUpdated);
    }

    //handleModal
    const [idCateDelete, setIdCateDelete] = useState();
    const [showModal, setShowModal] = useState(false);
    const handleShowModal = (id) => {
        setIdCateDelete(id)
        setShowModal(true);
    }
    const handleCloseModal = () => { setShowModal(false); }

    useEffect(() => {
        fetchCates();
    }, [isUpdated])

    const fetchCates = async () => {
        CateService.getAllCates().then((res => {
            setCates(res.data)
        }))
    }


    const deleteCate = () => {
        CateService.deleteCate(idCateDelete).then((res => {
            console.log(res.data);
            handleUpdate()
            handleCloseModal()
        }))
    }
    return (
        <>
            <div class="hero-wrap hero-bread" style={{ padding: 20 }}>
            </div>

            <section class="ftco-section ftco-cart">
                <div class="container">
                    <h2 class="mb-3">Categories Management</h2>
                    <div className="datatable">
                        <div className="datatableTitle">

                            <a href="/admin-categories-new" className="link">
                                Add category
                            </a>
                        </div>
                        <Datatable data={cates} col={columns} />
                        <Modal show={showModal} onHide={handleCloseModal} size="lg" backdrop='static' keyboard={false}>
                            <Modal.Header closeButton>
                                <Modal.Title>Confirm</Modal.Title>
                            </Modal.Header>
                            <Modal.Body>Do you sure to delete this category?</Modal.Body>
                            <Modal.Footer>

                                <Button variant="secondary" onClick={deleteCate}>
                                    Yes
                                </Button>
                                <Button variant="secondary" onClick={handleCloseModal}>
                                    No
                                </Button>
                            </Modal.Footer>
                        </Modal>
                    </div>
                </div>
            </section>
        </>
    );
}

export default AdminCate;