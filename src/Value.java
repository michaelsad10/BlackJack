public enum Value {
    TWO(2),THREE(3),FOUR(4),FIVE(5),SIX(6),SEVEN(7),EIGHT(8),NINE(9),TEN(10),JACK(10),QUEEN(10),KING(10),ACE(1);

    //Got some of this from stackoverflow
    private int numVal;
    Value(int numVal){
        this.numVal = numVal;
    }
    public int getNumVal(){
        return numVal;
    }

    public int getAceValue(boolean isEleven){
        if (isEleven){
            return 11;
        }
        return 1;
    }
}
