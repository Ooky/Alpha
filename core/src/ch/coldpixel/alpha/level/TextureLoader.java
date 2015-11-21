/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.coldpixel.alpha.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author Coldpixel
 */
public class TextureLoader {

//==============================================================================
//Initialization
//==============================================================================
    //Background
    private  Texture backgroundTexture;
    public static TextureRegion cloud;                      //14
    public static TextureRegion backLevel1;
    //Items
    private final Texture itemList;
    private final TextureRegion sword;
    //Terrain
    private final Texture terrain;
    private  static TextureRegion surface0;                 //01
    private  static TextureRegion surface1;                 //02
    private  static TextureRegion surface2;                 //03
    private  static TextureRegion surfaceToGround0;         //04
    private  static TextureRegion surfaceToGround1;         //05
    private  static TextureRegion surfaceToGround2;         //06
    private  static TextureRegion backSurfaceToGround0;     //07
    private  static TextureRegion ground0;                  //08
    private  static TextureRegion ground1;                  //09
    private  static TextureRegion ground2;                  //10
    private  static TextureRegion backGround0;              //11
    private  static TextureRegion stairs0;                  //12
    private  static TextureRegion SpikeTrap0;               //13
    private static TextureRegion backGround1;               //80
    //Destinations
    private final Texture destination;                  
    private  static TextureRegion placeholder1;             //50
    //Just for Collision, not really great, should be change if collision works
    private  static TextureRegion emptyTexture;             //99

//==============================================================================
//Methods
//==============================================================================
    public TextureLoader() {
//------------------------------------------------------------------------------
//Background
//------------------------------------------------------------------------------
        backgroundTexture = new Texture(Gdx.files.internal("Graphics/Background/BackgroundRegion.png"));
        cloud = new TextureRegion(backgroundTexture, 0, 0, 16, 62);
        
        //Items
        itemList = new Texture(Gdx.files.internal("Graphics/Items/itemList.png"));
        sword = new TextureRegion(itemList, 0, 0, 24, 24);
//------------------------------------------------------------------------------
//Terrain
//------------------------------------------------------------------------------
        terrain = new Texture(Gdx.files.internal("Graphics/Terrain/Terrain.png"));
        //Just for Collision, not really great, should be change if collision works
        emptyTexture = new TextureRegion(terrain, 11, 11, 1, 1);
        //Surface
        surface0 = new TextureRegion(terrain, 11, 11, 16, 16);
        surface1 = new TextureRegion(terrain, 27, 11, 16, 16);
        surface2 = new TextureRegion(terrain, 43, 11, 16, 16);
        //Surface to Ground
        surfaceToGround0 = new TextureRegion(terrain, 11, 27, 16, 16);
        surfaceToGround1 = new TextureRegion(terrain, 27, 27, 16, 16);
        surfaceToGround2 = new TextureRegion(terrain, 43, 27, 16, 16);
        //background Surface to Ground
        backSurfaceToGround0 = new TextureRegion(terrain, 59, 27, 16, 16);
        //Ground
        ground0 = new TextureRegion(terrain, 11, 43, 16, 16);
        ground1 = new TextureRegion(terrain, 27, 43, 16, 16);
        ground2 = new TextureRegion(terrain, 43, 43, 16, 16);
        //Background ground
        backGround0 = new TextureRegion(terrain, 59, 43, 16, 16);
        //Stairs
        stairs0 = new TextureRegion(terrain, 11, 59, 16, 16);
        //Traps
        SpikeTrap0 = new TextureRegion(terrain, 11, 75, 16, 16);
        //Backrounds
        backgroundTexture = new Texture(Gdx.files.internal("Graphics/Background/background1.png"));
        backGround1 = new TextureRegion(backgroundTexture,0,0,1920,780);
//------------------------------------------------------------------------------
//Destination
//------------------------------------------------------------------------------
        destination = new Texture(Gdx.files.internal("Graphics/Destination/Destination.png"));
        placeholder1 = new TextureRegion(destination,0,0,32,64);
    }

//==============================================================================
//Getter
//==============================================================================
    public static TextureRegion getTextureRegion(int textureNumber){
        switch(textureNumber){
            case 1:
                return surface0;
            case 2:
                 return surface1;
            case 3:
                 return surface2;
            case 4:
                 return surfaceToGround0;
            case 5:
                 return surfaceToGround1;
            case 6:
                 return surfaceToGround2;
            case 7:
                 return backSurfaceToGround0;
            case 8:
                 return ground0;
            case 9:
                 return ground1;
            case 10:
                 return ground2;
            case 11:
                 return backGround0;
            case 12:
                 return stairs0;
            case 13:
                 return SpikeTrap0;
            case 14:
                 return cloud;
            case 50:
                 return placeholder1;
            case 80:
                return backGround1;
            case 99:
                 return emptyTexture;
            default:
                return cloud;
        }
    }
    
    public TextureRegion getSword() {
        return sword;
    }
}