<h1 align="left"> Android Automation Framework </h1>

Basic POM framework with additional support like retry mechanism and running tests on sauce labs. The `SAUCE_USERNAME` and `SAUCE_ACCESS_KEY` are kept as environment variables. The desired capabilities are read form `AndroidCapabilities.json`. The objective here is to allow for scale. It's easy to add/remove/update any device from the json file and it keeps the driver class readable.

To run it locally, you can change the `RUN_ON_CLOUD` to `NO`. Additionally, if you want to retry after a test fails update the `RETRY_COUNT` in `config.properties`.

To separate data from setup/tests I have used a `Constants` file.

<h3 align="left"> How to execute a test </h3>

* Run the following command it will execute the tests, generate a report and open it.

~~~
mvn test && allure generate --clean && allure open
~~~

* You can also run the tests by selecting `RunTests.xml` and running it using the TestNG runner.
