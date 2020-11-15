# client-transaction-reporting
#Purpose
It is mainly developed to
 - Read the Future Transactions done by client from flat file.
    Note - In future, flat File can be read by defing a xml schema for a record. https://jrecordbind.org/
 - Process the file and format it.
 - Write the file to output file output.csv.

#Technical Details and Design Highlights.
    * ClientReportScheduler is main entry point that will trigger the reporting job. Currently The scheduler is schduled at frequency of 1 minute to generate Output.csv. However, it(cron expression) can be changed to daily frequency.
    * The solution complies with software engineering design principles. Like the Single Responsibility principle, Interface segregation principle (Dependency Injection)
    3. Code is not tightly coupled. For instance you can introduce a new writer class. (XML writer in future) and current design will still work. 
    4. The solution is having clean, Well documented, Clear logging, optimized code with Unit testing coverage and clear comments and documentation.
    5. The Solution is based on latest java8 stream API.
    6. The solution keeps maximum data immutable. Increasing performance.
    7. The solution uses centralized constants and configuration, making is flexible. The feature can be easily available in production without any deployment.
Note - the Requirement says that input flat file contains future transactions for client 1234. But actually, it contains data for other clients as well.
So the solution has an addition feature (flag), which will allow the user to generate the report for all the client OR ONLY for client 1234, without any code change and without any deployed.

#Softwares requirement.
It is a stand alone Java project. To run this project , you require the following componets/software on your machine.
* Java 8+
* Git
* Maven
* IDE - (intellij or eclipse)

# Installation and execution Steps
1. Clone the code into your IDE
2. Go to terminal and build the project by using, MVC clean install.
3. Wait for a minute and after that following can be found on console logs
  2020-11-13 18:14:00.005  INFO 4044 --- [   scheduling-1] c.a.t.scheduler.ClientReportScheduler    : Scheduler started
2020-11-13 18:14:00.005  INFO 4044 --- [   scheduling-1] c.a.t.main.ReportingServiceImpl          : Transaction Reporting :: Started
2020-11-13 18:14:00.005  INFO 4044 --- [   scheduling-1] c.a.t.services.reader.FileReaderImpl     : Reading Input file :: Started
2020-11-13 18:14:00.093  INFO 4044 --- [   scheduling-1] c.a.t.services.reader.FileReaderImpl     : Reading Input file :: Finished
2020-11-13 18:14:00.093  INFO 4044 --- [   scheduling-1] c.a.t.s.processor.ReportProcessorImpl    : Report Formatting :: Started
2020-11-13 18:14:00.109  INFO 4044 --- [   scheduling-1] c.a.t.s.processor.ReportProcessorImpl    : Report Formatting :: Finished
2020-11-13 18:14:00.109  INFO 4044 --- [   scheduling-1] c.a.t.services.writer.CSVWriterImpl      : Writing Output file :: Started
2020-11-13 18:14:00.117  INFO 4044 --- [   scheduling-1] c.a.t.services.writer.CSVWriterImpl      : Writing Output file :: Finished
2020-11-13 18:14:00.117  INFO 4044 --- [   scheduling-1] c.a.t.main.ReportingServiceImpl          : Transaction Reporting :: Finished
2020-11-13 18:14:00.117  INFO 4044 --- [   scheduling-1] c.a.t.scheduler.ClientReportScheduler    : Scheduler Finished

#Output
The output.csv is generated at root level. Can be seen futher.


#Documentation
All services and components have proper comments for devs to understand logic and maintain the software in future.


#Spec and Requirements-
Project Requirement Specifications can be found in /src/main/resources.

