package model;


import java.util.Calendar;
import java.util.Date;

public class Ticket {
    static int lastID = 0;

    int id;
    float totalPrice;
    int minutesUsed;
    Date expendedDate;

    public Ticket(float totalPrice, int minutesUsed) {
        this.totalPrice = totalPrice;
        this.minutesUsed = minutesUsed;
        expendedDate = Calendar.getInstance().getTime();
        id = lastID++;
    }

    public boolean hasSameID(Ticket otherTicket){
        return (otherTicket.getID()==this.id);
    }

    public int getID(){
        return id;
    }
}
