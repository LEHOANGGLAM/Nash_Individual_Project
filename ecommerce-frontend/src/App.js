import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Header from './common/Header';
import TopBar from './common/TopBar';
import Slider from './common/Slider';

function App() {
  return (
    <div className="App">
      <Router>
        <TopBar />
        <Header />
        <Slider />
        <div>


        </div>


      </Router>

    </div>
  );
}

export default App;
