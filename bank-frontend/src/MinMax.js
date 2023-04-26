import React, { useState } from 'react';

export default function MinMax() {
  const [code, setCode] = useState('');
  const [lastDays, setLastDays] = useState(0);
  const [min, setMin] = useState(0);
  const [max, setMax] = useState(0);


  const handleSubmit = async (e) => {
    e.preventDefault();
    var url = `http://localhost:8080/exchangerates/${code}/?N=${lastDays}`;
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
      setMin(result.min);
      setMax(result.max);
    }
  };

  return (
    <>
        <h3>Get maximum and minimum average exchange rate in the last days</h3>
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
          Number of days:
          <input
            type="number"
            value={lastDays}
            onChange={(e) => setLastDays(e.target.value)}
          />
        </label>
        <br />
        <br />
        <button type="submit">Get</button>
      </form>
      <br/>
      <b>Max exchange rate value: {max}</b>
      <br/>
      <b>Min exchange rate value: {min}</b>
    </>
  );
}
