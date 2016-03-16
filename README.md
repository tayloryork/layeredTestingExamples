# layeredTestingExamples
This project should show to to test a web application at muliple 'levels': unit, rest (local), installed rest (desktop installer or cloud), installed GUI

# Travis-CI
[![Build Status](https://travis-ci.org/tayloryork/layeredTestingExamples.svg?branch=master)](https://travis-ci.org/tayloryork/layeredTestingExamples)

Unit Tests Layer: `gradle test`
- Junit tests in /src/test/java

Rest/Api Tests Layer: `gradle restTest`
- Starts a very fast Jersey JettyTestContainer
- Then runs Junit tests in /src/restTest/java

DB Unit Tests Layer: `gradle dbTest`
- Starts an in memory DB
- Then runs Junit tests in /src/dbTest/java

Mocked Dep-E2E Tests Layer: `gradle mockedDepsE2ETest`
- Real UI
- Starts a full Jetty server for our Container
- Mocked out BIG dependencies.
- Heavy Dependency (DB, Ldap, etc) setup for each test or test suite.
- Then runs Junit tests in /src/localE2ETest/java

E2E Tests Layer: `gradle E2ETest -host myVm1`
- Real UI
- Runs againts and INSTALLED server / cloud deployment.
- Very correct communication between all components.
- Can be any host. localhost:8080, myVm1:8080, etc.
- Minimal Dependency changes for tests. (Avoid inserting into the db)
- Then runs Junit tests in /src/deploymentE2ETest/java
- NOTHING mocked.

Todo:
- [x] Create Unit tests
- [x] Create Unit test gradle task
- [x] Create Rest tests
- [x] Create Rest test gradle task
- [x] Create Integration tests
- [x] Create Integration test gradle task
- [x] Create installer & Gradle task
- [x] Install the Installer
- [x] Implement Sut Propery files (System Under Test ~ which server & port are you testing against)
- [x] Split "Local Integration" and "Deployed Integration" tests.
- [x] Local Integration Tests are tests run against Jetty/gradle.
- [x] Deployed Integration Tests are run against an Installed/Deployed server.

Create Installer: `gradle installer`
- Create installer using install4j/installAnywhere/izpack...something.  whatever is free.

Locally Install App: `gradle runInstallLocally`
- Install the installer file to the local environment

Start the installed server: `gradle startLocalInstall`
- Execute C:\Progam Files\layeredTestingExamples\bin\start.bat or whatever we have.

Run "Deployed" Integration Tests: `gradle deployedIntegrationTest -Duut.propfile=localhost.properties`
- Run the integration tests against the "Deployed" Server, ie, the server which has our app installed to it

We could maybe even publish to a cloud provider instead of creating an installer.
Publish to cloud: `gradle publishToCloud` // no idea
- Publish the war to a cloud server.

Cloud Integration Tests: `gradle deployedCloudIntTest`
- Run E2E tests against a server deployed to a cloud provider.
