import React, { useEffect, useState } from 'react'
import ProductService from '../../services/ProductService';
import HeadWrap from '../../components/HeadWrap/HeadWrap';
import Filter from '../../components/ProductList/Filter';
import Products from '../../components/ProductList/Products';
import Pagination from '../../components/Pagination/Pagination';
import queryString from 'query-string';


ProductList.propTypes = {
    
}

ProductList.defaultProps = {
   
}


function ProductList(props) {
   // const { products, totalPage, currentPage, filters } = props;
    const [products, setProducts] =  useState([]);
    const [totalPage, setTotalPage] =  useState();
    const [currentPage, setCurrentPage] =  useState();
    const [filters, setFilters] =  useState([]);

    useEffect(() => {
        fetchProducts(filters);
    }, [products])
  
    const fetchProducts = async (filters) => {
        let predicates = queryString.stringify(filters);
        ProductService.getAllProducts(predicates).then((res) => {
            setProducts(res.data.listResponse)
            setTotalPage(res.data.totalPage )
        })
    }

    return (
        <>
            <HeadWrap />
            <section class="ftco-section bg-light">
                <div class="container">
                    <div class="row">
                        <Filter />
                        <div class="col-md-8 col-lg-10 order-md-last">
                            <Products products={products} />
                            <Pagination totalPage={totalPage}/>
                        </div>
                    </div>
                </div>
            </section>
        </>
    )
}


export default ProductList;

