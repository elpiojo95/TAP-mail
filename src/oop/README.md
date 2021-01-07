How to use CLI:
the command help will show you all the commands you can use and wich parameters you have to complete.
it have two modes: ADMIN and USER

when you started the CLI you are in ADMIN mode, and you can type this commands
- createuser <username> <Name> <DD/MM/YYYY> : this command will create a user in the system.
- filter contains <word> : will show you all the messages in the system that contains this word
- filter lessthan <n> : will show you all the messages in the system that have less than n words
- swap : will change the storage Type File to memory or vice versa
- logas <username> : will log as this user only if this user has been created before. If you use this command, it will change to USER mode.
- exit : will end the program

When you Type logas command and changes to USER mode, you can type this commands:
- send <to> -s "subject" -b "body" : to send a message to another user
- update : to update the list of messages in your mailbox
- list : to read this messages
- sort sender :  to sort this list by sender
- sort time : to sort this list by creation time
- filter subject <word> : filter the messages in your mailbox that contains the word on subject
- filter seder <username> : filter the messages in your mailbox by sender username
- exit : will go back to the ADMIN mode