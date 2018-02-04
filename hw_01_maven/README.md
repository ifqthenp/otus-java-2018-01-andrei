# HW 1. Maven Project setup

- Use Google Guava or Apache Commons libraries of your choice to process
input data
- Set the name of the project in pom.xml
- Build <project_name>.jar (fat jar) containing all dependencies
- Run application from the command line
- Push project to the GitHub repository
- Create 'obfuscation' branch and modify pom.xml so that source code is
obfuscated when the project is built

1. Type ```mvn clean package``` to build the project.
2. Type ```java -jar <project_name>.jar``` to run executable jar.
3. Switch to ```obfuscation``` branch and build the project again to get
the jar with obfuscated java classes.
