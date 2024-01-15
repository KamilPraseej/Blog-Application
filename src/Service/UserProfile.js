import { jwtDecode } from 'jwt-decode';


const USER_API_BASE_URL = "http://localhost:8181/api/v1/user/view-userProfile/";

class UserProfile {
  async getUserDetails() {
    const email = jwtDecode(localStorage.getItem("bearer-token")).email;
    const token = localStorage.getItem("bearer-token");
    const url = `${USER_API_BASE_URL}${encodeURIComponent(email)}`; 

    const resp = await fetch(url, {
      mode: "cors",
      method: "get",
      headers: {
        Authorization: `Bearer ${token}`,
      }
    });

    const data=await resp.json()
    return data;
  }
}

const userProfile = new UserProfile(); 
export default userProfile; 
