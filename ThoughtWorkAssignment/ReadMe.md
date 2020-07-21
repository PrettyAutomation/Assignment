### Question1 :

#### a) To create the Palindrome with given input

1) The program is written in java language
2) The Main method is written in CreatePalindromeTest (ThoughtWorkAssignment/src/test/java/CreatePalindromeTest.java)
3) Util methods are written in PalindromeUtil class (ThoughtWorkAssignment/src/main/java/util/PalindromeUtil.java)
4) User is providing w (length of word), x (no. of alphabet), y (no. of digit) and z(no. special char) need to use to
   create palindrome using palindrome_Word_Formed(w,x,y,z); method called in main function

#### b) Write Test Scenario for the palindrome logic given

1) I have written possible Test scenario in the excel sheet Palinddropm_HLTC_v0.1.xlsx
   Inside the Resource folder (ThoughtWorkAssignment/src/main/resources/Palinddropm_HLTC_v0.1.xlsx)


### Question 2

#### High Level Framework design with Automation script for given scenario

#### Project structure: Maven Project : Utility classes, Resources, driver, config, are added in main/java package and Test classes are added in test/java package

// The test scenario which has been taken is :

1) Open Amazon url
2) Login with username and password
3) Search the watches for women
4) pick the item which is contains denial brand
5) increase the quantity to 2 and buy now
6) Select the payment method as upi
7) enter the upi and verify if it is incorrect print and close the browser
8) if upi is correct check verified is displayed and submit the order

//

1) Automation Script is written using selenium web driver with java
2) TestNG framework is used to create the test
3) Util methods are written in the TestBase class (ThoughtWorkAssignment/src/main/java/util/TestBase.java)
4) DataProvider used to test by using multiple username and password by reading data from excel (ThoughtWorkAssignment/src/main/java/config/Data_v0.1.xlsx)
5) To get the environment variable like browser and url used property file  (ThoughtWorkAssignment/src/main/java/config/config1.properties)
6) As selenium does not support captcha i did not try that scenario (although we can achieve it by using external api)
7) ExtentReporterNG class is added to generate Extent Report using testng.xml file and saved in test-ouput folder
8) driver executable files are stored in driver package
9) To do cross browser testing given option to select the browser in initialize method.
