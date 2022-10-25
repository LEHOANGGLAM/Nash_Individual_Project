//import './App.css';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

import Header from './components//Header';
import Footer from './components/Footer';
import Home from './pages/Home';
import LoginAndRegister from './pages/Login&Register/LoginAndRegister';
import PageNotFound from './pages/Error/PageNotFound';
import ProductList from './pages/ProductList';
import ProductDetail from './pages/ProductDetail';

function App() {
  return (
    <div className="App">
      <Router>
        <Header />
        <Switch>
          <Route exact path="/"><Home /></Route>
          <Route path="/login"> <LoginAndRegister /></Route>

          <Route path="/products"><ProductList /></Route>
          <Route path="/product-:productId"><ProductDetail /></Route>

          <Route path="*"> <PageNotFound /></Route>
        </Switch>
        <Footer />
      </Router>

    </div>
  );
}

export default App;
