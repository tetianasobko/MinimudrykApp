Description of the Developed Application

#### List of Topics

On the first page (see Figure 1.1), the user is greeted with the main interface of the application. They see a list of topics included in the chosen area of knowledge or course. Each topic is presented as a list item that includes the topic title and the progress percentage of the topic.

![image](https://github.com/tetianasobko/MinimudrykApp/assets/137815271/427c2436-9440-468c-98c9-91f51e8cd7d0)

(Figure 1.1)

#### Task Page

Upon navigating to the task page (see Figure 3.2), we see that the top row of the page contains a title indicating the current topic the user is in. Next to the title is a "<" button that allows the user to return to the list of topics or the previous page with one click.

The main part of the page consists of a list of tasks related to the chosen topic. Each task is represented by a square button containing the name of the task or exercise. Each button has an icon indicating the task's status. When the task is not yet completed, the icon is an empty circle. Once the user successfully completes the task, the icon changes to include a check mark. Additionally, the button's color may further indicate the task's status: grey for uncompleted tasks and green for successfully completed ones.

![image](https://github.com/tetianasobko/MinimudrykApp/assets/137815271/6c7a9235-eba1-4b20-a42b-2327c0dc77a0)

(Figure 1.2)

#### First Topic Tasks

When the user clicks on a task button, they are taken to the task execution page. It is important to note that the first topic contains tasks in test format, meaning they include either open-ended questions (see Figure 3.3) or multiple-choice questions (see Figure 3.5).

##### Open-Ended Questions

If a task has an open-ended question, the user sees the top row of the page with the task title and a "<" button to return to the list of tasks. Below is a card with the task text or question. In the upper right corner of the card, there is a hint button icon. When the user clicks this button, a hint window about the task appears. Below is a text field where the answer can be entered. After entering the answer, the user can press the "Check" button to verify the correctness of the entered answer.

![image](https://github.com/tetianasobko/MinimudrykApp/assets/137815271/f7abd77e-8637-4661-a115-bc6693fe7748)

(Figure 1.3)

If the answer is incorrect, an appropriate window appears (see Figure 3.4) with a "Try again" button. If the answer is correct, a window confirming the successful completion of the task appears.

![image](https://github.com/tetianasobko/MinimudrykApp/assets/137815271/0b5a9fa6-7e19-4545-bc23-ee8e895e03dc)
![image](https://github.com/tetianasobko/MinimudrykApp/assets/137815271/194d3e63-0724-446d-a030-d5c956975e10)

(Figure 1.4)

##### Multiple-Choice Questions

When a task has multiple-choice answers, the user sees a similar interface. It also has a task title, a "<" button, task text, and a hint button. However, instead of entering an answer, the user can select one of the given options. After choosing an answer, a "Check" button appears to verify the correctness of the selected option. The further process is similar: if the answer is incorrect, a window with a "Try again" button appears, and if correct, a window confirming the successful completion of the task appears.

![image](https://github.com/tetianasobko/MinimudrykApp/assets/137815271/7425b3e9-92d9-40d9-9baf-b325b8845008)

(Figure 1.5)

#### Second Topic Tasks

The second topic contains more interactive tasks. In the tasks of the second topic, the user also sees the top row with the task title and a "<" button to return to the list of tasks. Below this row is a button labeled "Condition" (see Figure 3.6). When this button is pressed, it expands to reveal the task's conditions. Below this is the interactive part of the task.

![image](https://github.com/tetianasobko/MinimudrykApp/assets/137815271/53d08b6c-3219-4bd0-853a-5c51cee776f8)
![image](https://github.com/tetianasobko/MinimudrykApp/assets/137815271/41e51e11-2e81-4e8e-a9e0-02d62db9dfbe)

(Figure 1.6)

##### Task "Horizontal Rearrangement"

The first task has the following condition: "Two players play on a 2×10 board. In the first column, both cells contain the first player's tokens, and in the tenth column, the second player's tokens. On their turn, a player moves any of their tokens to any free cell on the same row, without jumping over another player's token. The player who cannot make a move loses."

At the bottom of the screen is a 2×10 table where the two cells of the first column are blue, and the two cells of the last column are green, representing the starting positions of the players (see Figure 3.7).

The user selects a cell to move a token by clicking on it. The selected cell turns light blue to indicate the choice. After this, the user clicks the "Make a move" button, which changes the selected cell's color to blue. The program then calculates and makes its move.

![image](https://github.com/tetianasobko/MinimudrykApp/assets/137815271/1d3cd959-a031-427e-bdc0-79c162279cf2)
![image](https://github.com/tetianasobko/MinimudrykApp/assets/137815271/51fefc28-99b6-4dc8-94e7-2b000589399a)

(Figure 1.7)

This process repeats until one of the players wins. If the user wins, the task is considered completed, and a corresponding window appears. In case of a loss, a window with a "Try again" button appears, starting the game anew. Next to the "Make a move" button is a "Start second" button. If the user chooses this option, the program makes the first move, and the rest of the game proceeds similarly.

##### Task "Rectangle Maze"

The next task has the following condition: "In a 5×9 rectangle, a token is placed in the lower left corner. Players take turns moving it any number of cells up or to the right. The winner is the one who places the token in the upper right corner."

At the top of the screen, the task title, the "<" button, and the "Condition" button, which reveals the task's condition, are displayed (see Figure 3.8). 

At the bottom of the screen is a 5×9 table where each cell is a grey square button, and one cell is blue according to the task's condition, representing the starting position. The user can select cells according to the condition. The selected cell turns green.

After selecting a cell to move, the user clicks the "Make a move" button. Then the blue cell turns grey, and the cell chosen by the user turns blue. The program calculates the next move as the second player, and after a few seconds, the blue cell changes its position.

![image](https://github.com/tetianasobko/MinimudrykApp/assets/137815271/3fe5d5da-7839-4272-9bb8-17e5cd80b7a7)
![image](https://github.com/tetianasobko/MinimudrykApp/assets/137815271/79060a87-93e5-40db-9207-5e509048ac81)

(Figure 1.8)

The game then proceeds similarly to the previously described scenario: both players take turns selecting cells to move the token until one of them wins.

##### Task "Multiplication Race to 1000"

In the next game, the first player names an integer from 2 to 9. Then the second player multiplies this number by any integer from 2 to 9. After that, the first player multiplies the result by any integer from 2 to 9, and so on. The winner is the one who first gets a product greater than 1000.

The game interface is similar to a calculator (see Figure 3.9), but instead of regular buttons with numbers from 0 to 9, the buttons consist of numbers from 2 to 9. The user selects a number to multiply the result by clicking the corresponding button. The user's choice is displayed above the buttons, along with information about the last move made by the program. There is a large "=" button to confirm the user's choice, and then the multiplication occurs. The game continues until one player achieves a product greater than 1000. After this, a victory or defeat message is displayed. The game interface also includes a button to clear all chosen numbers and start the game anew.

![image](https://github.com/tetianasobko/MinimudrykApp/assets/137815271/2ede73b0-91ed-44ae-a755-b3e85e8804f4)

(Figure 1.9)

#### Third Topic Tasks

Let's consider another task from the third topic. In this game, the user needs to express number A through number B using various mathematical operations. The interface of the upper part of the screen is similar to the one described earlier for the second topic tasks.

In the lower part of the screen, the user sees the number A that needs to be expressed. Below is a field displaying the expression the user is forming. Further, the user sees a table with buttons, including number B, a clear button for the expression field, a set of mathematical operations the user can use to create the expression, a "del" button to delete the last entered character in the expression, and a button to check the user's expression.

![image](https://github.com/tetianasobko/MinimudrykApp/assets/137815271/02ca72b1-e385-40ff-8b8c-04a940c91043)

(Figure 1.10)

The user can use number B and mathematical operations to create an expression to express number A (see Figure 3.10). After creating the expression, the user can click the check button to verify its correctness. If the expression is correct, the task is considered successfully completed (see Figure 3.11).

![image](https://github.com/tetianasobko/MinimudrykApp/assets/137815271/514f5119-b1ff-4dd9-9a1d-967fdecae4ba)
![image](https://github.com/tetianasobko/MinimudrykApp/assets/137815271/b89ae358-77a5-47b8-bbcc-36f9127e79e6)

(Figure 1.11)
