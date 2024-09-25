import React, { useState, useEffect } from 'react';
import { Card, CardContent,Button } from '@mui/material';
import { useDrag, useDrop, DndProvider } from 'react-dnd';
import { HTML5Backend } from 'react-dnd-html5-backend';
import axios from 'axios';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';


const formatDate = (timestamp) => {
    const date = new Date(timestamp);
    return date.toISOString().split('T')[0]; // Returns 'YYYY-MM-DD'
  };

// Card component that can be dragged
const DraggableCard = ({ card, moveCard }) => {
    const [{ isDragging }, drag] = useDrag(() => ({
      type: 'CARD',
      item: { card },
      collect: (monitor) => ({
        isDragging: !!monitor.isDragging(),
      }),
    }));
  
    return (
      <div ref={drag} style={{ opacity: isDragging ? 0.5 : 1 }}>
        <Card sx={{ minWidth: 275, margin: '20px' }}>
          <CardContent>
            <h2>{card.task}</h2>
            <p>{card.description}</p>
            <p>Start: {formatDate(card.startdate)}</p>
            <p>End: {formatDate(card.enddate)}</p>
            <p>Status: {card.status}</p>
            <Button
            variant="text"  // Use text variant for transparent background
            startIcon={<EditIcon />}
            //onClick={onEdit}
            onClick={() => EditCard(card.id)}
            sx={{
              backgroundColor: 'transparent',  // Transparent background
              color: 'primary.main',  // Default color or customize with your theme
              '&:hover': {
                backgroundColor: 'rgba(0, 0, 0, 0.1)'  // Slight hover effect if needed
                
              }
              
            }
            
          }
          >
            Edit
          </Button>
            
            <Button 
            variant="contained" 
            color='red' 
            sx={{backgroundColor: 'red',  // Red background
              color: 'white',  // White text to contrast with red background
              '&:hover': {
                backgroundColor: 'darkred'  // Darker red on hover
                }}}
            startIcon={<DeleteIcon />}
            onClick={() => deleteCard(card.id)    
            }
          >
          </Button>
          </CardContent>

        </Card>
      </div>
    );
  };
// Column component that accepts dropped cards
const StatusColumn = ({ status, cards, moveCard }) => {
    const [{ isOver }, drop] = useDrop({
      accept: 'CARD',
      drop: (item) => moveCard(item.card, status),
      collect: (monitor) => ({
        isOver: !!monitor.isOver(),
      }),
    });
  
    return (
      <div
        ref={drop}
        className="status-column"
        style={{
          flex: 1,
          margin: '10px',
          backgroundColor: isOver ? 'lightgreen' : 'white',
        }}
      >
        <h3>{status.toUpperCase()}</h3>
        {cards.map((card) => (
          <DraggableCard key={card.id} card={card} moveCard={moveCard} />
        ))}
      </div>
    );
  };

  const CardColumns = () => {
    const [cards, setCards] = useState([]);
    const apiURL = 'http://localhost:8080/board/getall'; // Replace with your API URL
    const apiURL1='http://localhost:8080/board/update';
    
    // Fetch data from API when component mounts
    useEffect(() => {
      const fetchData = async () => {
        try {
          const response = await fetch(apiURL);
          const data = await response.json(); // Parse JSON from response
          setCards(data);
        } catch (error) {
          console.error('Error fetching data:', error);
        }
      };
  
      fetchData();
    }, []);

    
  
    // Function to move a card to a new column (status)
    const moveCard = async (card, newStatus) => {
      const updatedCard = { ...card, status: newStatus };
      console.log(newStatus);
      
      // Optimistically update the state
      setCards((prevCards) =>
        prevCards.map((c) => (c.id === card.id ? updatedCard : c))
      );
  
      // Send the updated status to the backend
      try {
        console.log(card.id)
        await axios.put(`${apiURL1}/${card.id}`, {
          task: card.task,
          description: card.description,
          startdate: card.startdate,
          enddate: card.enddate,
          status: newStatus, // Send the updated status
        }, {
          headers: {
            'Content-Type': 'application/json',
          }
        });
        console.log('Status updated successfully!');
      } catch (error) {
        console.error('Error updating status:', error);
    
        // Revert the change if there's an error
        setCards((prevCards) =>
          prevCards.map((c) => (c.id === card.id ? card : c))
        );
      }
    };
  
    // Group the cards by status
    const groupedCards = cards.reduce((acc, card) => {
      if (!acc[card.status]) {
        acc[card.status] = [];
      }
      acc[card.status].push(card);
      return acc;
    }, {});
  
    return (
      <DndProvider backend={HTML5Backend}>
        <div className="card-columns-container" style={{ display: 'flex' }}>
          {Object.keys(groupedCards).map((status) => (
            <StatusColumn
              key={status}
              status={status}
              cards={groupedCards[status]}
              moveCard={moveCard}
              deleteCard={deleteCard} // Pass deleteCard function to StatusColumn
            />
          ))}
        </div>
      </DndProvider>
    );
  };
  const deleteCard = async (id) => {
    // Optimistically remove the card from the frontend
    //setCards((prevCards) => prevCards.filter((card) => card.id !== id));
    const apiURLDelete="http://localhost:8080/board/delete";

    // Send the delete request to the backend
    try {
      await axios.delete(`${apiURLDelete}/${id}`, {
        headers: {
          'Content-Type': 'application/json',
        }
      });
      console.log('Card deleted successfully!');
    } catch (error) {
      console.error('Error deleting card:', error);
      // Optionally, add logic to revert the deletion if needed
    }
    window.location.reload();

  };
  const EditCard = async (id) => {
    

  };
  
  export default CardColumns;
  