import React, { useState, useEffect, useRef } from 'react';
import PropTypes from 'prop-types'
import CateService from '../../services/CateService';
import TextField from '@mui/material/TextField';

Filter.propTypes = {
    filters: PropTypes.object,
    handleFiltersChange: PropTypes.func,
}

Filter.defaultProps = {
    filters: null,
    handleFiltersChange: null,
}

function Filter(props) {
    const [categories, setCategories] = useState([]);
    const { filters, handleFiltersChange } = props;
    const [inputs, setInputs] = useState({});
    const [errors, setErrors] = useState({
        fromPrice: '',
        toPrice: ''
    });
    const typingTimeOutRef = useRef(null);

    useEffect(() => {
        fetchCategories();
    }, [])

    useEffect(() => {
        if (typingTimeOutRef.current) {
            clearTimeout(typingTimeOutRef.current);
        }
        typingTimeOutRef.current = setTimeout(() => {
            // console.log(inputs.fromPrice);
            if (handleFiltersChange) {
                handleFiltersChange(0, filters.cateId,
                    filters.name,
                    inputs.fromPrice,
                    inputs.toPrice)
            }
        }, 500);
    }, [inputs.fromPrice, inputs.toPrice])

    const fetchCategories = async () => {
        CateService.getAllCates().then((res) => {
            setCategories(res.data)
        })
    }

    const handleFilters = (newCateId) => {
        if (handleFiltersChange) {
            handleFiltersChange(0, newCateId, filters.name, filters.fromPrice, filters.toPrice)
        }
    }

    const handleInputsChange = e => {
        handleValidation()
        setInputs({
            ...inputs,
            [e.target.name]: e.target.value === '' ? undefined : e.target.value,
        });
    }

    const handleValidation = () => {
        let isValid = true;
        if (Number(inputs.fromPrice) >= Number(inputs.toPrice)) {
            setErrors({
                ...errors,
                fromPrice: "Must smaller than ToPrice",
                toPrice: "Must greater than fromPrice"
            });

            isValid = false;
            return isValid;
        } else {
            setErrors({
                ...errors,
                fromPrice: "",
                toPrice: ""
            });
        }
        return isValid;
    }

    return (
        <div class="col-md-4 col-lg-2">
            <div class="sidebar">
                <div class="sidebar-box-2">
                    <h2 class="heading">Categories</h2>
                    <div class="panel-heading" style={{ marginLeft: 10 }}>
                        <ul>
                            <li style={{ cursor: "pointer" }}><a onClick={() => handleFilters(undefined)}
                                style={filters.cateId === undefined ? { color: "brown", fontWeight: 'bold', fontSize: 15 } : {}} >
                                All Categries</a></li>
                            {
                                categories.map((cate, index) =>
                                    <li key={index} style={{ cursor: "pointer" }}><a
                                        onClick={() => handleFilters(cate.id)}
                                        style={filters.cateId === cate.id ? { color: "brown", fontWeight: 'bold', fontSize: 15 } : {}}
                                    >{cate.title}</a></li>
                                )
                            }
                        </ul>
                    </div>
                </div>
                <div class="sidebar-box-2">
                    <h2 class="heading">Price Range</h2>
                    <form method="post" class="colorlib-form-2">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="guests">Price from:</label>
                                    <TextField type="number" id="filled-basic" label="" variant="filled"
                                        name='fromPrice' value={Number(inputs.fromPrice).toString() || 0} onChange={handleInputsChange}
                                    />
                                    {errors.fromPrice && (
                                        <span style={{ color: "red", fontSize: 12 }} role="alert">
                                            {errors.fromPrice}
                                        </span>
                                    )}
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="guests">Price to:</label>
                                    <TextField type="number" id="filled-basic" label="" variant="filled" 
                                        name='toPrice' value={Number(inputs.toPrice).toString() || 0} onChange={handleInputsChange}
                                    />
                                     {errors.toPrice && (
                                        <span style={{ color: "red", fontSize: 12 }} role="alert">
                                            {errors.toPrice}
                                        </span>
                                    )}
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    )
}


export default Filter;