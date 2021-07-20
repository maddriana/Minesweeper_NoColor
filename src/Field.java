public class Field {

    /**
     * Field constructor
     * @param Size - size of the field (length = width)
     * @param Mines - number of mines
     */
    public Field(int Size, int Mines){
        size = Size;
        mines = Mines;
        field = new Point [size*size];
        minePositions = new int [mines];
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                int position = i*size+j;
                field[position] = new Point(i,j);
            }
        }
    }

    /**
     * showField method from type void
     * Prints the current state of the field in the console with row and column numeration
     */
    public void showField(){

        System.out.println();
        System.out.print('\t');
          for(int i=0; i<size; i++) {
                    System.out.print(i);
                     System.out.print('\t');
            }
            for( int i=0; i<size; i++){
                System.out.println();
                System.out.print(i);
                System.out.print('\t');
                for(int j=0; j<size;  j++){
                    int position = i*size+j;
                    if(field[position].getIsRed()==false){
                        System.out.print('-'); //Color reset
                        System.out.print('\t');
                        continue;
                    }
                    else {
                        System.out.print(field[position].getContent());
                        System.out.print('\t');
                    }
                }

            }
    };

    /**
     * loadField method from type void
     * @param x - row coordinate of the first move
     * @param y - column coordinate of the first move
     * Saves the position of the first move and generates randomly all mined positions
     *    A mined spot cannot be the first move and cannot be "re-mined"
     * All safe points are filled with the number of mines surrounding
     */

    public void loadField(int x, int y){

            firstPosition = x*size+y;
            field[firstPosition].setIsRed();
            for(int i=0; i<mines; i++){
                int position = (int)(Math.random()*(size*size-1));
                while(field[position].getContent()!='-' || position==firstPosition){
                    position = (int)(Math.random()*(size*size-1));
                }
                field[position].setContent('*');
                minePositions[i] = position;
            }
           for(int i=0; i<size; i++){
                for(int j=0; j<size; j++) {
                    int position = i*size+j;
                    if(field[position].isMine())continue;
                    else{
                        int count=0;
                        int top = position - size;
                        if(top>=0){
                            if(field[top].isMine())count++;
                        }
                        int bottom = position + size;
                        if(bottom<size*size){
                            if(field[bottom].isMine())count++;
                        }
                            if(j!=0){
                            if(field[position-1].isMine())count++;
                        }
                        if(j!=size-1){
                            if(field[position+1].isMine())count++;
                        }
                        field[position].setContent((char)(count+'0'));
                    }
                }
           }
    };


    /**
     * PlayNext method of type int
     * @param x - row coordinate of the new move
     * @param y - column coordinate of the new move
     * @return -1 if the move data input was invalid
     * @return 1 if the entered point is mined (uncovers all mined positions)
     * @return 0 if the entered position is safe (sets the point to red)
     */

    int playNext(int x, int y){
        int position = x*size+y;

        try{
            checkCoordinates(x,y);
        }catch(ValueException e){
            return -1;
        }

        if(field[position].isMine()){
            this.setAllMinesRed();
            return 1;
        }
        field[position].setIsRed();
        return 0;
    }


    /**
     * checkCoordinates method from type boolean
     * @param x - row coordinate of the new move
     * @param y - column coordinate of the new  move
     * @return true if there are no isue with the data input
     * @throws ValueException when the values are outside the field or the move is already made
     */
    boolean checkCoordinates(int x, int y)throws ValueException{
        if(x>=size || y>= size ||x<0 || y<0) throw new ValueException();
        int position = x*size+y;
        if(field[position].getIsRed())throw new ValueException();
        return true;
    }


    /**
     * setAllMinesRed method from type void
     * "Uncovers" all mined points
     */

    void setAllMinesRed(){
        for(int i=0; i<mines; i++){
            field[minePositions[i]].setIsRed();
        }
    }

     private int [] minePositions;
     private int firstPosition;
     private Point [] field;
     private int mines;
     private int size;
}
