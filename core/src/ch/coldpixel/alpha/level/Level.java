/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.coldpixel.alpha.level;

/**
 *
 * @author Ooky
 */
public class Level {

//==============================================================================
//Initialization
//==============================================================================
    //Levelsize
    private final int levelWidth;
    private final int levelHeight;

//==============================================================================
//Methods
//==============================================================================
    public Level(int levelWidth, int levelHeight) {
        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
    }
    
//==============================================================================
//Getter
//==============================================================================
    public int getLevelWidth() {
        return levelWidth;
    }

    public int getLevelHeight() {
        return levelHeight;
    }

}
