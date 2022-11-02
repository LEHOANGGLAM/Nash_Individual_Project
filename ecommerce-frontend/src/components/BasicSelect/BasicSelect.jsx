import * as React from 'react';
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import PropTypes from 'prop-types'

BasicSelect.propTypes = {
    data: PropTypes.array,
    handleDataChange: PropTypes.func,
    currentCate: PropTypes.object
}

BasicSelect.defaultProps = {
    data: [],
    handleDataChange: null,
    currentCate: null,
}


export default function BasicSelect(props) {
    const { data, handleDataChange, currentCate } = props

    const handleChange = (event) => {
        if (handleDataChange) {
            handleDataChange(event.target.value)
        }
    };

    return (
        <Box sx={{ minWidth: 120 }}>
            <FormControl fullWidth>
                <InputLabel id="demo-simple-select-label">Choose</InputLabel>
                <Select
                    labelId="demo-simple-select-label"
                    id="demo-simple-select"
                    value={currentCate}
                    label="Age"
                    onChange={handleChange}
                >
                    {data?.map((item, index) =>
                        <MenuItem value={item.id}>{item.title}</MenuItem>
                    )}

                </Select>
            </FormControl>
        </Box>
    );
}