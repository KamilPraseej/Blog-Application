import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { jwtDecode } from 'jwt-decode';

function MyBlogs() {
  const [blogs, setBlogs] = useState([]);

  useEffect(() => {
    const email = jwtDecode(localStorage.getItem("bearer-token")).email;

    axios.get(`http://localhost:8181/api/v1/blogs/all-blogs/${encodeURIComponent(email)}`)
      .then((response) => {
        setBlogs(response.data);
      })
      .catch((error) => {
        console.error('Error fetching blog data:', error);
      });
  }, []);

  return (
    <div>
      <h1>List of Blogs</h1><br/><br/>

      {blogs.map((blog) => (
        <div key={blog.id} className="blog-item">
          <h2>{blog.title}</h2><br/>
          <h3>Author: {blog.author}</h3><br/>
          <h3>Date: {blog.date}</h3><br/>
          <h3>Content: {blog.content}</h3><br/>
          <img src={blog.imageUrl} alt={blog.title} /><br/><br/><br/><br/><br/><br/>
        </div>
      ))}
    </div>
  );
}

export default MyBlogs;
