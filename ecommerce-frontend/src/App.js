//import './App.css';
import {
  BrowserRouter,
  Routes,
  Route,
  Outlet,
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
import AdminPrivateRoute from "./routes/AdminPrivateRoute";
import UserPrivateRoute from "./routes/UserPrivateRoute";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Header />
        <Routes>

          <Route path="/login" element={<LoginAndRegister />}></Route>
          <Route path="/" element={<Home />}></Route>

          <Route path="/products" element={<ProductList />}></Route>
          <Route path="/product-:productId" element={<ProductDetail />}></Route>

          {/* user */}
          <Route path="/" element={
            <UserPrivateRoute>
              <Outlet />
            </UserPrivateRoute>
          }>

            <Route path="/cart" element={<Cart />}></Route>
            <Route path="/checkout" element={<CheckOut />}></Route>
            <Route path="/myorders" element={<DisplayOrder />}></Route>

          </Route>



          {/* admin */}
          <Route path="/admin/overview" element={<Overview />}></Route>
          {/* loi css cua template nen k dung auth như user đuọc */}
          <Route path="/admin-products" element={
            <AdminPrivateRoute>
              <AdminProducts />
            </AdminPrivateRoute>}>
          </Route>
          <Route path="/admin-products-new" element={
            <AdminPrivateRoute>
              <CreateProduct />
            </AdminPrivateRoute>}>
          </Route>
          <Route path="/admin-products-new:id" element={<CreateProduct />}></Route>


          <Route path="/admin-users" element={
            <AdminPrivateRoute>
              <AdminUser />
            </AdminPrivateRoute>}>
          </Route>
          <Route path="/admin-users-new" element={
            <AdminPrivateRoute>
              <CreateUser />
            </AdminPrivateRoute>}>
          </Route>

          {/* tuong tu */}
          <Route path="/admin-categories" element={<AdminCate />}></Route>
          <Route path="/admin-categories-new" element={<CreateCate />}></Route>
          <Route path="/admin-categories-new:id" element={<CreateCate />}></Route>


          <Route path="*" element={<PageNotFound />}> </Route>
        </Routes>
        <Footer />
      </BrowserRouter>
    </div >
  );
}

export default App;
