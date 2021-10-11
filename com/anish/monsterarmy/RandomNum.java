package com.anish.monsterarmy;

import java.util.Random;

public class RandomNum {
    boolean[] sign;
    Random r;
    int size;
    int time;
    public RandomNum(int size)
    {
        this.size=size;
        sign=new boolean[size];
        for (int i=0;i<size;i++)
            sign[i]=false;
        r=new Random();
        time=0;
    }
    public int product()
    {
        if (time==size)
            return -1;
        int x=r.nextInt(size);
        while (sign[x]==true)
        {
            x=r.nextInt(size);
        }
        sign[x]=true;
        time++;
        return x;
    }

}


