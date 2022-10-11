import logo from './logo.svg';
//import './App.css';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

import Header from './components/Header/Header';
import Footer from './components/Footer/Footer';
import Home from './components/Home/Home';
import LoginAndRegister from './components/Login&Register/LoginAndRegister';

function App() {
  return (
    <div className="App">
      <Router>
        <Header />
        <Switch>
          <Route exact path="/" component={Home}></Route>
          <Route path="/login" component={LoginAndRegister}></Route>
        </Switch>
        <Footer />
      </Router>

    </div>
  );
}

export default App;
