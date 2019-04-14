package data

import data._
import helpers._
import scalafx.scene.image.Image

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

