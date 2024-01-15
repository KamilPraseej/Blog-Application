import React, { useState } from 'react';
import axios from 'axios';
import './CreateNewBlog.css';
import { jwtDecode } from 'jwt-decode';


function HelpDesk() {
  const [helpDeskText, setHelpDeskText] = useState('');

  const handleChange = (e) => {
    setHelpDeskText(e.target.value);
  };

  const handleSubmit = () => {
    const email = jwtDecode(localStorage.getItem("bearer-token")).email;

    axios.post(`http://localhost:8181/api/v1/blogs/helpdesk/${encodeURIComponent(email)}`, { message: helpDeskText })
      .then((response) => {
        alert('Help desk message submitted successfully:', response.data);
        setHelpDeskText('');
      })
      .catch((error) => {
        console.error('Error submitting help desk text:', error);
      });
  };

  return (
    <div className='helpdesk' >
      <h1>Help Desk</h1>
      <textarea
        placeholder="Type your help desk message here..."
        value={helpDeskText}
        onChange={handleChange}
        rows="10"
      />
      <button onClick={handleSubmit}>Submit</button>
    </div>
  );
}

export default HelpDesk;
