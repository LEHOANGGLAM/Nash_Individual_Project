import React from 'react';
import { DataGrid } from '@mui/x-data-grid';
import PropTypes from 'prop-types'
import './datatable.scss';


Datatable.propTypes = {
    data: PropTypes.array,
    col: PropTypes.array,
}

Datatable.defaultProps = {
    data: [],
    col: []
}

function Datatable(props) {

    const { data, col } = props;

    return (
        
            <DataGrid
                className="datagrid"
                rows={data}
                columns={col}
                pageSize={9}
                rowsPerPageOptions={[9]}
                checkboxSelection
            />

    );
}

export default Datatable;