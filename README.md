# s215824_Lykkehjulet

The Wheel of Fortune, also called "Lykkehjulet", has been implemented as an Android application. 
This application is a modified version of the game. 

The objective and rules for the game have been determined from the following YouTube clip: https://www.youtube.com/watch?v=79nl-BDNek0&ab_channel=HenrikLykke 
And then modified to fulfill the requirements posed by the assignment. 

List of rules to be implemented: 
* The game is for one player
* The player start with 0 in their bank
* When the game starts, a word is randamly chosen from predefined categories.
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


# Menu
<img width="253" alt="menu1" src="https://user-images.githubusercontent.com/91070526/203660791-fa90532b-b621-475e-97cc-8209fa9674eb.png">

# Rules
<img width="252" alt="rules1" src="https://user-images.githubusercontent.com/91070526/203660794-945febdc-db5c-4bf3-907a-f8486efce532.png">

# Game 
<img width="255" alt="game11" src="https://user-images.githubusercontent.com/91070526/203660806-6bfeea98-9002-4efe-95e3-b9aa92171b31.png">
<img width="254" alt="game22" src="https://user-images.githubusercontent.com/91070526/203660810-4d754d28-184c-45d0-853b-71362dd375b1.png">

# Won
<img width="251" alt="won1" src="https://user-images.githubusercontent.com/91070526/203660814-16bf4b24-e455-4563-a1ec-118e285e60c2.png">

# Lost
<img width="249" alt="lost1" src="https://user-images.githubusercontent.com/91070526/203660820-ef3de645-4efb-49cf-9a81-9b312e992854.png">


