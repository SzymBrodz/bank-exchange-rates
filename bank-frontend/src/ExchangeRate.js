import React, { useState } from 'react';

export default function ExchangeRate() {
  const [code, setCode] = useState('');
  const [date, setDate] = useState('');
  const [exchangeRate, setExchangeRate] = useState(0.0);

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log(code);
    console.log(date);
    var url = `http://localhost:8080/exchangerates/${code}/${date}/`;
    console.log(url);
    const response = await fetch(url, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    });
    if (!response.ok) {
      console.log('Error:', response.statusText);
    } else {
      const result = await response.json();
      setExchangeRate(result);
    }
  };

  return (
    <>
        <h3>Get average exchange rate on a specific date</h3>
        <form onSubmit={handleSubmit}>
            <label>
            Country code:
            <input
                type="text"
                value={code}
                onChange={(e) => setCode(e.target.value)}
            />
            </label>
            <br />
            <br />
            <label>
            Date:
            <input
                type="date"
                value={date}
                onChange={(e) => setDate(e.target.value)}
            />
            </label>
            <br />
            <br />
            <button type="submit">Get</button>
        </form>
        <br/>
        <b>Exchange Rate: {exchangeRate}</b>
    </>
  );
}
