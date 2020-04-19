import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        // type MM yyyy
        Scanner in = new Scanner(System.in);
        System.out.println("Press 1 - View Calender of any Month you specify \nPress 2 - View Current Date & Time \nPress 3 - View Current Date\nPress 4 - View Current Time");
        int input = in.nextInt();
        if (input == 1) {
            System.out.print("Enter month and year: MM yyyy ");
            int month = in.nextInt();
            int year = in.nextInt();
            // checks valid month
            try {

                if (month < 1 || month > 12)
                    throw new Exception("Invalid index for month: " + month);
                printCalendarMonthYear(month, year);
            } catch (Exception e) {
                System.err.println(e.getMessage());

            }
            System.out.println("");

        }
        else if (input == 2) {
            System.out.println("#### Date&Time ####");
            displayCurrentTimeAndDate();
        } else if(input == 3) {
            System.out.println("#### Date ####");
            displayCurrentDate();
        } else if(input == 4) {
            System.out.println("#### Time ####");
            displayCurrentTime();
        } else {
            System.out.println("Please enter one of the listed numbers");
        }

        in.close();

    }

    private static void printCalendarMonthYear(int month, int year) {
        Calendar cal = new GregorianCalendar();
        cal.clear();
        cal.set(year, month - 1, 1); // setting the calendar to the month and year provided as parameters
        System.out.println("Calendar for " + cal.getDisplayName(Calendar.MONTH, Calendar.LONG,
                Locale.US) + " " + cal.get(Calendar.YEAR));//to print Calendar for month and year
        int firstWeekdayOfMonth = cal.get(Calendar.DAY_OF_WEEK);//which weekday was the first in month
        int numberOfMonthDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH); //length of days in a month
        printCalendar(numberOfMonthDays, firstWeekdayOfMonth);
    }

    private static void printCalendar(int numberOfMonthDays, int firstWeekdayOfMonth) {
        int weekdayIndex = 0;
        System.out.println("Su  MO  Tu  We  Th  Fr  Sa"); // The order of days depends on your calendar

        for (int day = 1; day < firstWeekdayOfMonth; day++) {
            System.out.print("    "); //this loop to print the first day in his correct place
            weekdayIndex++;
        }
        for (int day = 1; day <= numberOfMonthDays; day++) {

            if (day < 10) // this is just for better visualising because unit number take less space of course than 2
                System.out.print(day + " ");
            else System.out.print(day);
            weekdayIndex++;
            if (weekdayIndex == 7) {
                weekdayIndex = 0;
                System.out.println();
            } else {
                System.out.print("  ");
            }
        }
    }

    private static void displayCurrentTimeAndDate() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println(formattedDate);
    }


    private static void displayCurrentDate() {
        LocalDate myCurrentDate = LocalDate.now(); // Create a date object
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        String formattedTime = myCurrentDate.format(myFormat);
        System.out.println(formattedTime);
    }

    private static void displayCurrentTime() {
        LocalTime myCurrentTime = LocalTime.now(); // Create a date object
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = myCurrentTime.format(myFormat);
        System.out.println(formattedTime);
    }
}

