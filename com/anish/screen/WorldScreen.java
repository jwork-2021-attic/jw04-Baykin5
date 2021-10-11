package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.World;
import com.anish.calabashbros.Wall;
import com.anish.calabashbros.Floor;
import com.anish.maze.MazeGenerator;
import com.anish.calabashbros.Destination;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    int SIZE=30;
    int [][]raws;
    Calabash calabash;
    

    public WorldScreen() {
        world = new World();
        
        MazeGenerator mazeGenerator = new MazeGenerator(SIZE);
        mazeGenerator.generateMaze();
        raws=mazeGenerator.getRaws();
        for (int i=0;i<SIZE;i++)
            for (int j=0;j<SIZE;j++){
                if (raws[i][j]==1){
                    world.put(new Floor(world),i,j);
                }
                else{
                    world.put(new Wall(world),i,j);
                }
            }
        world.put(new Destination(world),SIZE-1,SIZE-1);
        calabash = new Calabash(new Color(204, 0, 0), world);
        world.put(calabash, 0, 0);

    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int i = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        if (calabash.getX()==SIZE-1 && calabash.getY()==SIZE-1)   //has arrived the destination
            return this;
        int x=key.getKeyCode();
        switch(x)
        {
            case 38:{//up
                calabash.goUp();
                break;
            }
            case 40:{//down
                calabash.goDown();
                break;
            }
            case 37:{//left
                calabash.goLeft();
                break;
            }
            case 39:{//right\
                calabash.goRight();
                break;
            }

            default: break;
        }
        return this;
    }

}
