import React from 'react';
import './Profile.css';
import { Logout } from '../..';

function Profile({ onProfileClick, handleHomeClick, onCreateNewBlog, onHelpDeskClick, onMyBlogsClick}) {
  return (
    <div className="profile">
      <img
        className="profile-img"
        src="https://cirrusindia.co.in/wp-content/uploads/2016/10/dummy-profile-pic-male1.jpg"
        alt="profile"
      />

      <div className="text">
        <h1 onClick={() => handleHomeClick()}>Home</h1>
        <h1 onClick={() => onProfileClick()}>Profile</h1>
        <h1 onClick={() => onCreateNewBlog()}>Create New Blog</h1>
        <h1 onClick={()=> onMyBlogsClick()}>My Blogs</h1>
        <h1 onClick={() => onHelpDeskClick()}>Help Desk</h1>
        <h1 onClick={Logout}>Log Out</h1>
        
      </div>
    </div>
  );
}

export default Profile;
