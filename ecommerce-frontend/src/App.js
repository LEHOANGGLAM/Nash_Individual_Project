//import './App.css';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

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

function App() {
  return (
    <div className="App">
      <Router>
        <Header />
        <Switch>
          <Route exact path="/" component={Home}></Route>
          <Route path="/login" component={LoginAndRegister}></Route>

          <Route path="/products" component={ProductList}></Route>
          <Route path="/product-:productId" component={ProductDetail}></Route>

          <Route path="/cart" component={Cart}></Route>
          <Route path="/checkout" component={CheckOut}></Route>


          <Route path="/overview" component={Overview}></Route>

          <Route path="*"> <PageNotFound /></Route>
        </Switch>
        <Footer />
      </Router>

    </div>
  );
}

export default App;
