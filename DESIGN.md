# Design 
## What are the project's design goals, specifically what kinds of new features did you want to make easy to add

### Game Engine and Player Communication
The main design goal for the Game Engine and Player communication is to keep everything closed and flexible. The goal is to make extending and adding new features to the game easy and intuitive. This was achieved by sending each other `Command` objects that the player and game engine could use. 
### Data
The main design goal for the game data was to make adding conversion classes for new elements to the  within the game very easy. This design goal was prioritized since adding new elements such as attackers, defenders, obstacles, and projectiles to a given level needs to be very extensible so that future development is able to keep the existing code as closed as possible.

### Authoring
The main design goal for game authoring environment is extendability. We would like to make future features easy to add and confugirations for existing features easy to change. Future features may include different configurations/levels for game objects (attackers, defender, obstacles, projectiles), and different level/map/game macro setups.

### Networking
The main design goal was to make networked objects available and easy to add. So once a remote server had been coded and set up, messages could be sent that saved and displayed a user's username, high score, chat's, etc.

## Describe the high-level design of your project, focusing on the purpose and interaction of the core classes

### Game Engine and Player Communication
We used isolated commands to talk between each other so neither the front end or back end would have too much access to each other. Instead, if the front end wanted something placed in a location it would create a back end command "placeObject" and provide a location and unique identification number. this command list would be sent to the back end during the update game method, and the back end would be able to do these commands, and return a front end command list to make these changes (and any other change from the new frame) to the front end. This was a very novel way to communicate, and removed many of the dependencies associated with communicating between the front and back end.

### Data
The high-level design of the game data starts with the Data API. The Data API is used to communicate specifications for the game being built between the authoring environment and the game player/engine. The GameData class is used in order to implement this API. This class is in charge of saving and retrieving data from both the authoring environment and the game player/engine to XML files using XStream. The Data API is also in charge of creating the Game object used by the game engine in order to initialize aspects of the game. This is done by game converter classes that specify specific parameters within a game and levels within the game. Each level within the game then uses an element converter hierarchy in order to add level elements specified from the authoring environment into the Game object being built. Both the Game object and authoring game object used to create the Game are saved to the XML file.


### Authoring
The Game Authoring Environment (GAME AUTHORING ENVIRONMENT) employs the MVC model with multiple controllers, each for a type of configuration, such as defenders, obstacles, projectiles, attackers, levels, maps etc. The GameAuthoringController is the central control class that initializes all the constrollers for sepecific game authoring features. The Model stores all the information about the game design. Model is also responsible for communicating with Data API to save game designs, and load saved game designs.


### Networking
The communication between the server and client uses a command design pattern. The main server interface and the main social center interface have a set of public methods that are accessible to server and client messages, respectively. These commands are then sent back and forth and execute asynchronously. On the client side, the router is responsible for sending outgoing messages and executing incoming messages. On the server side, the main server program is responsible for sending and receivingn messages.

We used the command design pattern really well in our SLogo project, but it lead to one big problem: the interface going into all the commands (the `View`) ended up having about 30 methods to accomodate every command. In order to fix this problem, I split up the functionality of the server into managers. The `ChatManager` is responsible for all chats and the `AccountManager` is responsible for accounts. These managers are then held in the main server class and `getAccountManager()` and `getChatManager()` methods are called by command objects to then get these interfaces. This allowed all my server classes to stay below 10 public methods while still implementing the same command pattern. If I were to do this again, I would have the commands execute synchronously in a queue instead of asynchronously.

The front end is set up in a similar fashion to the server except that it also has display elements. These display elements are then created via reflection which makes them interchangeable. Command objects sent from the server are executed on the social center, which updates the display objects. For display pieces, such as a chat box, that require performing an action when a button is clicked, implement the observer design patter.

## What assumptions or decisions were made to simplify your project's design, especially those that affected adding required features

### Game Engine and Player Communication
One assumption that was made to simplify the design is that the only things that could travel and be placed on the path drawn by the author are `Attacker` and `Obstacle` game elements and that `Defender` game elements can only be placed in `MapElementType.BUILDABLE` cells. Additionally, we assumed that `Defender` objects would not have mobility. Additionally, it was assumed that once a `Defender` or `Obstacle` was placed in the map, that it would stay in its position and that the player would not want to change its location. 

### Data
One key assumption made within the Data API are that all the attributes that a game, level or element may have are set to some non-null value. This allows the conversion process to use getters and setters without checking for NullPointerExceptions. Thus, this burden is put on the authoring environment to make sure there are default values for all the attributes converted within the Data API.

### Authoring
One assumption made is the core types of game object include Attacker, Obstacle, Defender, and Projectile (which are the core types in a general tower defense game). Other minor assumptions include:  a map is immutale for a level, and the Defender does not move, only projectiles shoots projectiles, the socre is calculated for the entire game instead of each level.

### Networking
A major simplification of networking is that it assumes there will be a number of users <50 using the network at any given time. There is also no distributed file system or back up system on the server side. This means that it assumes the server will be running 24/7. This is not a reasonable assumption for real world server but works for the small scale server used in this project.

## Describe, in detail, how to add new features to your project, especially ones you were not able to complete by the deadline

### Game Engine and Player Communication
Right now, the communication between game engine and player supports placing/moving/removing sprites, initialization of the game in each level, dealing with collision, and exploding effects. If the game engine requires player to support a new feature, such as `freeze effect`, a new command class that extends `FrontEndCommand` will have to be made, and designed so that when the `execute` command in the object of the class is called, an appropriate method in the `FrontEndController` class will be called.

### Data
In order to add a new element feature converter to the Data API, all that needs to be done is to first create a class representing the converter of the new element feature which extends the AbstractElementConverter and implements the ElementConverter interface. After this class is created, all that needs to be done is for the name of the new element converter class to be added to the ConverterNames properties file. Once this is done, the new element converter will be used to convert the new authoring environment elements into backend game engine objects. If the new element has multiple backend implementations such as Obstacle or Projectile game engine classes, the ElementFactory class can be used in order to instantiate the new element. In order to use the ElementFactory class correctly, an entry mapping the authoring environment type name of the element to the name of the backend class implementation must be added to the ElementConverter properties file.

### Networking
To display any new item, new a `ServerMessage` and `ClientMessage` need to be added. The server message needs to add the newly stored option to a given manager. For example, if an avatar wished to be stored, the `Account` object would also need to hold an avatar object. The `AccountManager` would then need an `addAvatar(Avatar av)` method. Then on the front end, there would need to be a place to display the avatar along with an `updateAvatar(Avatar ava)` method.

To expand the network to things like networked gaming would required setting up a new design pattern on the backend that can keep track of multiple games simultaneously. This would require setting up a `GameManager`. Additionally, a new server message would need to be added that says `SendAttackerMesssage` and a client message would need to be added called `AddAttackerMessage`. These messages would then be how users send attackers to each other. This would also require the social center to send data to the player. This change would require an observer design pattern where a player interface with a method called `addAttacker()` is present.