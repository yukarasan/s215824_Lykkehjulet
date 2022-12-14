# s215824_Lykkehjulet

The Wheel of Fortune, also called "Lykkehjulet", has been implemented as an Android application. 
This application is a modified version of the game. 

The objective and rules for the game have been determined from the following YouTube clip: https://www.youtube.com/watch?v=79nl-BDNek0&ab_channel=HenrikLykke 
And then modified to fulfill the requirements posed by the assignment. 

List of rules to be implemented: 
* The game is for one player
* The player start with 0 in their bank
* When the game starts, a word is randomly chosen from a list of predefined categories.
  * The word and category is displayed on the screen
    * The word is initially hidden
* In the first round, the wheel is "spun" by tapping a button. Here a player can land on: 
  * A value e.g. "1500", which is the amount of points you gain by guessing a correct letter.
    * The letter must be either a consonant or vowel from the danish alphabet
    * The points that a player gets, is determined by the number of times a letter occur in the word
    * If the occurence is 0, then the player looses a life (5 lifes) 
  * "FALLIT" or bankrupt, which takes all the earned points away from the player. 
* The "wheel is spun" until the game is won or lost. 
  * The game is won when all the letters have been found
  * The game is lost when the player looses all of their lifes before the game is won.
* The game should be able to be played again if a player has won or lost their current game  

# Loading
![loading](https://user-images.githubusercontent.com/91070526/204875684-9926695a-bb19-44ad-a63d-e2360f4fad9b.jpg)

# Menu
![menu](https://user-images.githubusercontent.com/91070526/204875718-18e7cfca-44fd-4be8-8f77-8f1561d80005.jpg)

# Rules
![rules](https://user-images.githubusercontent.com/91070526/204875746-04f10593-bc80-43bf-a346-9ef2bab593b5.jpg)

# Game 
![GameSpinning](https://user-images.githubusercontent.com/91070526/204875819-951c6965-5320-4d32-b5d1-76cb976ca21e.jpg)
![GameWrongAnswer](https://user-images.githubusercontent.com/91070526/204875845-b739098c-b646-487d-802b-956e22541b71.jpg)
![gameCorrectAnswer](https://user-images.githubusercontent.com/91070526/204875870-1ac838a4-e7cf-43df-9c56-84f1e6de1a9a.jpg)

# Won
![won](https://user-images.githubusercontent.com/91070526/204875888-8911c37a-b80c-491a-97cb-33353efc8d49.jpg)

# Lost
![lost](https://user-images.githubusercontent.com/91070526/204875919-81d0fc11-ea50-47e2-a746-cbe4f0de5c6b.jpg)
