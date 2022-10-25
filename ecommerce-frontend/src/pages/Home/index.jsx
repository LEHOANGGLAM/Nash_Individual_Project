
import SimpleSlider from '../../components/Home/SimpleSlider';
import ProductList from '../../components/Home/ProductList';
import MiddleBar from '../../components/Home/MiddleBar';
import ImageGallery from '../../components/Home/ImageGallery';


const Home = () => {
    return (
        <div>
            <SimpleSlider />
            <MiddleBar />
            <ProductList />
            <ImageGallery />
        </div>
    )
}

export default Home;