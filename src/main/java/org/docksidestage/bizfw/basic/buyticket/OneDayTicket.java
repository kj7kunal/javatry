package org.docksidestage.bizfw.basic.buyticket;

public class OneDayTicket implements TicketInterface {
    private int displayPrice = 7400;
    private boolean alreadyIn = false;

    @Override
    public void doInPark() {
        if (alreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        alreadyIn = true;

    }
    @Override
    public int getDisplayPrice() {
        return displayPrice;
    }
}
