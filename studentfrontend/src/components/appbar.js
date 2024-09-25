import React from 'react';
import { AppBar, Toolbar, Typography, Button, IconButton } from '@mui/material';
import MenuIcon from '@mui/icons-material/Menu'; // Optional menu icon
import AccountCircleIcon from '@mui/icons-material/AccountCircle'; // Optional user icon
import { useNavigate } from 'react-router-dom'; // Import useNavigate for navigation

const Header = () => {
  const navigate = useNavigate(); // Initialize the navigate function

  // Function to handle the button click and navigate to the add task page
  const handleAddTaskClick = () => {
    navigate('/add-task'); // This will navigate to the "/add-task" page
  };
  

  return (
    <AppBar position="static" style={{ backgroundColor: '#1976d2' }}>
      <Toolbar>
        {/* Optional Menu Icon */}
        <IconButton
          edge="start"
          color="inherit"
          aria-label="menu"
          sx={{ mr: 2 }}
        >
          <MenuIcon />
        </IconButton>
        
        {/* Application Title */}
        <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
          Kanban Board
        </Typography>
        
        {/* ADD TASK Button that navigates to add-task page */}
        <Button color="inherit" onClick={handleAddTaskClick}>
          ADD TASK
        </Button>
        
        {/* Optional user account icon */}
        <IconButton color="inherit">
          <AccountCircleIcon />
        </IconButton>
      </Toolbar>
      
    </AppBar>
  );
};

export default Header;
