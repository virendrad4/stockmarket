import StocksList from './Component/StocksList';
import AddStock from './Component/AddStock';
import EditStock from './Component/EditStock';
import NavBar from './Component/NavBar';
import NotFound from './Component/NotFound';
import { BrowserRouter, Route, Switch } from 'react-router-dom';

function App() {
  return (
    <BrowserRouter>
      <NavBar />
      <Switch>

        <Route exact path="/" component={StocksList} />
        <Route exact path="/add" component={AddStock} />
        <Route exact path="/edit/:id" component={EditStock} />
        <Route component={NotFound} />
      </Switch>
    </BrowserRouter>
  );
}

export default App;
