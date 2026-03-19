package model;

public class Logic {
    private int size;
    private int[][] board;
    private int subgridSize;

    public Logic(int size){
        this.size=size;
        this.subgridSize=(int)(Math.sqrt(size));
        this.board=new int [size][size];
    }

    public int getNumber(int row, int column) {
        return board[row][column];
    }
    public void setNumbers(int number, int row, int column){
        board[row][column]=number;
    }

    public boolean solve(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(board[i][j]==0){
                    for(int num =1;num<=size;num++){
                        if(isOK(i,j,num)) {
                            board[i][j] = num;

                            if (solve()) {
                                return true;
                            }
                            board[i][j] = 0;
                        }

                    }
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isOK(int row,int column, int num){
        for(int i=0;i<size;i++){
            if(board[row][i]==num || board[i][column]==num){
                return false;
            }
        }
        int inicioLinha = row-(row%subgridSize);
        int inicioColuna = column -(column%subgridSize);
        for(int i=inicioLinha;i<inicioLinha+subgridSize;i++){
            for(int j= inicioColuna; j<inicioColuna+subgridSize;j++){
                if(board[i][j]==num){
                    return false;
                }
            }
        }
        return true;
    }

}

