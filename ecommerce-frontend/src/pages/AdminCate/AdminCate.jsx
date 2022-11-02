import * as React from 'react';
import { useState, useEffect } from 'react';
import Datatable from '../../components/Datatable/datatable';
import CateService from '../../services/CateService';

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
                    <div className='viewButton' ><a href="/" style={{color: 'blue'}}>View</a></div>
                    <div className='deleteButton'  onClick={() => blockCate(params.row.id)}>Delete</div>
                </div>
            )

        }
    }
];


const blockCate = (id) => {
    CateService.deleteCate(id).then((res => {
        console.log(res.data);
        // window.location.reload();
    }))
}
function AdminCate(props) {
  

    const [cates, setCates] = useState([]);

    useEffect(() => {
        fetchCates();
    }, [columns])

    const fetchCates = async () => {
        CateService.getAllCates().then((res => {
            setCates(res.data)
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
                    </div>
                </div>
            </section>
        </>
    );
}

export default AdminCate;