import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import HeadWrap from '../../components/HeadWrap/HeadWrap';
import ProductInfo from '../../components/Product/ProductInfo';
import ProductService from '../../services/ProductService';
import ProductImages from '../../components/Product/ProductImages';
import PageNotFound from '../Error/PageNotFound';
import Rating from '../../components/Product/Rating';


ProductDetail.propTypes = {

}

ProductDetail.defaultProps = {
    
}


function ProductDetail(props) {
    const { productId } = useParams();
    const [product, setProduct] = useState({});
    const [isNotFound, setIsNotFound] = useState(false);
    const [cateId, setCateId] = useState();

    const fetchProduct = async (productId) => {
        await ProductService.getProductById(productId).then((res) => {
            setProduct(res.data)
            setCateId(res.data.categoryCollection[0]?.id) //To take similar product by cateID
        },
            (err) => {
                setIsNotFound(true)
            }
        )
    }

    useEffect(() => {
        fetchProduct(productId);
    }, [])

    return (
        <>
            {
                !isNotFound ?
                    <>
                        <HeadWrap />
                        <section class="ftco-section">
                            <div class="container">
                                <div class="row" >
                                    {
                                        Object.keys(product).length !== 0 ?
                                            <>
                                                <ProductImages images={product?.imageCollection} />
                                                <ProductInfo product={product} />
                                                <Rating product={product} cateId={cateId}/>
                                            </>
                                            : <></>
                                    }
                                </div>
                            </div>
                        </section>
                    </>
                    :
                    <PageNotFound />
            }
        </>
    )
}


export default ProductDetail;

