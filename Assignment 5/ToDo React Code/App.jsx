import { useState } from "react";
import "./App.css";

function App() {
  const [todo, setTodo] = useState("");
  const [todos, setTodos] = useState([]);

  const addTodo = () => {
    if (todo.trim() === "") return;
    setTodos([...todos, todo]);
    setTodo("");
  };

  return (
    <div className="app-container">
      <div className="todo-box">
        <h2>Todo List</h2>

        <div className="input-row">
          <input
            type="text"
            value={todo}
            onChange={(e) => setTodo(e.target.value)}
            onKeyDown={(e) => {
              if (e.key === "Enter") addTodo();
            }}
            placeholder="Enter a todo"
          />
          <button onClick={addTodo}>Add</button>
        </div>

        <ul>
          {todos.map((t, index) => (
            <li key={index}>
              {t}
              <button
                className="delete-btn"
                onClick={() =>
                  setTodos(todos.filter((_, i) => i !== index))
                }
              >
                Delete
              </button>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}

export default App;
