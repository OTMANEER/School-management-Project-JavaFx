-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: Jun 27, 2020 at 06:33 PM
-- Server version: 8.0.18
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quiz_app_db`
--
CREATE DATABASE IF NOT EXISTS `quiz_app_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `quiz_app_db`;

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
CREATE TABLE IF NOT EXISTS `questions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(800) NOT NULL,
  `reponse1` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `reponse2` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `reponse3` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `reponse4` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type` int(11) NOT NULL,
  `solution` varchar(200) NOT NULL,
  `createur` varchar(92) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=148 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`id`, `question`, `reponse1`, `reponse2`, `reponse3`, `reponse4`, `type`, `solution`, `createur`) VALUES
(143, '// What is the result:        \n public class SumTest{\n            public static void doSum(Integer x,Integer y) {\n                System.out.println(\"Integer sum is \"+(x+y));\n            }\n            public static void doSum(double x,double y) {\n                System.out.println(\"double sum is \"+(x+y));\n            }\n            public static void doSum(float x,float y) {\n                System.out.println(\"float sum is \"+(x+y));\n            }\n            public static void doSum(int  x,int y) {\n                System.out.println(\"int sum is \"+(x+y));\n            }\n\n            public static void main(String[] args) {\n                doSum(10,20);\n                doSum(10.0,20.0);\n            }\n\n        }', 'int sum is 30    float sum is 30.0', 'int sum is 30    double sum is 30.0', 'Integer sum is 30    double sum is 30.0', 'Integer sum is 30    float sum is 30.0', 1, 'int sum is 30    double sum is 30.0', 'younes'),
(142, 'What is the result of this code:\n\nSystem.out.println(\"5+2= \"+3 + 4);\nSystem.out.println(\"5+2= \"+(3 + 4));\n', '5+2 = 34; 5+3=34;', '5+2 = 3+4; 5+2 = 7;', '7=7; 7=7;', '5+2 = 34; 5+2=7;', 1, '5+2 = 34; 5+2=7;', 'younes'),
(141, 'Choose a True Statement (One is enough):\n//Acc.java\npackage p1;\npublic class Acc{\n    int p;\n    private int q;\n    protected int r;\n    public int s;\n}\n//Test.java\npackage p2;\nimport p1.Acc;\npublic class Test extends Acc{\n    public static void main(String[] args) {\n        Acc obj = new Test();\n    }\n    \n}', 'Both p and s are accessible by obj.', 'Only s is accessible by obj.', 'Both r and s are accessible by obj', 'p, r, and s are accessible by obj.', 1, 'Only s is accessible by obj.', 'younes'),
(140, 'What is the result :\n\npublic class test {\n    public static void main(String[] args) {\n        if(args[0].equals(\"hello\")?false:true){\n            System.out.println(\"Success\");\n        }else{\n            System.out.println(\"Failure\");\n        }\n    }\n}', 'Success', 'Failure', 'Compilation fails.', 'An exception is thrown at runtime', 1, 'Failure', 'younes'),
(139, 'Which code should you add to print 10:20 ?\npublic class test {\n    public static void main(String[] args) {\n        /*insert code here*/\n        array[0]=10;\n        array[1]=20;\n        System.out.println(array[0]+ \" : \"+array[1]);\n    }\n}\n', ' int [] array  = new int[2];', ' int[] array; array = int [2];', ' int array = new int [2];', ' int array[2];', 1, ' int[] array; array = int [2];', 'younes'),
(138, ' Which code should you use at line 1 to instantiate dvd object successfully\n    class CD {\n        int r;\n        CD(int r){\n            this.r=r;\n        }\n    }\n    class DVD extends CD{\n        int c;\n        DVD(int r,int c){\n            //Line 1\n        }\n    }\n    and the given code fragment:\n    DVD dvd  = new DVD(10,20);', 'super.r = r; this.c = c; ', 'super(r) ; this(c);', 'super(r) ;this.c = c;', 'this.c = r; super(c);', 1, 'super(r) ;this.c = c;', 'younes'),
(128, 'Which three statements describe the object-oriented features of the Java language? (Choose One of Two )', 'Objects cannot be reused.', 'A subclass can inherit from a superclass.', 'Objects can share behaviors with other objects.', 'Object is the root class of all other objects.', 1, 'A subclass can inherit from a superclass.', 'younes'),
(127, 'Which code fragment when inserted enable the code to print true\n\n StringBuilder sb1 = new StringBuilder(\"Duke\");\n String str1 = sb1.toString();\n //isert code here\n        System.out.println(str1 == str2);', 'String str2 = str1;', 'String str2 = new String (str1);', 'String str2 = sb1. toString ();', 'String str2 = \"Duke\";', 1, 'String str2 = str1;', 'younes'),
(126, 'Which three are advantages of the Java exception mechanism? (Choose One of three.)', 'Provides a set of standard exceptions that covers all the possible errors', ' the programmer can choose where to handle exceptions', 'Allows the creation of new exceptions ', ' the error handling code is separated from the normal program function', 1, 'Provides a set of standard exceptions that covers all the possible errors', 'younes'),
(125, 'What is the result: \n    public static void main(String[] args) {\n        String[] arr = {\"A\",\"B\",\"C\",\"D\"};\n        for (int i=0;i<arr.length;i++)\n        {\n            System.out.println(arr[i]+ \" \");\n            if (arr[i].equals(\"C\"))\n            {\n                continue;\n            }\n            System.out.println(\"Work Done\");\n            break;\n        }\n    }', 'A B C Work done', 'A B C D Work done', 'A Work done', 'Compilation fails', 1, 'A Work done', 'younes'),
(124, 'What is the result:\n    class X{\n        static int i;\n        int j;\n\n        public static void main(String[] args) {\n            X x1 = new X();\n            X x2 = new X();\n            x1.i = 3;\n            x1.j = 4;\n            x2.i=5;\n            x2.j=6;\n            System.out.println(x1.i+ \"\"+\n                    x1.j + \" \"+\n                    x2.i+\" \"+\n                    x2.j);\n\n        }\n    }', ' 3 4 5 6', '3 4 3 6', '5 4 5 6', ' 3 6 4 6', 1, '5 4 5 6', 'younes'),
(123, 'What is the result:         \nint x = 100;\n        int a=x++;\n        int b=++x;\n        int c=x++;\n        int d = (a<b)?(a<c) ? (b<c)?b:c;\n        System.out.println(d);', '100', '101', '102', 'Compilation fails', 1, 'Compilation fails', 'younes'),
(122, 'What is the result: \n     public static void main(String[] args) {\n        int num = 5;\n        do {\n            System.out.println(num-- +\" \");\n        }while (num == 0);\n    }', '  5 4 3 2 1 0', '5 4 3 2 1', '4 2 1', '5', 1, '5', 'younes'),
(121, '    Which option can replace xxx to enable the code to print 135?\nint a[]={1,2,3,4};\n    for(XXX){\n        System.out.println(a[e]);', 'int e=0 ;e<=4; e++;', 'int e=0; e<5; e+=2;', 'int e=0; e<=5; e+=1;', 'int e=1; e<5; e+=2;', 1, 'int e=0; e<5; e+=2;', 'younes'),
(120, 'What is the result of this fragment:  \n  public static void main(String[] args) {\n    String ta = \"A \";\n      ta.concat(\"B \");\n    String tb = \"C \";\n    ta = ta.concat(tb);\n    ta.replace(\'C\',\'D\');\n    ta= ta.concat(tb);\n        System.out.println(ta);\n    }', 'A B C D', 'A C D', 'A B C C', 'A B D', 1, 'A B C C', 'younes'),
(117, 'What is the result:\n    public static void main(String[] args) {\n  		 Short s1 = 200;\n  		 Integer s2 = 400;\n 		  Long s3= (long) s1+s2; //Line 1\n 	 	 String s4=(String) (s3 * s4); // Line  2\n     	  	 System.out.println(\"Sum is \"+s4);\n    }', 'Sum is 600', 'Compilation fails at line n1.', 'Compilation fails at line n2.', 'A ClassCastException is thrown at line n1.', 1, 'Compilation fails at line n2.', 'younes'),
(118, 'What is the name of the Java concept that uses access modifiers to protect variables and hide them within a class?\n', 'Encapsulation', 'Inheritance', 'Abstraction', 'Instantiation ', 1, 'Encapsulation', 'younes'),
(119, ' Which two modifications, made independently, enable the code to compile? (Choose One.)\n   abstract class Planet {\n            protected void revolve(){ //Line 1\n            }\n    abstract void rotate();          //Line 2\n    }\n    class Earth extends Planet {\n            void revolve(){          //Line 3\n            }\n            protected void rotate() { //Line 4\n            }\n        }', 'Make the method at line n1 public.', 'Make the method at line n2 public.', 'Make the method at line n3 public. ', 'Make the method at line n4 public.', 1, 'Make the method at line n4 public.', 'younes'),
(116, 'What is the result: \n    public static void main(String[] args) {\n  String date = LocalDate\n                       .parse(\"2014-05-04\")\n                       .format(DateTimeFormatter.ISO_DATE_TIME);\n System.out.println(\"date\");\n    }', 'May 04, 2014T00:00:00.000', '2014-05-04T00:00: 00. 000', ' 5/4/14T00:00:00.000', ' An exception is thrown at runtime', 1, ' An exception is thrown at runtime', 'younes'),
(115, ' Which statement, if either were true, would make the code compile?\n        ArrayList<Vehicule> myList = new ArrayList<>();\n        myList.add(new Motorcycle());', 'Vehicle is an interface that is implemented by the Motorcycle class.', 'Vehicle is a superclass of Motorcycle.', 'Motorcycle is a superclass of Vehicle.', 'Motorcycle is an interface that implements the Vehicle class.', 1, 'Vehicle is a superclass of Motorcycle.', 'younes'),
(144, '1', 'The Java Persistence API', 'The query language', 'The Java Persistence Criteria API', 'Object/relational mapping metadata', 7, 'The Java Persistence API', 'younes'),
(145, '2', 'java.lang.String getURI()   Returns: HTTP method .', 'HttpMethod getMethod()  Returns: URI  .', 'java.lang.String getHeader(HttpHeader header) Returns: HTTP header value Always', 'HttpMessageHeaders getHeaders() Returns: the request headers.', 7, 'HttpMessageHeaders getHeaders() Returns: the request headers.', 'younes'),
(146, '3', ' Annotations can be used by the compiler to suppress warnings.', 'Software tools can process annotation information to generate code.', 'Some annotations are available to be examined at runtime.', 'XML files, and so forth Should be generated In the Runtime Processing', 7, ' Annotations can be used by the compiler to suppress warnings.', 'younes'),
(147, '4', 'Addressability Identifies all resources using a uniform resource identifier (URI). ', 'Stateless interaction Enables the access of a resource using a UI such as HTTP methods.', 'Cacheable Enables the Reliability of client responses.', 'Layered system (client connect to an itermediary server (with client\'s knowledge))', 7, 'Addressability Identifies all resources using a uniform resource identifier (URI). ', 'younes');

