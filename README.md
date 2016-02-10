# layeredTestingExamples
This project should show to to test a web application at muliple 'levels': unit, rest (local), installed rest (desktop installer or cloud), installed GUI

Unit: `gradle test`
- Junit tests in /src/test/java

Rest: `gradle restTest`
- Junit tests in /src/restTest/java

Create Installer: `gradle installer`
- Create installer using install4j/install...something.  whatever is free.

Local Install Installer: `gradle installLocally`
- Install the installer file to the local pc

Publish to cloud: `gradle publishToCloud` // no idea
- Publish the war to a cloud server.

Deployed Integration: `gradle deployedIntTest`
- Run E2E tests against the deployed server. (Desktop after running installer, or cloud after war is deployed).
