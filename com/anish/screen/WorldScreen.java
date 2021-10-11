package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.monsterarmy.BubbleSorter;
import com.anish.monsterarmy.Monster;
import com.anish.monsterarmy.World;
import com.anish.monsterarmy.RandomNum;
import com.anish.monsterarmy.MonsterMatrix;


import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;

    private MonsterMatrix matrix;
    private Monster[]mons;

    String[] sortSteps;

    public WorldScreen() {
        world = new World();
        matrix= new MonsterMatrix(8, 8,world);
        RandomNum randomNum=new RandomNum(8*8);
        for (int i=0;i<64;i++)
        {            
            int tempR=randomNum.product();
            Monster m=new Monster(new Color(255-i*4,255-i*4,i*4),i,world);
            matrix.put(tempR,m);
        }
        matrix.putWorld();

        mons=matrix.getMons();


        BubbleSorter<Monster> b = new BubbleSorter<>();
        b.load(mons);
        b.sort();

        sortSteps = this.parsePlan(b.getPlan());
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Monster[] mons, String step) {
        String[] couple = step.split("<->");
        getBroByRank(mons, Integer.parseInt(couple[0])).swap(getBroByRank(mons, Integer.parseInt(couple[1])));
    }

    private Monster getBroByRank(Monster[] mons, int rank) {
        for (Monster mon : mons) {
            if (mon.getRank() == rank) {
                return mon;
            }
        }
        return null;
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

        if (i < this.sortSteps.length) {
            this.execute(mons, sortSteps[i]);
            i++;
        }

        return this;
    }

}
