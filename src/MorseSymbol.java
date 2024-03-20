public class MorseSymbol {
    private String morse;
    private String alpha;

    public MorseSymbol(String a, String m){
        this.morse = m;
        this.alpha = a;
    }

    public MorseSymbol(){
        this.morse = "";
        this.alpha = "";
    }
/**
    public String getMorse() {
        return morse;
    }

    public void setMorse(String morse) {
        this.morse = morse;
    }
**/
    /**
     *
     * @return alpha
     */
    public String getAlpha() {
        return alpha;
    }

    /**
    public void setAlpha(String alpha) {
        this.alpha = alpha;
    }
**/
    /**
     *
     * @return morse and alpha
     */
    public String toString(){
        return " { " + morse + " | " + alpha + " } ";
    }

    /**
     *
     * @param other
     * @return
     */
    public boolean equals(Object other) {
        if(other instanceof MorseSymbol)    {
            MorseSymbol temp = (MorseSymbol) other;
            return this.alpha.equals(temp.alpha) && this.morse.equals(temp.morse);
        }
        return false;
    }
}