
import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";

const firebaseConfig = {
    apiKey: "AIzaSyBNciOu3lJljJ-C_e12CgoYmt5lVDXGy5M",
    authDomain: "chat-ecommerce-fd1e8.firebaseapp.com",
    projectId: "chat-ecommerce-fd1e8",
    storageBucket: "chat-ecommerce-fd1e8.appspot.com",
    messagingSenderId: "635166635027",
    appId: "1:635166635027:web:25c835d553e320bc3dbbe6",
    measurementId: "G-WME7D679R8"
  };
  
  // Initialize Firebase
export const app = initializeApp(firebaseConfig);
export const auth = getAuth();

