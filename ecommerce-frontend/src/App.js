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
import Overview from './pages/AdminOverview/Overview';
import AdminProducts from './pages/AdminProduct/AdminProducts';
import CreateProduct from "./pages/AdminAddProduct/CreateProduct";
import AdminUser from "./pages/AdminUser/AdminUser";
import CreateUser from "./pages/AdminAddUser/CreateUser";
import AdminCate from "./pages/AdminCate/AdminCate";
import CreateCate from "./pages/AdminAddCate/CreateCate";
import DisplayOrder from "./pages/DisplayOrder/DisplayOrder";

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

          <Route path="/myorders" element={<DisplayOrder />}></Route>
          {/* admin */}
          <Route path="/overview" element={<Overview />}></Route>
          <Route path="/admin-products" element={<AdminProducts />}></Route>
          <Route path="/admin-products-new" element={<CreateProduct />}></Route>

          <Route path="/admin-users" element={<AdminUser />}></Route>
          <Route path="/admin-users-new" element={<CreateUser />}></Route>
          <Route path="/admin-users-new:id" element={<CreateUser />}></Route>

          <Route path="/admin-categories" element={<AdminCate />}></Route>
          <Route path="/admin-categories-new" element={<CreateCate />}></Route>

          <Route path="*" element={<PageNotFound />}> </Route>
        </Routes>
        <Footer />
      </BrowserRouter>
    </div >
  );
}

export default App;
