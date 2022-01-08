import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.DataTruncation;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.io.IOException;
import jam.FlightDeparturesMap.FlightDeparturesMap;
import jam.Util.CSVParser;
import jam.Util.DateUtil;
import jam.Util.Flight;
import jam.Util.FlightData;

public class Main
{
   public static void main(String[] args) throws IOException
   {
      String RawCSVData = Files.readString(Path.of("Data\\Flights.csv"));
      CSVParser.ParseCSV(RawCSVData);

      String AirNumber = "TB8171";
      
      System.out.println("Date of Flight: " + (FlightData.Flights.get(AirNumber).DateOfFlight));
      System.out.println("Departure Time: " + (FlightData.Flights.get(AirNumber).DepartureTime));
      System.out.println("Arrival Time: " + (FlightData.Flights.get(AirNumber).ArrivalTime));
      System.out.println("Flight Duration: " + DateUtil.DurationToString(FlightData.Flights.get(AirNumber).FlightDuration));
      System.out.println("Distance Traveled: " + FlightData.Flights.get(AirNumber).DistanceTraveled);
      System.out.println("Delay: " + FlightData.Flights.get(AirNumber).Delay.toMinutes());
      System.out.println("Departure Airport: " + FlightData.Flights.get(AirNumber).DepartureAirport);
      System.out.println("Departure City: " + FlightData.Flights.get(AirNumber).DepartureCity);
      System.out.println("Arrival Airport: " + FlightData.Flights.get(AirNumber).ArrivalAirport);
      System.out.println("Arrival City: " + FlightData.Flights.get(AirNumber).ArrivalCity);
      System.out.println("Flight Number: " + FlightData.Flights.get(AirNumber).FlightNumber);
      System.out.println("Airline: " + FlightData.Flights.get(AirNumber).Airline);


      FlightData.FlightsToday = new HashMap<String, Flight>();
      LocalDateTime currenttime = LocalDateTime.now();
      Iterator<Entry<String, Flight>> entrySet = FlightData.Flights.entrySet().iterator();
      while (entrySet.hasNext())
      {
         Entry<String, Flight> entry = entrySet.next();
         
         LocalDateTime datetime = LocalDateTime.of(entry.getValue().DateOfFlight.getYear(),entry.getValue().DateOfFlight.getMonth(), entry.getValue().DateOfFlight.getDayOfMonth(),   entry.getValue().DepartureTime.getHour(), entry.getValue().DepartureTime.getMinute(), 0);
         long time = currenttime.until(datetime, ChronoUnit.HOURS);

         if(time < 24 && time>0){
            FlightData.FlightsToday.put(entry.getKey(), entry.getValue());
         }
      

      }
      

      FlightDeparturesMap.InitWindow();
   }
}

