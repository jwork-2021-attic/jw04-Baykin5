package com.anish.monsterarmy;

public class MonsterMatrix {
    int n;
    int m;
    private Monster[][] matrix;
    private Monster[] mons;
    private World world;

    public MonsterMatrix(int n,int m,World world){
        this.n=n;
        this.m=m;
        this.world=world;

        matrix=new Monster[n][m];
        mons=new Monster[n*m];

    }

    public void put(int pos,Monster mon){
        matrix[pos/m][pos%m]=mon;
        mons[pos]=mon;
    }

    public void putWorld(){
        for (int i=0;i<n;i++)
            for (int j=0;j<m;j++){
                world.put(matrix[i][j],2*j+1,2*i+1);
            }
    }

    public Monster[] getMons(){
        return mons;
    }
}
