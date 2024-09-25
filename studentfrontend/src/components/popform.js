import React, { useState } from 'react';
import { Modal, Button, TextField, Box,MenuItem, Select, InputLabel, FormControl } from '@mui/material';
import axios from 'axios';  // Import axios for making HTTP requests
import { useNavigate } from 'react-router-dom';

const AddTaskForm = ({ addTask }) => {
  const [open, setOpen] = useState(false);
  const [taskDetails, setTaskDetails] = useState({
    task: '',
    description: '',
    startdate: '',
    enddate: ''
  });

  // Handle modal open and close
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  // Handle input changes
  const handleChange = (e) => {
    setTaskDetails({
      ...taskDetails,
      [e.target.name]: e.target.value
    });
  };
  const navigate = useNavigate();

  

  // Handle form submission and send data to backend
  const handleSubmit = async (e) => {
    e.preventDefault();
    
    try {
      // Call the backend API to save task details
      const response = await axios.post('http://localhost:8080/board/update', taskDetails); // Replace with your API URL
      
      if (response.status === 201 || response.status === 200) {
        console.log("Task successfully added:", response.data);
        addTask(response.data);  // Optimistically add the new task to the frontend state
        handleClose();
        navigate("/")  // Close the modal after submission
      } else {
        console.error("Error adding task:", response);
      }
    } catch (error) {
      console.error("Error while sending request:", error);
    }
  };

  return (
    <div>
      {/* Button to trigger modal */}
      <Button variant="contained" color="primary" onClick={handleOpen}>
        Add New Task
      </Button>

      {/* Modal with form */}
      <Modal open={open} onClose={handleClose}>
        <Box
          sx={{
            position: 'absolute',
            top: '50%',
            left: '50%',
            transform: 'translate(-50%, -50%)',
            width: 400,
            bgcolor: 'background.paper',
            border: '2px solid #000',
            boxShadow: 24,
            p: 4
          }}
        >
          <h2>Add New Task</h2>
          <form onSubmit={handleSubmit}>
            <TextField
              label="Task Name"
              name="task"
              value={taskDetails.task}
              onChange={handleChange}
              fullWidth
              margin="normal"
              required
            />
            <TextField
              label="Description"
              name="description"
              value={taskDetails.description}
              onChange={handleChange}
              fullWidth
              margin="normal"
            />
            <TextField
              label="Start Date"
              name="startdate"
              type="date"
              value={taskDetails.startdate}
              onChange={handleChange}
              fullWidth
              margin="normal"
              InputLabelProps={{ shrink: true }}
              required
            />
            <TextField
              label="End Date"
              name="enddate"
              type="date"
              value={taskDetails.enddate}
              onChange={handleChange}
              fullWidth
              margin="normal"
              InputLabelProps={{ shrink: true }}
              required
            />
            <FormControl fullWidth margin="normal">
              <InputLabel>Status</InputLabel>
              <Select
                name="status"
                value={taskDetails.status}
                onChange={handleChange}
                required
              >
                <MenuItem value="In progress">In Progress</MenuItem>
                <MenuItem value="TO DO">To Do</MenuItem>
                <MenuItem value="Done">Done</MenuItem>
              </Select>
            </FormControl>
            <Button type="submit" variant="contained" color="primary">
              Submit
            </Button>
          </form>
        </Box>
      </Modal>
    </div>
  );
};

const App = () => {
  const [tasks, setTasks] = useState([]);

  // Function to add a new task to the frontend state
  const addTask = (newTask) => {
    setTasks([...tasks, newTask]);
  };

  return (
    <div>
      <h1>Kanban Board</h1>
      <AddTaskForm addTask={addTask} />

      {/* Display added tasks */}
      {/* <div>
        {tasks.map((task, index) => (
          <div key={index}>
            <h3>{task.task}</h3>
            <p>{task.description}</p>
            <p>Start Date: {task.startdate}</p>
            <p>End Date: {task.enddate}</p>
          </div>
        ))}
      </div> */}
      
    </div>
  );
};


export default App;
