import React, { useState } from 'react';
import './App.css';
import Profile from './Components/Profile/Profile';
import Content from './Components/Content/Content';
import ProfileDetails from './Components/Profile/ProfileDetails';
import CreateNewBlog from './Components/Profile/CreateNewBlog';
import HelpDesk from './Components/Profile/HelpDesk';
import MyBlogs from './Components/Profile/MyBlogs';

function App() {
  const [showProfileDetails, setShowProfileDetails] = useState(false);
  const [showContent, setShowContent] = useState(true);
  const [showBlog, setShowBlog] = useState(false);
  const [showHelpDesk, setHelpDesk] = useState(false);
  const [showMyBlogs, setShowMyBlogs] = useState(false);

  const handleProfileClick = () => {
    setShowProfileDetails(true);
    setShowContent(false);
    setShowBlog(false);
    setHelpDesk(false);
    setShowMyBlogs(false);
  };

  const handleHomeClick = () => {
    setShowProfileDetails(false);
    setShowContent(true);
    setShowBlog(false);
    setHelpDesk(false);
    setShowMyBlogs(false);
  };

  const createNewBlog = () => {
    setShowBlog(true);
    setShowProfileDetails(false);
    setShowContent(false);
    setHelpDesk(false);
    setShowMyBlogs(false);
  }

  const helpDesks = () => {
    setShowBlog(false);
    setShowProfileDetails(false);
    setShowContent(false);
    setHelpDesk(true);
    setShowMyBlogs(false);
  }

  const showMyBlog = () => {
    setShowBlog(false);
    setShowProfileDetails(false);
    setShowContent(false);
    setHelpDesk(false);
    setShowMyBlogs(true);
  }

  return (
    <div className="row">
      <div className='column1'>
        <Profile onProfileClick={handleProfileClick} handleHomeClick={handleHomeClick} onCreateNewBlog={createNewBlog} onHelpDeskClick={helpDesks} onMyBlogsClick={showMyBlog}/>
      </div>
      <div className='column2'>

        {showProfileDetails && <ProfileDetails />}
        {showContent && <Content />}
        {showBlog && <CreateNewBlog />}
        {showHelpDesk && <HelpDesk/>}
        {showMyBlogs && <MyBlogs/>}
      </div>
    </div>
  );
}

export default App;

