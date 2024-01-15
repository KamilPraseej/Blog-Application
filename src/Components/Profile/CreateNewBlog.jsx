import React, { useState } from 'react';
import axios from 'axios';
import './CreateNewBlog.css';
import { jwtDecode } from 'jwt-decode';


function CreateNewBlog() {
  const [blogData, setBlogData] = useState({
    title: '',
    author: '',
    content: '',
    date: '',
    imageUrl: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setBlogData({
      ...blogData,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const email = jwtDecode(localStorage.getItem("bearer-token")).email;

    axios.post(`http://localhost:8181/api/v1/blogs/create-blog/${encodeURIComponent(email)}`, blogData)
      .then(response => {
        alert('Blog data submitted successfully:', response.data);
      })
      .catch(error => {
        console.error('Error submitting blog data:', error);
      });
  };

  return (
    <div className="container">
      <div className="centered-content">
        <h1>Create New Blog</h1>
        <form onSubmit={handleSubmit}>
          <div>
            <label>Title:</label>
            <input type="text" name="title" value={blogData.title} onChange={handleChange} required />
          </div>
          <div>
            <label>Author:</label>
            <input type="text" name="author" value={blogData.author} onChange={handleChange} required />
          </div>
          <div>
            <label>Content:</label>
            <textarea name="content" value={blogData.content} onChange={handleChange} required />
          </div>
          <div>
            <label>Date:</label>
            <input type="text" name="date" value={blogData.date} onChange={handleChange} required />
          </div>
          <div>
            <label>Image URL:</label>
            <input type="text" name="imageUrl" value={blogData.imageUrl} onChange={handleChange} required />
          </div>
          <button type="submit">Submit</button>
        </form>
      </div>
    </div>
  );
}

export default CreateNewBlog;
