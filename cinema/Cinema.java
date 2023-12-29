package cinema;

import java.util.Objects;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int numRows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seatsRow = scanner.nextInt();

        String[][] cinema = new String[numRows][seatsRow];

        generateCinema(-1, -1, cinema);

        do {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    printCinema(cinema,numRows,seatsRow);
                    break;
                case 2:
                    buyTicket(numRows,seatsRow,cinema);
                    break;
                case 3:
                    statistics(cinema, numRows,seatsRow);
                    break;
                case 0:
                    return;
                default:
            }
        } while (true);

    }
    static void statistics(String[][] cinema, int rows, int seats)    {
        
        //counting number of purchased tickets, current income and total income.
        int numPurchasedTickets = 0;
        int currentIncome = 0;
        int totalIncome = 0;

        for(int i = 0; i < cinema.length; i++)  {
            for(int j = 0; j < cinema[i].length; j++)   {
                if(Objects.equals(cinema[i][j], "B")) {
                    numPurchasedTickets++;
                    currentIncome = getPrice(rows,seats,i+1) + currentIncome;
                }
                    totalIncome = getPrice(rows,seats,i+1) + totalIncome;
            }
        }
        
        float percentage = ((float) numPurchasedTickets / (rows * seats) * 100);

        System.out.println();
        System.out.printf("Number of purchased tickets: %d \n", numPurchasedTickets);
        System.out.printf("Percentage: %.2f%% \n", percentage);
        System.out.printf("Current income: $%d \n", currentIncome);
        System.out.printf("Total income: $%d \n", totalIncome);
        System.out.println();
    }

    static void buyTicket(int numRows, int seatsRow, String[][] cinema) {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println();
            System.out.println("Enter a row number:");
            int rowNum = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatNum = scanner.nextInt();
            if(rowNum > numRows || seatNum > seatsRow)  {
                System.out.println("Wrong input!");
                continue;
            }

            if (Objects.equals(cinema[rowNum - 1][seatNum - 1], "B")) {
                System.out.println("That ticket has already been purchased!");
            } else {
                generateCinema(rowNum, seatNum, cinema);
                System.out.println("Ticket price: $" + getPrice(numRows, seatsRow, rowNum));
                System.out.println();
                break;
            }
        }while(true);
    }

    static int getPrice(int rows, int seats, int numRow)  {
        if ((rows * seats) <= 60) {
            return 10;
        } else if ((rows * seats) > 60) {
            if ((rows % 2) == 0)    {
                if (numRow > (rows/2))  {
                    return 8;
                } else {
                    return 10;
                }
            } else {
                if (numRow > 4)  {
                    return 8;
                } else {
                    return 10;
                }
            }
        }
        return 0;
    }

    static void generateCinema(int rowNum, int seatNum, String[][] cinema)  {

        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                if (rowNum - 1 == i && seatNum - 1 == j) {
                    cinema[i][j] = "B";
                } else if(!Objects.equals(cinema[i][j], "B")) {
                    cinema[i][j] = "S";
                }
            }
        }
    }
    static void printCinema(String[][] cinema,int rows, int seats) {
        System.out.println();
        System.out.println("Cinema:");
        System.out.print("  ");

        for (int i = 0; i < seats; i++) {
            System.out.print(i + 1 + " ");
        }

        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < seats; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}