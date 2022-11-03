import * as React from 'react';
import { useState, useEffect } from 'react';
import currencyFormat from '../../components/Common/CurrencyFormat';
import Datatable from '../../components/Datatable/datatable';
import ProductService from '../../services/ProductService';


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
        renderCell: () => {
            return (
                <div className='cellAction'>
                    <div className='viewButton'>View</div>
                    <div className='deleteButton'>Delete</div>
                </div>
            )

        }
    }
];

function AdminProducts(props) {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        fetchProducts();
    }, [])

    const fetchProducts = async () => {
        ProductService.getAllProducts().then((res => {
            setProducts(res.data)
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
                  
                    </div>
                </div>
            </section>
        </>
    );
}

export default AdminProducts;