import axios from "axios"

const AUTH_REST_API_BASE_URL = "http://localhost:8080/api/auth"

export const registerAPICall = (registerObj)=>axios.post(AUTH_REST_API_BASE_URL+'/register',registerObj);

export const loginAPICall = (usernameOrEmail,password)=>axios.post(AUTH_REST_API_BASE_URL+'/login',{usernameOrEmail,password});

export const storeToken =(token)=> localStorage.setItem("token",token);


export const getToken=()=>localStorage.getItem("token");

export const saveLoggedInUSer=(username,role)=>{
    sessionStorage.setItem("authenticationUser",username);
    sessionStorage.setItem("role",role);
}


export const isUSerLoggedIn=()=>{

    const username = sessionStorage.getItem("authenticationUser");

    if(username==null){
       
        return false;
    }
    else{
        
        return true;
    }
}

export const getLoggedInUSer = ()=>{

    const username = sessionStorage.getItem("authenticationUser");

    return username;
}

export const logout =()=>{
    localStorage.clear();
    sessionStorage.clear();
  
}

export const isAdminUser = ()=>{

    let role = sessionStorage.getItem("role");

    if(role!=null && role=== 'ROLE_ADMIN'){
        return true;
    }
    else{
        return false;
    }
}