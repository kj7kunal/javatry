package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author kunal.jain
 */
public class TicketBuyResult {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int change;
    private Ticket tiketto;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBuyResult(Ticket tiketto, int change) {
        this.change = change;
        this.tiketto = tiketto;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getChange() {
        return change;
    }

    public Ticket getTicket() {
        return tiketto;
    }
}
