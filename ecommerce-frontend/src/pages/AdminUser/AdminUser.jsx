import * as React from 'react';
import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import Datatable from '../../components/Datatable/datatable';
import UserService from '../../services/UserService';

const columns = [
    { field: 'id', headerName: 'ID', width: 70 },
    { field: 'firstName', headerName: 'First Name', width: 200 },
    { field: 'lastName', headerName: 'Last Name', width: 150 },
    { field: 'mobile', headerName: 'Phone', width: 100 },
    { field: 'email', headerName: 'Email', width: 200 },
    {
        field: 'active', headerName: 'Status', width: 100,
        renderCell: (params) => {
            return (
                <div className={`cellWithStatus ${params.row.active ? "publish" : "draft"}`}>
                    {params.row.active ? "Active" : "Block"}
                </div>
            )
        }
    },
    {
        field: 'action', headerName: 'Action', width: 200,
        renderCell: (params) => {
            return (
                <div className='cellAction'>
                    {/* <div className='viewButton' ><a href={`/admin-users-new-${params.row.id}`} style={{color: 'blue'}}>View</a></div> */}
                    <div className='deleteButton' onClick={() => blockUser(params.row.id)}>Block</div>
                </div>
            )

        }
    }
];

const blockUser = (id) => {
    UserService.deleteUser(id).then((res => {
        console.log(res.data);
        // window.location.reload();
    }))
}
function AdminUser(props) {

    const [users, setUsers] = useState([]);

    useEffect(() => {
        fetchUsers();
    }, [columns])

    const fetchUsers = async () => {
        UserService.getAllUsers().then((res => {
            setUsers(res.data)
        }))
    }


    return (
        <>
            <div class="hero-wrap hero-bread" style={{ padding: 20 }}>
            </div>

            <section class="ftco-section ftco-cart">
                <div class="container">
                    <h2 class="mb-3">Users Management</h2>
                    <div className="datatable">
                        <div className="datatableTitle">

                            {/* <Link to="new"  className="link"> Add admin account</Link> */}

                            <a href="/new" className="link">
                                Add admin account
                            </a>
                        </div>
                        <Datatable data={users} col={columns} />
                    </div>
                </div>
            </section>
        </>
    );
}

export default AdminUser;