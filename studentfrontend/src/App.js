
import './App.css';
import AppBar from './components/appbar'
import Tasks from './components/popform';
import Edit from './components/Edit';
import Showcard from './components/showcard'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'

function App() {
  
  return (
    <Router>
      <AppBar />
    <Routes>
      {/* Define the routes */}
      
      <Route path="/" element={<Showcard/>} />
      <Route path="/add-task" element={<Tasks />} />
      <Route path='/edit' element={<Edit/>}/>
    </Routes>
    
  </Router>
    
  );
}

export default App;