-- --------------------------------------------------------

--
-- Table structure for table `result_student`
--

DROP TABLE IF EXISTS `result_student`;
CREATE TABLE IF NOT EXISTS `result_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `test_id` int(11) NOT NULL,
  `score` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `result_student`
--

INSERT INTO `result_student` (`id`, `name`, `test_id`, `score`) VALUES
(65, 'soufiane ragragui', 7, ' 0/4'),
(64, 'soufiane ragragui', 1, ' 0/20'),
(63, 'soufiane ragragui', 1, ' 7/20');

-- --------------------------------------------------------

--
-- Table structure for table `user_professeur`
--

DROP TABLE IF EXISTS `user_professeur`;
CREATE TABLE IF NOT EXISTS `user_professeur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `Matiere` varchar(92) NOT NULL,
  `Mail` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user_professeur`
--

INSERT INTO `user_professeur` (`id`, `username`, `password`, `name`, `Matiere`, `Mail`) VALUES
(26, 'otmane', '0000', 'otmane ragragui', 'Informatique', 'otmane@usmba.ac.ma'),
(27, 'younes', '0000', 'younes lakhrisi', 'informatique', 'lakhrisi@test.ma');

-- --------------------------------------------------------

--
-- Table structure for table `user_student`
--

DROP TABLE IF EXISTS `user_student`;
CREATE TABLE IF NOT EXISTS `user_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user_student`
--

INSERT INTO `user_student` (`id`, `username`, `password`, `name`) VALUES
(24, 'soufiane', '0000', 'soufiane ragragui'),
(26, 'yassine', '0000', 'yassine bakhiyi ');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
