import React, { useEffect, useRef, useState } from 'react'
import ProductService from '../../services/ProductService';
import HeadWrap from '../../components/HeadWrap/HeadWrap';
import Filter from '../../components/Product/Filter';
import Products from '../../components/Product/Products';
import Pagination from '../../components/Pagination';
import queryString from 'query-string';
import background from '../../images/searchicon.png'

ProductList.propTypes = {

}

ProductList.defaultProps = {

}


function ProductList(props) {
    const [products, setProducts] = useState([]);
    const [totalPage, setTotalPage] = useState();
    const typingTimeOutRef = useRef(null);
    const [filters, setFilters] = useState({
        page: 0,
        cateId: undefined,
        name: '',
        fromPrice: undefined,
        toPrice: undefined
    });

    useEffect(() => {
        fetchProducts(filters);
    }, [filters])

    const fetchProducts = async (filters) => {
        let predicates = queryString.stringify(filters);
        ProductService.getProductsByPredicates(predicates).then((res) => {
            setProducts(res.data.listResponse)
            setTotalPage(res.data.totalPage)
        })
        console.log(predicates);
    }

    const handleFiltersChange = (newPage, cateId, name, fromPrice, toPrice) => {
        setFilters({
            ...filters,
            page: newPage,
            cateId: cateId,
            name: name,
            fromPrice: fromPrice,
            toPrice: toPrice
        })
    }

    const handleSearchChange = e => {
        if (typingTimeOutRef.current) {
            clearTimeout(typingTimeOutRef.current);
        }
        typingTimeOutRef.current = setTimeout(() => {
            handleFiltersChange(0, filters.cateId,
                e.target.value, 
                filters.fromPrice,
                filters.toPrice)
        }, 500);
    }

    return (
        <>
            <HeadWrap />
            <section class="ftco-section bg-light">
                <div class="container">
                    <div class="row">
                        <Filter handleFiltersChange={handleFiltersChange} filters={filters} />
                        <div class="col-md-8 col-lg-10 order-md-last">
                            <input className='search' type="text" onChange={handleSearchChange} placeholder="Search" style={{ marginBottom: 20, backgroundImage: `url(${background})`}} />
                            
                            <Products products={products} />
                            <Pagination currentPage={filters.page} totalPage={totalPage} handleFilterPageChange={handleFiltersChange} />
                        </div>
                    </div>
                </div>
            </section>
        </>
    )
}


export default ProductList;

