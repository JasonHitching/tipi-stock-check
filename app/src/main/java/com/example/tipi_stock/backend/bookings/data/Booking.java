package com.example.tipi_stock.backend.bookings.data;



import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

/**
 * Entity model of a structure booking
 */
@Entity(tableName = "booking_table")
public class Booking {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String structureType;

    public String customerFirstName;

    public String customerLastName;

    public String customerAddress;

    public double cost;

    public LocalDate bookingStartDate;

    public int numberOfDays;

    public Booking(String structureType, String customerFirstName, String customerLastName,
                   String customerAddress, double cost, LocalDate bookingStartDate, int numberOfDays) {
        this.structureType = structureType;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerAddress = customerAddress;
        this.cost = cost;
        this.bookingStartDate = bookingStartDate;
        this.numberOfDays = numberOfDays;
    }

    public int getId() {
        return id;
    }

    public String getStructureType() {
        return structureType;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public String getCustomerAddress() { return customerAddress; }

    public double getCost() {
        return cost;
    }

    public LocalDate getBookingStartDate() {
        return bookingStartDate;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }
}
