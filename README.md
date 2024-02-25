Application reads json file with ticket list, and calculate skewness in price array. 
In addition, it can find faster fly time for every Carrier.
To build application, run:

`mvn clean package`

In `/target` directory you will find `best-tickets-1.0-SNAPSHOT-jar-with-dependencies.jar`

Copy this `jar` and `tickets.json` to preferable directory, with terminal, go to this directory and run:

`java -jar best-tickets-1.0-SNAPSHOT-jar-with-dependencies.jar`

