import React from 'react';
import './style.css';
import ExchangeRate from './ExchangeRate.js';
import MinMax from './MinMax';
import BuyAsk from './BuyAsk';

export default function App() {
  return (
    <>
      <h1>Exchange Rate Service</h1>
      <p id = "powered">powered by NBP</p>
      <div className="grid-container">
        <div className="grid-item">
          <ExchangeRate />
        </div>
        <div className="grid-item">
          <MinMax />
        </div>
        <div className="grid-item">
          <BuyAsk />
        </div>
      </div>
      <footer className="footer">
                <p>Copyright &copy; 2023, Szymon Brodziński</p>
      </footer>
    </>
  )
}
