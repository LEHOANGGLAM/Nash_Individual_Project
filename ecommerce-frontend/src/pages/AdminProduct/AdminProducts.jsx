import * as React from 'react';
import { useState, useEffect } from 'react';
import currencyFormat from '../../components/Common/CurrencyFormat';
import Datatable from '../../components/Datatable/datatable';
import ProductService from '../../services/ProductService';
import { Link } from 'react-router-dom';
import { Button, Modal } from 'react-bootstrap';


function AdminProducts(props) {

    const columns = [
        { field: 'id', headerName: 'ID', width: 70 },
        {
            field: 'name', headerName: 'Name', width: 300,
            renderCell: (params) => {
                return (
                    <div className='cellWithImg'>
                        <img src={params.row.imageCollection[0]?.link} className='cellImg' alt="product image" />
                        {params.row.title}
                    </div>
                )
            }
        },
        { field: 'quantity', headerName: 'Stock', width: 100 },
        {
            field: 'price', headerName: 'Price', width: 200,
            renderCell: (params) => {
                return (
                    <div> {currencyFormat(params.row.price)} VND</div>
                )
            }
        },
        {
            field: 'active', headerName: 'Status', width: 100,
            renderCell: (params) => {
                return (
                    <div className={`cellWithStatus ${params.row.active ? "publish" : "draft"}`}>
                        {params.row.active ? "Publish" : "Draft"}
                    </div>
                )
            }
        },
        {
            field: 'action', headerName: 'Action', width: 200,
            renderCell: (params) => {
                return (
                    <div className='cellAction'>
                        <div className='viewButton'><Link to={`/admin-products-new${params.row.id}`} style={{ color: 'blue' }}>View</Link></div>
                        <div className='deleteButton' onClick={() => handleShowModal(params.row.id)}>Delete</div>
                    </div>
                )

            }
        }
    ];

    const [products, setProducts] = useState([]);

    const [isUpdated, setUpdate] = useState(false);
    const handleUpdate = () => {
        setUpdate(!isUpdated);
    }

    //handleModal
    const [idProductDelete, setIdProductDelete] = useState();
    const [showModal, setShowModal] = useState(false);
    const handleShowModal = (id) => {
        setIdProductDelete(id)
        setShowModal(true);
    }
    const handleCloseModal = () => { setShowModal(false); }

    useEffect(() => {
        fetchProducts();
    }, [isUpdated])

    const fetchProducts = async () => {
        ProductService.getAllProducts().then((res => {
            setProducts(res.data)
        }))
    }



    const deletePro = () => {
        ProductService.deleteProduct(idProductDelete).then((res => {
            console.log(res.data);
            handleUpdate();

            handleCloseModal()
        }))
    }

    return (
        <>
            <div class="hero-wrap hero-bread" style={{ padding: 20 }}>
            </div>

            <section class="ftco-section ftco-cart">
                <div class="container">
                    <h2 class="mb-3">Products Management</h2>
                    <div className="datatable">
                        <div className="datatableTitle">

                            <a href="/admin-products-new" className="link">
                                Add New
                            </a>
                        </div>
                        <Datatable data={products} col={columns} />
                        <Modal show={showModal} onHide={handleCloseModal} size="lg" backdrop='static' keyboard={false}>
                            <Modal.Header closeButton>
                                <Modal.Title>Confirm</Modal.Title>
                            </Modal.Header>
                            <Modal.Body>Do you sure to delete this product?</Modal.Body>
                            <Modal.Footer>

                                <Button variant="secondary" onClick={deletePro}>
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

export default AdminProducts;