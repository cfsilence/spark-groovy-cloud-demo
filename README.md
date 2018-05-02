# spark-groovy-cloud-demo

There is not much to this application.  The purpose of it is to provide a simple application for demo purposes when deploying to the cloud via various services.

To create a "fat" jar, run the `gradle jar` task.  To package the fat jar into a zip with a `manifest.json` for use in deploying to Oracle's Application Container Cloud Service run `gradle packageOracle`.

There are three Gradle targets for running the application:
 
* `dev`
* `qa`
* `prod`

Each have a config file related to them for necessary, well, config information.  Check out /src/main/groovy/conf/config-template.groovy.  If they don't exist, create 3 files in that directory:

* `config-dev.yml`
* `config-qa.yml`
* `config-prod.yml`

And modify the contents as appropriate.

To run the service, type one of the following commands:

* `gradle runDev`
* `gradle runQa`
* `gradle runProd`

And the service will pick up the appropriate config at runtime.  You can also use IntelliJ IDEA's Gradle plugin to run/debug those tasks.
