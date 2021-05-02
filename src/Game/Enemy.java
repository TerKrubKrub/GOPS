package Game;

import java.util.ArrayList;

public class Enemy extends Player{

    public int pointChooseCard = 0;
    private int pointAction = 0;
    private String action;
    private int amountBet = 0;



    public int getPointAction() {
        return this.pointAction;
    }

    public Enemy() {

    }

    public Card botChooseCard(int pointCentral, ArrayList<Card> botHand) {
//        for(int a = 0; a<botHand.size(); a++){
//            System.out.print(botHand.get(a).getPoint()+" ");
//        }
        this.pointChooseCard=0;
        int indexForDelete=0;
        //botHand.get(0).getPoint();

        //point 1-5
        if(pointCentral<=5 ) {
            int random = (int)(Math.random()*4);

            if(random==0){
                this.pointChooseCard=botHand.get(0).getPoint();
                this.pointAction=10;
                indexForDelete=0;
            }

            else if(random==1){
                if(botHand.size()-1>2){
                    this.pointChooseCard=botHand.get(1).getPoint();
                    this.pointAction=11;
                    indexForDelete=1;
                }

                else{
                    this.pointChooseCard=botHand.get(0).getPoint();
                    indexForDelete=0;
                    pointAction=10;
                }

            }

            else if(random==2){
                if(botHand.size()-1>3){
                    this.pointChooseCard=botHand.get(2).getPoint();
                    indexForDelete=2;
                    this.pointAction=12;
                }

                else {
                    this.pointChooseCard = botHand.get(0).getPoint();
                    indexForDelete = 0;
                    this.pointAction=10;
                }
            }

            else if(random==3){
                if(botHand.size()-1>4) {
                    this.pointChooseCard = botHand.get(3).getPoint();
                    indexForDelete = 3;
                    this.pointAction=13;
                }
                else {
                    this.pointChooseCard = botHand.get(0).getPoint();
                    indexForDelete=0;
                    this.pointAction=10;
                }
            }
        }

        //point 6-10
        else if(pointCentral<=10) {
            int random = (int)(Math.random()*3);

            if(random==0){
                if(botHand.size()-1>7){
                    this.pointChooseCard=botHand.get(botHand.size()-1-5).getPoint();
                    indexForDelete=(int)(botHand.size()-1-5);
                    this.pointAction=20;
                }

                else{
                    this.pointChooseCard=botHand.get((int)((botHand.size()-1)/2)).getPoint();
                    indexForDelete=((int)((botHand.size()-1)/2));
                    this.pointAction=22;
                }

            }

            else if(random==1){
                if(botHand.size()-1>6) {
                    this.pointChooseCard = botHand.get(botHand.size()-1 - 4).getPoint();
                    indexForDelete = botHand.size()-1 - 4;
                    this.pointAction=21;
                }
                else{
                    this.pointChooseCard = botHand.get((int) ((botHand.size()-1) / 2)).getPoint();
                    indexForDelete = (int) ((botHand.size()-1) / 2);
                    this.pointAction=22;
                }
            }

            else if(random==2){
                if(botHand.size()-1>5) {
                    this.pointChooseCard = botHand.get(botHand.size()-1 - 3).getPoint();
                    indexForDelete = (int) (botHand.size()-1 - 3);
                    this.pointAction=22;
                }
                else {
                    this.pointChooseCard = botHand.get((int) ((botHand.size()-1) / 2)).getPoint();
                    indexForDelete = (int) ((botHand.size()-1) / 2);
                    this.pointAction=22;
                }
            }
        }

        //point 11-15
        else {
            int random = (int)(Math.random()*3);

            if(random==0){
                if(botHand.size()-1>4) {
                    this.pointChooseCard = botHand.get(botHand.size()-1 - 2).getPoint();
                    indexForDelete = botHand.size()-1 - 2;
                    this.pointAction=30;
                }
                else {
                    this.pointChooseCard = botHand.get((int) botHand.size()-1).getPoint();
                    indexForDelete = (int) (botHand.size()-1);
                    this.pointAction=32;
                }

            }

            else if(random==1){
                if(botHand.size()-1>3) {
                    this.pointChooseCard = botHand.get(botHand.size()-1 - 1).getPoint();
                    indexForDelete = botHand.size()-1 - 1;
                    this.pointAction=31;
                }
                else {
                    this.pointChooseCard = botHand.get((int) botHand.size()-1).getPoint();
                    indexForDelete = (int) botHand.size()-1;
                    this.pointAction=32;
                }
            }

            else if(random==2){
                this.pointChooseCard=botHand.get((int)botHand.size()-1).getPoint();
                indexForDelete = (int)botHand.size()-1;
                this.pointAction=32;
            }
        }
//        for(Card i : botHand) {
//            System.out.print(i.getPoint() + " ");
//        }
        //System.out.println("Bot Choose: " + this.pointChooseCard);
        //System.out.println("Bot size"+(botHand.size()-1));
        for(Card i : botHand) {
            if(i.getPoint() == this.pointChooseCard) {
                return i;
            }
        }
        botHand.remove(indexForDelete);
//        for(int z=0;z<botHand.size();z++){
//            botHand.removeIf(card -> botHand.get(z).getValue()==pointChooseCard);
//        }
        return null;
    }

    @Override
    public String toString() {
        return "Enemy{" +
                "action='" + action + '\'' +
                '}';
    }

