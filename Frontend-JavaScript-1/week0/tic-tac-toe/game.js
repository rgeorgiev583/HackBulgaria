"use strict";
var prompt = require("sync-prompt").prompt;

function printBoard(board) {
  var
    i = 0,
    n = board.length;
  // keep in mind that this is poorly-written JavaScript code
  // we will learn not to use for loops in JavaScript
  for(i; i < n; i++) {
    console.log(board[i].join(""));
  }
}

// entry point for the game
function gameLoop() {
  var
    board = [ ["*", "*", "*"],
              ["*", "*", "*"],
              ["*", "*", "*"] ],
    playerXName = prompt("Player X, enter your name: "),
    playerYName = prompt("Player O, enter your name: "),
    xTurn = true,
    position = null,
    positionX = 0,
    positionY = 0,
    win = false,
    winner = false,
    announceIfWin = function() {
      if(win)
      {
        if(winner == "X")
          console.log(playerXName + " has won! Game over!");
        else
          console.log(playerYName + " has won! Game over!");
      }
    };

  while(true) {
    console.log("This is the current state of the board:");
    printBoard(board);

    if(xTurn) {
      console.log(playerXName + "'s turn!");
    } else {
      console.log(playerYName + "'s turn!");
    }

    // this is blocking prompt
    position = prompt("Please enter placement position in the format 'x y'\n"+
      "  (matrix coordinates, where (0, 0) is the upper- and leftmost cell):");
    position = position.split(" ");
    positionX = position[0];
    positionY = position[1];

    if(xTurn) {
      board[positionX][positionY] = "X";
    }
    else {
      board[positionX][positionY] = "O";
    }

    for(var i = 0; i < 3; i++)
    {
      win = board[i][0] != "*" && board[i][1] != "*" && board[i][2] != "*" &&
            board[i][0] == board[i][1] && board[i][1] == board[i][2] &&
            board[i][2] == board[i][0];
      winner = board[i][0];
      announceIfWin();
      if(win)
        return;

      win = board[0][i] != "*" && board[1][i] != "*" && board[2][i] != "*" &&
            board[0][i] == board[1][i] && board[1][i] == board[2][i] &&
            board[2][i] == board[0][i];
      winner = board[0][i];
      announceIfWin();
      if(win)
        return;
    }

    win = board[0][0] != "*" && board[1][1] != "*" && board[2][2] != "*" &&
          board[0][0] == board[1][1] && board[1][1] == board[2][2] &&
          board[2][2] == board[0][0];
    winner = board[0][0];
    announceIfWin();
    if(win)
      return;

    win = board[0][2] != "*" && board[1][1] != "*" && board[2][0] != "*" &&
          board[0][2] == board[1][1] && board[1][1] == board[2][0] &&
          board[2][0] == board[0][2];
    winner = board[0][2];
    announceIfWin();
    if(win)
      return;

    xTurn = !xTurn;
  }
}

gameLoop();
