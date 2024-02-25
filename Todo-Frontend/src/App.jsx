import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

import ListTodoComponent from './components/ListTodoComponent'
import HeaderTodoComponent from './components/HeaderTodoComponent'
import FooterTodoComponent from './components/FooterTodoComponent'
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import TodoComponent from './components/TodoComponent'
import RegisterComponent from './components/RegisterComponent'
import LoginComponent from './components/LoginComponent'
import { isUSerLoggedIn } from '../services/AuthService'

function App() {
  // const [count, setCount] = useState(0)

  function AuthenticatedRoute({childern}) {

    const isAuth = isUSerLoggedIn();

    if (isAuth) {

      console.log(isAuth)
      return childern;
    
    }
    return <Navigate to="/" />

  }


  return (
    <>
   
      <BrowserRouter>
        <HeaderTodoComponent />
        
        <Routes>
          {/* http://localhost:8080 */}
          <Route path='/' element={< LoginComponent />}></Route>

          {/* http://localhost:8080/todos */}
          <Route path='/todos' element={< ListTodoComponent />  }></Route>
             {/* <AuthenticatedRoute> */}
              
            {/* </AuthenticatedRoute> */}

         

          {/* http://localhost:8080/add-todo */}
          <Route path='/add-todo' element={< TodoComponent />
            // <AuthenticatedRoute>
            //   < TodoComponent />
            // </AuthenticatedRoute>

          }></Route>
          {/* http://localhost:8080/update-todo/:id */}
          <Route path='/update-todo/:id' element={ <TodoComponent />
            // <AuthenticatedRoute>
            //   <TodoComponent />
            // </AuthenticatedRoute>
          }></Route>

          {/* http://localhost:8080/register */}
          <Route path='/register' element={<RegisterComponent />}></Route>
          {/* http://localhost:8080/login */}
          <Route path='/login' element={<LoginComponent />}></Route>

        </Routes>

        <FooterTodoComponent />
      </BrowserRouter>






    </>
  )
}

export default App
