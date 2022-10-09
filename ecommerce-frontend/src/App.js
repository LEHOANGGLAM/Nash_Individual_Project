import logo from './logo.svg';
//import './App.css';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Header from './components/Header/Header';
import TopBar from './components/Header/TopBar';
import Slider from './components/Header/Slider';
import ProductList from './common/ProductList';
import MiddleBar from './components/Home/MiddleBar';
import Login from './components/Login&Register/Login';

function App() {
  return (
    <div className="App">
      <Router>
        <TopBar />
        <Header />
       
        <Slider />
       
        <MiddleBar />
        <ProductList />
        <div>
          <Switch>


            <Route path="/login" component={Login}></Route>

          </Switch>
        </div>
      </Router>

    </div>
  );
}

export default App;
