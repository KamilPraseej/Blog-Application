import React, { Component } from 'react';
import UserProfile from '../../Service/UserProfile.js';
import './Profile.css'; 

export default class ProfileDetails extends Component {
  constructor(props) {
    super(props);

    this.state = {
      user: {
        firstName: '',
        emailId: '',
        age: 0,
        contactNo: '',
      },
    };
  }

  componentDidMount() {
    UserProfile.getUserDetails().then((data) => {
      this.setState({ user: data });
    });
  }

  render() {
    const { user } = this.state;

    return (
        <div className='centered-profile'>
          <div className='profile-data'>
            <h2>Full Name: {user.firstName + ' ' + user.lastName}</h2>
            <h2>Email ID: {user.emailId}</h2>
            <h2>Age: {user.age}</h2>
            <h2>Contact No: {user.contactNo}</h2>
          </div>
        </div>
      );
      
}
}
