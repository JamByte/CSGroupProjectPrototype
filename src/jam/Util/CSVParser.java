package jam.Util;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class CSVParser {
    public static void ParseCSV(String CSVData)
    {
        String[] CSVDatas = CSVData.split("\n"); 
        HashMap<String,Flight> Data =  new HashMap<String, Flight>();
        for(int i=0; i < CSVDatas.length;i++)
        {
            Flight flight= new Flight();
            String[] CSV = CSVDatas[i].split(",");
            //Date of flight
            String[] DateSpilt = CSV[0].split("/");
            //parse the year month and day
            flight.DateOfFlight = LocalDate.of(Integer.parseInt(DateSpilt[2]),Integer.parseInt(DateSpilt[1]),Integer.parseInt(DateSpilt[0]));
            //Parse Departure,arrival and duration
            String[] timeSpiltDep = CSV[1].split(":");
            String[] timeSpiltArr = CSV[2].split(":");
            String[] timeSpiltDur = CSV[3].split(":");
            flight.DepartureTime = LocalTime.of(Integer.parseInt(timeSpiltDep[0]), Integer.parseInt(timeSpiltDep[1]),0);
            flight.ArrivalTime =LocalTime.of(Integer.parseInt(timeSpiltArr[0]), Integer.parseInt(timeSpiltArr[1]),0);
            
            flight.FlightDuration= Duration.ofMinutes((Long.parseLong(timeSpiltDur[0])*60) + Long.parseLong(timeSpiltDur[1]));

            //Distance Traveled
            flight.DistanceTraveled = Float.parseFloat(CSV[4]);

            //Delay mins
            flight.Delay =  Duration.ofMinutes(Long.parseLong(CSV[5]));

            //citys and airports and number and airline 
            flight.DepartureAirport =  CSV[6];
            flight.DepartureCity =  CSV[7];
            flight.ArrivalAirport =  CSV[8];
            flight.ArrivalCity =  CSV[9];
            flight.FlightNumber =  CSV[10];
            flight.Airline =  CSV[11];

            Data.put(CSV[10], flight);
        }
        FlightData.Flights = Data;
    }
}
