import React from "react";
import blogPosts from "../../Constants/blogData";


function Content(props) {
  return (
    <div className="row">
      {blogPosts.map((blog, index) => (
        <div key={index}>
          <h2>{blog.title}</h2><br/>
          <p>{blog.date}</p><br/>
          <p>{blog.content}</p><br/>
          <img src={blog.image} alt={blog.title} /><br/>
          <a href={blog.url} target="_blank" rel="noopener noreferrer">Read More</a><br/><br/>
        </div>
      ))}
    </div>
  );
}

export default Content;
