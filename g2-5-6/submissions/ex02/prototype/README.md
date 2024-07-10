# Prototype of system's Java Command Line Interface (CLI)
## The User's Options
##### While a user navigates through the menu, they are expected to give an integer input. Depending on their response, the following operations might be called in the switch-case.
Option 1 : the operation calls the execute function in Command1.java. This works by printing a line in the console for the sole purpose of the user's understanding of the result of their command. Then, an object from the Command1 class is created and is used to call the execute method in that class.

Option 2 : this option works very similarly to the first option. An explanatory message to the user is printed. Consequently, an object of the Command2 class is created, and it calls the execute function in Command2.java

Option 3 : again, this operation works in parallel with the first two options. A message is printed to the console to inform the user of the operations being executed. The function calls the execute method in Command3.java via the command3 object created within the body.

Default: this alternative is added in case the user enters an input other than the expected ones, i.e "1", "2", or "3"

## Execute Method
This is a method in the classes Command1, Command2, and Command3. Essentially, the purpose of this method is to merely model the results of the user's selection. A message is printed in each of the "execute" functions, letting the user know that their chosen task has been executed. Later on, these functions will have proper implementation, according to the specified demands. However, in the prototype these functions act as a demonstration to inform the client, which is why they include several printing commands.  
