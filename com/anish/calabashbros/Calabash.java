package com.anish.calabashbros;

import java.awt.Color;

public class Calabash extends Creature {


    public Calabash(Color color, World world) {
        super(color, (char) 2, world);
    }



    public void swap(Calabash another) {
        int x = this.getX();
        int y = this.getY();
        this.moveTo(another.getX(), another.getY());
        another.moveTo(x, y);
    }

    public void goUp(){
        int x=this.getX();
        int y=this.getY();
        if (y>0 && !(world.get(x, y-1) instanceof Wall)){
            this.moveTo(x,y-1);
            world.put(new PassedFloor(world),x,y);
        }
    }

    public void goDown(){
        int x=this.getX();
        int y=this.getY();
        if (y<World.HEIGHT-1 && !(world.get(x, y+1) instanceof Wall)){
            this.moveTo(x,y+1);
            world.put(new PassedFloor(world),x,y);

        }
    }
    public void goLeft(){
        int x=this.getX();
        int y=this.getY();
        if (x>0 && !(world.get(x-1, y) instanceof Wall)){
            this.moveTo(x-1,y);
            world.put(new PassedFloor(world),x,y);
        }
    }
    public void goRight(){
        int x=this.getX();
        int y=this.getY();
        if (x<World.WIDTH-1 && !(world.get(x+1, y) instanceof Wall)){
            this.moveTo(x+1,y);
            world.put(new PassedFloor(world),x,y);
        }
    }

}
