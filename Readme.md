<h1 align="left"> Android Automation Framework </h1>

Basic POM framework with additional support like retry mechanism and running tests on sauce labs. The `SAUCE_USERNAME` and `SAUCE_ACCESS_KEY` are kept as environment variables. The desired capabilities are read form `AndroidCapabilities.json`. The objective here is to allow for scale. It's easy to add/remove/update any device from the json file and it keeps the driver class readable.

To run it locally, you can change the `RUN_ON_CLOUD` to `NO`. Additionally, if you want to retry after a test fails update the `RETRY_COUNT` in `config.properties`. The other approach to run on local/cloud was to create separate xml files, but I felt the current approach would allow for easier integration with CI.

To separate data from setup/tests I have used a `Constants` file.

<h3 align="left"> How to execute a test </h3>

* Run the following command it will execute the tests, generate a report and open it.

~~~
mvn test && allure generate --clean && allure open
~~~

* You can also run the tests by selecting `RunTests.xml` and running it using the TestNG runner.

<h3 align="left"> Project Structure </h3>

The `src` folder contains all packages related to the tests

* Driver folder contains the DriverManager class which starts the appium server and instantiates the driver

* PageObjects folder contains classes that handle all page related details like page elements and actions against those elements

* Resources folder contains test data and config required to run the tests

* Utilities folder has framework helpers like reusable actions (CommonMethods) 

* Listeners folder has all test listeners in use like reporting and retry mechanism 


<h3 align="left"> Reporting </h3>

Allure report is used for reporting. The setup done is very basic and the report generated is mostly empty. The objective here was to integrate some reporting framework which could be utilised once the framework scales.

<h4 align="left"> What would I improve if I spent more time on this task </h4>

* Improve reporting 
* Create a Jenkinsfile
