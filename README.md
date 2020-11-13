# client-transaction-reporting
It is mainly developed to
 - Read the Future Transactions done by client from flat file.
    Note - In future, File can be read by defing a xml schema for a record. https://jrecordbind.org/
 - Process the file and format it.
 - Write the file to output file output.csv.

Currently The scheduler is schduled at frequency of 1 minute to generate Output.csv. However, it(cron expression) can be changed to daily frequency.

 This project uses SOLID principle.

#Setup.
It is a stand alone Java project. To run this project , you require the following componets/software on your machine.
* Java 8+
* Git
* Maven
* IDE - (intellij or eclipse)

Steps
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

#Spec and Requirements-
Project Requirement Specifications can be found in /src/main/resources.

