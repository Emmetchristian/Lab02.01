public class MorseTreeNode {
    private MorseSymbol value;
    private MorseTreeNode left;
    private MorseTreeNode right;
/**
    public MorseTreeNode() {
        this.value = new MorseSymbol();
        this.left = null;
        this.right = null;
    }
**/

    /**
     * sets vlue left and right
     * @param value
     */
    public MorseTreeNode(MorseSymbol value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    /**
    public MorseTreeNode(MorseSymbol value, MorseTreeNode left, MorseTreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
    public MorseTreeNode(MorseTreeNode other)   {
        this.value = other.value;
        this.left=  other.left;
        this.right = other.right;
    }

     **/

    /**
     *
     * @return value
     */
    public MorseSymbol getValue() {
        return value;
    }

    /**
    public void setValue(MorseSymbol value) {
        this.value = value;
    }
**/
    /**
     *
     * @return left
     */
    public MorseTreeNode getLeft() {
        return left;
    }
    /**
     * sets left
     *
     */
    public void setLeft(MorseTreeNode left) {
        this.left = left;
    }
    /**
     *
     * @return right
     */
    public MorseTreeNode getRight() {
        return right;
    }

    /**
     * sets right
     * @param right
     */
    public void setRight(MorseTreeNode right) {
        this.right = right;
    }

    /**
     *
     * @return value as a string
     */
    public String toString()    {
        return this.value.toString();
    }
}