public class Point {

    /**
     *Point constructor
     * Sets the point coordinates and default values for isRed and content
     */
    public Point(int X, int Y){
        x=X;
        y=Y;
        isRed = false;
        content = '-';
    }

    /**
     * Setters and getters for the member-values of the class
     * setColor sets color according to the content of the point
     * setIsRed changes the isRed value to true
     */
    public void setContent(char a){
        content = a;
       // this.setColor();
    }

    public void setIsRed(){
        isRed = true;
    }

   /* private void setColor(){
        int colorCode = (int) (content -'0');
        switch (colorCode){
            case 0:
                color = "\u001B[32m";
                break;
            case 1:
                color = "\u001B[33m";
                break;
            case 2:
                color = "\u001B[33m";
                break;
            case 3:
                color = "\u001B[33m";
                break;
            case 4:
                color = "\u001B[31m";
                break;
            default:
                color = "\u001B[41m";
                break;
        }
    }
*/
    public boolean getIsRed(){;
        return isRed;
    }

    public char getContent(){
        return content;
    }

   /* public String getColor(){
        return color;
    }
*/
    public boolean isMine(){
        if(this.content=='*')return true;
        return false;
    }

    private int x;
    private int y;
    private char content;
    private boolean isRed;
  //  String color;
}
