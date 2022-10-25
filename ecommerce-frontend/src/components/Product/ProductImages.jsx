import OwlCarousel from 'react-owl-carousel';
import PropTypes from 'prop-types'


function ProductImages(props) {
    const { images } = props;

    return (
        <>
            <div class="col-lg-6 mb-5 ftco-animate fadeInUp ftco-animated">
                <OwlCarousel className='owl-stage' dots items={1} autoplay autoplayTimeout={3000}>
                    {images?.map(item =>
                        <a href={item.link} key={item.id} class="image-popup prod-img-bg" ><img src={item.link} class="img-fluid" alt="Colorlib Template" /></a>
                    )}
                </OwlCarousel>
            </div>
        </>
    )
}

export default ProductImages;