    public String botChooseActionFirst(int pointForAction){

        //1-5 ลงไพ่น้อยสุด
        String actionF = "Call";
        int randomAction = (int)((Math.random()*100)+1);
        if(pointForAction == 10)
        {
            if(randomAction<=80 && randomAction>=1)
            {
                actionF = "Call";
            }
            else if(randomAction>=81 && randomAction <=100){
                actionF = "Raise10";
                this.amountBet = 10;
            }

        }

        //1-5 ลงไพ่น้อยสุดอันดับ2
        if(pointForAction == 11)
        {
            if(randomAction<=80 && randomAction>=1)
            {
                actionF = "Call";
            }
            else if(randomAction>=81 && randomAction <=100){
                actionF = "Raise10";
                this.amountBet = 10;
            }
        }
        //1-5 ลงไพ่น้อยสุดอันดับ3
        if(pointForAction == 12)
        {
            if(randomAction<=75 && randomAction>=1)
            {
                actionF = "Call";
            }
            else if(randomAction>=76 && randomAction <=100){
                actionF = "Raise20";
                this.amountBet = 20;
            }
        }
        //1-5 ลงไพ่น้อยสุดอันดับ4
        if(pointForAction == 13)
        {
            if(randomAction<=75 && randomAction>=1)
            {
                actionF = "Call";
            }
            else if(randomAction>=76 && randomAction <=100){
                actionF = "Raise20";
                this.amountBet = 20;
            }
        }

        //6-10 ลงไพ่แต้มสูงสุดอันดับ6
        if(pointForAction ==20)
        {
            if(randomAction<=70 && randomAction>=1)
            {
                actionF = "Call";
            }
            else if(randomAction>=71 && randomAction <=100){
                actionF = "Raise30";
                this.amountBet = 30;
            }
        }

        //6-10 ลงไพ่แต้มสูงสุดอันดับ5
        if(pointForAction ==20)
        {
            if(randomAction<=60 && randomAction>=1)
            {
                actionF = "Call";
            }
            else if(randomAction>=61 && randomAction <=100){
                actionF = "Raise40";
                this.amountBet = 40;
            }
        }

        //6-10 ลงไพ่แต้มสูงสุดอันดับ4
        if(pointForAction ==20)
        {
            if(randomAction<=50 && randomAction>=1)
            {
                actionF = "Call";
            }
            else if(randomAction>=51 && randomAction <=100){
                actionF = "Raise50";
                this.amountBet = 50;
            }
        }

        //11-15 ลงไพ่แต้มสูงสุดอันดับ4
        if(pointForAction ==30)
        {
            if(randomAction<=30 && randomAction>=1)
            {
                actionF = "Call";
            }
            else if(randomAction>=31 && randomAction <=100){
                actionF = "Raise100";
                this.amountBet = 100;
            }
        }

        //11-15 ลงไพ่แต้มสูงสุดอันดับ4
        if(pointForAction ==20)
        {
            if(randomAction<=25 && randomAction>=1)
            {
                actionF = "Call";
            }
            else if(randomAction>=26 && randomAction <=100){
                actionF = "Raise120";
                this.amountBet = 120;
            }
        }

        //11-15 ลงไพ่แต้มสูงสุดอันดับ4
        if(pointForAction ==20)
        {
            if(randomAction<=20 && randomAction>=1)
            {
                actionF = "Call";
            }
            else if(randomAction>=21 && randomAction <=100){
                actionF = "Raise120";
                this.amountBet = 120;
            }
        }

        return actionF;

    }

    public String botChooseActionSecond(int pointForAction){
        String action="";
        int random = (int)(Math.random()*100)+1;

        //1-5
        if(pointForAction <16){
            if(pointForAction == 10){
                if(random<=50)
                    action="Fold";

                else if(random<=90)
                    action="Call";

                else if(random<=100)
                    action="Raise10";
                amountBet=10;
            }
            else
            if(pointForAction == 11){
                if(random<=50)
                    action="Fold";

                else if(random<=90)
                    action="Call";

                else if(random<=100)
                    action="Raise10";
                amountBet=10;
            }
            else
            if(pointForAction == 12){
                if(random<=40)
                    action="Fold";

                else if(random<=90)
                    action="Call";

                else if(random<=100)
                    action="Raise30";
                amountBet=30;
            }
            else
            if(pointForAction == 13){
                if(random<=35)
                    action="Fold";

                else if(random<=85)
                    action="Call";

                else if(random<=100)
                    action="Raise20";
                amountBet=20;
            }
        }else

        if(pointForAction<25)
        {
            //6-10
            if(pointForAction == 20){
                if(random<=25)
                    action="Fold";

                else if(random<=85)
                    action="Call";

                else if(random<=100)
                    action="Raise60";
                amountBet=60;
            }
            else
            if(pointForAction == 21){
                if(random<=25)
                    action="Fold";

                else if(random<=85)
                    action="Call";

                else if(random<=100)
                    action="Raise50";
                amountBet=50;
            }
            else
            if(pointForAction == 22){
                if(random<=20)
                    action="Fold";

                else if(random<=75)
                    action="Call";

                else if(random<=100)
                    action="Raise60";
                amountBet=60;
            }
        }else

        if(pointForAction<36)
        {
            //11-15
            if(pointForAction == 30){
                if(random<=10)
                    action="Fold";

                else if(random<=75)
                    action="Call";

                else if(random<=100)
                    action="Raise80";
                amountBet=80;
            }
            else
            if(pointForAction == 31){
                if(random<=5)
                    action="Fold";

                else if(random<=40)
                    action="Call";

                else if(random<=100)
                    action="Raise100";
                amountBet=100;
            }
            else
            if(pointForAction == 32){
                if(random<=35)
                    action="Call";

                else if(random<=100)
                    action="Raise100";
                amountBet=100;
            }
        }
        return  action;
    }

    public int getAmountBet() {
        return amountBet;
    }
}