# AutoKeypresser

This software application can be used for repetitive actions concerning Keyboard input.<br />
It enables the user to an easy-to-use method of using custom keysets.<br />

Features:<br />
-Window at the top-left of the screen with all the options.<br />
-Start and Stop the automatic keypressing loop with two buttons.<br />
-Change the keys to a custom set of keys, with specified wait-times.<br />

Format:<br />
To add a custom key set the user has to specify a specific key set, using the following format:<br /><br />
E:[key] ->  Enable key, so that the Robot class will press the specified key.<br />

D:[key] ->  Disable key, so that the Robot class will release the specified key.<br />
            Without this the key that has been enabled before will not be disabled.<br />
            
W:[time] -> Wait in milliseconds, with this argument the user can specify how long the system has to wait in milliseconds before entering the next key.<br />
            It is important to use this function, since some applications need to keep up with the loop.<br />

Made by:<br />
Wout Haakman<br />
E-mail:   wouthaakman@hotmail.com<br />
Github:   HaakmanWout<br />
Reddit:   u/haakmanwout<br />
