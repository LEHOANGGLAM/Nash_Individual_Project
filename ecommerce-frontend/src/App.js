//import './App.css';
import {
  BrowserRouter,
  Routes,
  Route,
} from "react-router-dom";

import Header from './components//Header';
import Footer from './components/Footer';
import Home from './pages/Home';
import LoginAndRegister from './pages/Login&Register/LoginAndRegister';
import PageNotFound from './pages/Error/PageNotFound';
import ProductList from './pages/ProductList';
import ProductDetail from './pages/ProductDetail';
import Cart from './pages/Cart';
import CheckOut from './pages/CheckOut';
import Overview from './pages/Admin/Overview';
import AdminProducts from './pages/Admin/AdminProducts';
import CreateProduct from "./pages/Admin/CreateProduct";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Header />
        <Routes>

          <Route path="/" element={<Home />}></Route>
          <Route path="/login" element={<LoginAndRegister />}></Route>

          <Route path="/products" element={<ProductList />}></Route>
          <Route path="/product-:productId" element={<ProductDetail />}></Route>

          <Route path="/cart" element={<Cart />}></Route>
          <Route path="/checkout" element={<CheckOut />}></Route>

          {/* admin */}
          <Route path="/overview" element={<Overview />}></Route>
          <Route path="/admin-products" element={<AdminProducts />}></Route>
          <Route path="/admin-products/new" element={<CreateProduct />}></Route>

          <Route path="*" element={<PageNotFound />}> </Route>
        </Routes>
        <Footer />
      </BrowserRouter>
    </div >
  );
}

export default App;
