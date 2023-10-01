public class State {
    protected int nickel = 10, dime = 10, quarter = 10;
    protected int value = 0;
    protected boolean change = false;

    void setState(int nickel, int dime, int quarter, int value, boolean flag){
        this.nickel = nickel;
        this.dime = dime;
        this.quarter = quarter;
        this.value = value;
        this.change = flag;
    }
    void setNickel(int nick) {this.nickel = nick;}
    void setDime(int dime) {this.dime = dime;}
    void setQuarter(int quarter) {this.quarter = quarter;}
    void setValue(int value) {this.value = value;}
    void setChange(boolean flag){this.change = flag;}
    int getNickel(){return this.nickel;}
    int getDime(){return this.dime;}
    int getQuarter(){return this.quarter;}
    int getValue(){return this.value;}
    boolean getChange(){return this.change;}

}
