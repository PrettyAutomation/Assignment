### Scenario

* Open the Flipkart portal from the link https://www.flipkart.com
* “Login to Flipkart portal and search for an item and add to cart and purchase it and logout”
* Details of data to be tested: search for a Camera and purchase a random (not the first/last) Camera from the search result
* Verify information (name/price/description...) on product search screen, comparing that info between product search screen vs checkout screen
* Must use assertion during verification
* TC must pass/fail base on assertion
* Handle screen rotation efficiently.

### Detail about the Automation project

* Automation Script is written using selenium web driver with java
* TestNG framework is used to create the test
* driver executable files are stored in driver package
* Util methods are written in the TestBase class
* To do cross browser testing given option to select the browser in initialize method.
* DataProvider used to test by using multiple username and password by reading data from excel
* To get the environment variable like browser and url used property file
* ExtentReporterNG class is added to generate Extent Report using testng.xml file and saved in test-ouput folder


### FeedBack Collaborated

1) implemented page object model
2) Added the switch case for cross browser testing
3) Implemented the grid setup the hub and node creation bat file added in resource folder
   go to terminal and inside the project directory and run sh hub.bat and in another terminal run sh node.bat command
   then run the test case by adding gridsetup method instead of initialize method.


