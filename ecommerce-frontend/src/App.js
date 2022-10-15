import logo from './logo.svg';
//import './App.css';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

import Header from './components/Share/Header/Header';
import Footer from './components/Share/Footer/Footer';
import Home from './components/Home/Home';
import LoginAndRegister from './components/Share/Login&Register/LoginAndRegister';
import ShoppingList from './components/ShoppingList/ShoppingList'

function App() {
  return (
    <div className="App">
      <Router>
        <Header />
        <Switch>
          <Route exact path="/" component={Home}></Route>
          <Route path="/login" component={LoginAndRegister}></Route>

          <Route path="/shoppinglist" component={ShoppingList}></Route>
        </Switch>
        <Footer />
      </Router>

    </div>
  );
}

export default App;
