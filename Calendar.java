package com.shishir.samples;
/**********************************************
 * Created by Shishir Iyer on July 26, 2016
 * All code belongs to Shishir Iyer. Do not
 * duplicate!
 *
 * ============================================
 *
 * This is a class that can be used to
 * generate simple text calendars, like glance
 * calendars and month calendars.
 *
 * For example, printCalendar(Month.July, 2016)
 * prints the following output to console:
 *
 *     July 2016
 * Su Mo Tu We Th Fr Sa
 *                1  2
 * 3  4  5  6  7  8  9
 * 10 11 12 13 14 15 16
 * 17 18 19 20 21 22 23
 * 24 25 26 27 28 29 30
 * 31
 *
 * To run, type in a terminal the following:
 * javac Calendar.java
 * java Calendar
 *********************************************/
import java.util.Scanner;

public class Calendar {
    //method to print calendar of single month
    private static void printCalendar(Month month, int year) {
        int yearStart;
        String mm = month.name;
        System.out.println("\n    " + mm + " " + Integer.toString(year));
        System.out.println("Su Mo Tu We Th Fr Sa");

        //calculate start day for any year based on the current year
        int startDay = 5;
        if(year > 2016) {
            for(int i = 2017; i <= year; i++) {
                if((i / 4) % 25 == 1 && (i / 4) % 4 != 1) {
                    startDay++;
                } else if(i % 4 == 1) {
                    startDay += 2;
                } else {
                    startDay++;
                }
            }
        } else if(year < 2016) {
            for(int i = 2015; i >= year; i--) {
                if((i / 4) % 25 == 0 && (i / 4) % 4 != 0) {
                    startDay--;
                } else if(i % 4 == 0) {
                    startDay -= 2;
                } else {
                    startDay--;
                }
            }

            while(startDay < 0) {
                startDay += 7;
            }
        }

        //make sure yearStart stays within 0 - 7
        yearStart = startDay % 7;

        //calculate the start day for any month based on the start day of the year
        int monthStart = (year % 4 != 0 || mm.equalsIgnoreCase("January") || mm.equalsIgnoreCase("February")) ? month.getAbsoluteStartDate(yearStart) : month.getAbsoluteStartDate(yearStart) + 1;
        monthStart = (monthStart < 0) ? (monthStart + 7) : ((monthStart >= 7) ? (monthStart - 7) : (monthStart)); // make sure monthStart stays within 0 - 7

        //how many days each month has
        int numDays = (year % 4 == 0 && mm.equalsIgnoreCase("February")) ? month.numDays + 1 : month.numDays;

        //print the first line
        if(monthStart != 0) {  //print spaces if necessary
            int i = 0;
            while(i < monthStart) {
                System.out.print("   ");
                i++;
            }

            for(int j = 1; j <= 7 - monthStart; j++) {
                System.out.print(Integer.toString(j) + "  ");
            }
        } else {
            for(int i = 1; i <= 7; i++) {
                System.out.print(Integer.toString(i) + "  ");
            }
        }

        System.out.println();

        //print the rest of the calendar
        for(int i = 8 - monthStart; i <= numDays; i++) {
            if((monthStart == 0 && i % 7 == 0) || i % 7 == 7 - monthStart){
                if(i < 10) {
                    System.out.println(Integer.toString(i) + "  ");
                } else {
                    System.out.println(Integer.toString(i) + " ");
                }
            } else if(i % 7 != 7 - monthStart) {
                if(i < 10) {
                    System.out.print(Integer.toString(i) + "  ");
                } else {
                    System.out.print(Integer.toString(i) + " ");
                }
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String month = input.next();
        int year = input.nextInt();
        input.close();
        
        printCalendar(Month.getValue(month), year);
    }
}
