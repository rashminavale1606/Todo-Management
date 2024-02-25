import React from 'react'
import { NavLink, useNavigate } from 'react-router-dom'
import { isUSerLoggedIn, logout } from '../../services/AuthService'

// Shift+Alt+F---> to get code in proper Manner keyword
// ctrl+/ --> to get the comment keyword
// ctrl+Shift+P --> Open's Terminal
const HeaderTodoComponent = () => {

    const isAuth = isUSerLoggedIn();

    const navigator = useNavigate();
    function handleLogout() {
        logout();
        navigator('/login')
    }

    return (
        <div>
            <header>
                <nav className='navbar navbar-expand-md navbar-dark bg-dark'>
                    <div>
                        <a href="http://localhost::3000"
                            className='navbar-brand'>
                            Todo Management Application
                        </a>
                    </div>
                    <div className='collapse  navbar-collapse'>
                        <ul className='navbar-nav'>

                            {
                                isAuth &&
                                <li className='nav-item'>
                                    <NavLink to={"/todos"}
                                        className="nav-link"

                                    > Todos</NavLink>
                                </li>
                            }


                        </ul>

                    </div>

                    <ul className='navbar-nav'>
                        {
                            !isAuth &&
                            <li className='nav-item'>
                                <NavLink to={"/register"}
                                    className={'nav-link'}

                                > Register</NavLink>
                            </li>
                        }

                        {
                            !isAuth &&
                            <li className='nav-item'
                            >
                                <NavLink to={"/login"}
                                    className={'nav-link'}
                                    

                                > Login</NavLink>
                            </li>
                        }

                        {
                            isAuth &&
                            <li className='nav-item'>
                                <NavLink to={"/login"}
                                    className={'nav-link'}
                                    onClick={handleLogout}

                                > Logout</NavLink>
                            </li>
                        }



                    </ul>
                </nav>
            </header>
        </div>
    )
}

export default HeaderTodoComponent