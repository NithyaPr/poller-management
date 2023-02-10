//import React from 'react';
import './App.css';
import './index.css';
import { openNav, closeNav } from './home';
import './services/api/services';
import { SearchServices } from './services/components/SearchServices';

function App() {
  return (
    <div>
      <div id="mySidenav" className="sidenav">
        <button onClick={closeNav} className="closebtn">&times;</button>
        <button onClick={closeNav} >About</button>

        <button style={{fontSize: 15}} onClick={closeNav} >Services</button>

        <button style={{fontSize: 15}} onClick={closeNav} >Clients</button>

        <button style={{fontSize: 15}} onClick={closeNav} >Contact</button>

      </div>
      <button style={{fontSize: 30}}
       onClick={openNav} >&#9776; Poller Management</button>
      <SearchServices></SearchServices>
    </div>
  );
}

export default App;
