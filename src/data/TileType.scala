package data

import data._
import helpers._
import scalafx.scene.image.Image
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

trait TileType {
  
  def buildable: Boolean
  def text: Image
  def width: Int = 64
  def height: Int = 64
  
}
  
  object Grass extends TileType{
    def buildable = true
    val t = Helpers.loadText("src/res/GrassTile.png")
    def text = t
  }
  
  object Dirt extends TileType {
    def buildable = false
    val t = Helpers.loadText("src/res/DirtTile.png")
    def text = t
  }
  
  //This is just a placeholder tile for the auto-move AI. This tiletype never exists in our game!
  object Null extends TileType {
    def buildable = false
    val t = Helpers.loadText("src/res/DirtTile.png")
    def text = t
  }

