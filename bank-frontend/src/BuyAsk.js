import React, { useState } from 'react';

export default function BuyAsk() {
  const [code, setCode] = useState('');
  const [lastDays, setLastDays] = useState(0);
  const [diff, setDiff] = useState(0);

  const handleSubmit = async (e) => {
    e.preventDefault();
    var url = `http://localhost:8080/exchangerates/buyask/${code}/?N=${lastDays}`;
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
      setDiff(result);
    }
  };

  return (
    <>
        <h3>Get the major difference beetwen buy and ask bid in the last days</h3>
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
        <b>Major difference: {diff}</b>
    </>
  );
}
