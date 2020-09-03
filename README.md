# School-management-Project-JavaFx

This is a pure JavaFx project that handle QCM Tests for Students and Statistics for Professors


in this project i tried to give two seperated interfaces for the same application using Java and JavaFx for better user interface experience.

  # Student
  
  * The Student window give data from the database that is already installed in the server and setted  by the professor in its own window.
  * When A student finish the test he has the choice to return back or continu in order to see the result that will be stored automaticaly in the database.
  * I tried to give  more enthusiastic experience by adding a Video part where the student can hear and answer (the video could be aploaded by the professor)
  * More Animations are added  using Css for better UX.
  
  
  # Professor
  
  In this part i worked on the global window After the authentication so this window give a global idea about the Authority that I gave to the professor
  
  * The professor Can add or delete a student from the database so he cannot log in anymore
  * The professor Can see the list of all students with (or without) the index and the last received Score of the recent passed test.
  * The professor Can see The number of questions / Students / Tests in a better way as Charts // Graphs.
  * The professor Can add/delete Questions 
  * The professor Can apload a video from his own Server (Computer usb ...) this video would be  copied in a specific location and stored in a table with the     
    index of last video +1.
  * More to discover ..
  
  
  # Usage 
  
  1. This application is a Maven Javafx Project So you need to download all the maven repository;
  2. Execute the .sql file that you'll find in  [MonApp/libs]
  3. Make sure that you use Java8 (1.8.xxx)
  4. for better experience try to install the database and Run the following command to see the stored Users with the password  
   
   ==> use quiz_app_db;
      >show tables;
      >select * from user_professeur;
      >select * from user_students;
      >exit   
   
   # Libraries
   
  FontAwesome
  JFoenix
  
  
