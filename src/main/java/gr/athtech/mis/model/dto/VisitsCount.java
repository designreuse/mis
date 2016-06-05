package gr.athtech.mis.model.dto;

//helper class, used only to hold the pie chart information
public class VisitsCount {

    private int scheduledVisitsCount;
    private int paidVisitsCount;

    public VisitsCount(int scheduledVisitsCount, int paidVisitsCount) {
        this.scheduledVisitsCount = scheduledVisitsCount;
        this.paidVisitsCount = paidVisitsCount;
    }

    public int getScheduledVisitsCount() {
        return scheduledVisitsCount;
    }

    public int getPaidVisitsCount() {
        return paidVisitsCount;
    }

    public void setScheduledVisitsCount(int scheduledVisitsCount) {
        this.scheduledVisitsCount = scheduledVisitsCount;
    }

    public void setPaidVisitsCount(int paidVisitsCount) {
        this.paidVisitsCount = paidVisitsCount;
    }

}
