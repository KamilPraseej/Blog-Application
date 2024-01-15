import React from 'react';
import {createRoot} from 'react-dom/client'
import Keycloak from 'keycloak-js';
import App from './App';

let initOptions = {
  url: 'http://localhost:8080/',
  realm: 'Blog-Application',
  clientId: 'Blog-App-React',
  onLoad: 'login-required'
};

let keycloak = Keycloak(initOptions);

keycloak.init({ onLoad: initOptions.onLoad }).success((auth) => {
  if (!auth) {
    window.location.reload();
  } else {
    console.info("Authenticated");
  }

  localStorage.setItem("bearer-token", keycloak.token);
  localStorage.setItem("refresh-token", keycloak.refreshToken);
  console.log(keycloak.token);
  console.log(localStorage.getItem("b"));
}).error(() => {
  console.error("Authentication Failed");
});

export function Logout() {

      keycloak.logout({ redirectUri: window.location.origin }); 
   
}

const root=createRoot(document.getElementById("root"))

root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
);
