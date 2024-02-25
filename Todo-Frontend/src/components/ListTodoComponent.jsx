import React, { useEffect, useState } from 'react'
import { completeTodo, deleteTodo, getAllTodos, inCompleteTodo } from '../../services/TodoService'
import { useNavigate } from 'react-router-dom'
import { isAdminUser } from '../../services/AuthService'

const ListTodoComponent = () => {

    const [todos, setTodos] = useState([])

    const navigate = useNavigate();

    const isAdmin = isAdminUser();


    useEffect(() => {
        listTodos();
    }, [])

     function listTodos() {

        getAllTodos().then((response) => {
            setTodos(response.data);
            console.log(response.data);//error checking
        }).catch((error) => console.error(error))
    }
    function addNewTodo() {
       
        
        navigate('/add-todo')
        window.location.reload(false);
    }

    function updateTodo(id) {
        console.log(id)
        navigate(`/update-todo/${id}`)
        window.location.reload(false);
    }

    function removeTodo(id) {

        deleteTodo(id).then((response) => {
            listTodos();
        }).catch(error => console.error(error))
    }

    function markCompletedTodo(id) {
        completeTodo(id).then((response) => {

            listTodos();
        }).catch(error => console.error(error))
    }

    function markInCompleteTodo(id) {
        inCompleteTodo(id).then((response) => {
            listTodos();
        }).catch(error => console.error(error))
    }



    return (
        <div className='container'>
            <br />
            <h1 className='text-center'>ListTodoComponent</h1>

            {
                isAdmin && 
                <button className='btn btn-primary mb-2'
                onClick={addNewTodo}> Add Todo </button>
            }
            

            <div>
                <table className='table table-bordered table-striped text-center'>
                    <thead>
                        <tr>
                            <th>Todo Title</th>
                            <th>Todo Description</th>
                            <th>Todo Completed</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            todos.map(todo =>
                                <tr key={todo.id}>
                                    <td>{todo.title}</td>
                                    <td>{todo.description}</td>
                                    <td>{todo.completed ? 'Yes' : 'No'}</td>
                                    <td>

                                        {
                                            isAdmin && 
                                            <button className='btn btn-info'
                                            onClick={() => updateTodo(todo.id)}>
                                            Update
                                        </button>
                                        }

                                        {
                                            isAdmin && 
                                            <button className='btn btn-danger'
                                            onClick={() => removeTodo(todo.id)}
                                            style={{ marginLeft: "10px" }}>
                                            Delete
                                        </button>
                                        }
                                        

                                        <button className='btn btn-success'
                                            onClick={() => markCompletedTodo(todo.id)}
                                            style={{ marginLeft: "10px" }}>
                                            Completed
                                        </button>

                                        <button className='btn btn-warning'
                                            onClick={() => markInCompleteTodo(todo.id)}
                                            style={{ marginLeft: "10px" }}>
                                            In-Complete
                                        </button>
                                    </td>
                                </tr>

                            )
                        }
                    </tbody>
                </table>
            </div>


        </div>
    )
}

export default ListTodoComponent