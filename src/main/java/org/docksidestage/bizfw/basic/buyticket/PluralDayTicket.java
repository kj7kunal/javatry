package org.docksidestage.bizfw.basic.buyticket;

public class PluralDayTicket implements TicketInterface {
    private int displayPrice;
    private int days;

    public PluralDayTicket(int days, int price){
        this.displayPrice = price;
        this.days = days;
    }

    @Override
    public void doInPark() {
        if (days<=0) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        --days;
    }

    @Override
    public int getDisplayPrice() {
        return displayPrice;
    }

    public int getDays(){
        return days;
    }
}