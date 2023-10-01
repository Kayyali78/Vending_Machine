import java.util.Arrays;
import java.util.Scanner;

public class Vend {
    State s;
    char[] chars = {'n','d','q','c','w'};
    int[] turnCounts = new int[5];
    int[] outCounts = new int[3];

    void vend(){
        s =  new State();
        s.setState(10,10,10,0,false);
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("Input: ");
            String text = in.next();

            if (text.equals("exit")){
                System.out.println("Exiting vending machine");
                break;
            }

            processInputs(text);
            lambda();
            delta();
        }
    }

    void processInputs(String text){
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < chars.length; j++) {
                if (text.charAt(i) == chars[j]) {
                    turnCounts[j]++;
                }
            }
        }

    }

    void lambda(){
        System.out.println("Value at beginning of lambda is: "+s.getValue());
        if (s.getValue() >= 100) {
            dispenseCoffee();
        } else {
            if (s.getChange()){
                change();
            } else {
                System.out.println("Output: Nothing");
            }
        }
    }

    void dispenseCoffee(){
        int numCoff = (int)(s.getValue()/100);
        System.out.println("Number of coffees to dispense is " + numCoff);
        if (numCoff > 0) {
            System.out.println("Output: Coffee * "+numCoff+ "\n");
            s.setValue(s.getValue()-(numCoff*100));
        }
    }

    void change(){
        while (s.getValue()>0) {
            if (s.value >= 25 && s.quarter !=0) {
                s.value -= 25;
                s.quarter -= 1;
                outCounts[2] ++;
            } else if (s.value >= 10 && s.dime != 0) {
                s.value -= 10;
                s.dime -= 1;
                outCounts[1] ++;
            } else if (s.value >= 5 && s.nickel != 0) {
                s.value -= 5;
                s.nickel -= 1;
                outCounts[0] ++;
            } else {
                System.out.println("We don't have the right change, sorry");
                break;
            }
        }
        System.out.println("Change is: ");
        System.out.println("Quarters: " + outCounts[2]);
        System.out.println("Dimes: " + outCounts[1]);
        System.out.println("Nickels: " + outCounts[0]);


        s.setChange(false);
        s.setValue(0);
        Arrays.fill(turnCounts, 0);
        Arrays.fill(outCounts, 0);
    }

    void delta(){
        int newValue = 0;
        if (turnCounts[0] != 0){
            int tempNick = turnCounts[0];
            int temp = s.getNickel()+tempNick;
            s.setNickel(temp);
            newValue += (tempNick*5);
            //System.out.println("Value is currently: " + s.value);
        }
        if (turnCounts[1] != 0){
            int tempDime = turnCounts[1];
            int temp = s.getDime()+tempDime;
            s.setDime(temp);
            newValue += (tempDime*10);
            //System.out.println("Value is currently: "+s.value);
        }
        if (turnCounts[2] != 0){
            int tempQuarter = turnCounts[2];
            int temp = s.getQuarter() + tempQuarter;
            s.setQuarter(temp);
            newValue += (tempQuarter*25);
            //System.out.println("Value is currently: "+s.value);
        }
        if (turnCounts[3] != 0){
            s.change = true;
            turnCounts[3] = 0;
        }
        newValue += s.getValue();
        s.setValue(newValue);
        System.out.println("Value after delta is: "+s.getValue());
        Arrays.fill(turnCounts, 0);
    }
}
