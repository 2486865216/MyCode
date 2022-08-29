import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
//记录页面性能
import reportWebVitals from './reportWebVitals';

ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);
reportWebVitals();
