package data
import scalafx.application.JFXApp
import scalafx.geometry.Pos
import scalafx.scene.image.Image
import scalafx.scene.shape.Rectangle
import scalafx.scene.paint.Color
import scalafx.scene.canvas.GraphicsContext
import helpers._


class Tile(val x: Int, val y: Int, val tiletype: TileType) {
  
  def buildable: Boolean = tiletype.buildable
  
  def text: Color = tiletype.text
  
  def draw(): Unit = {
    val g = Helpers.gc
    Helpers.drawTile(x, y, tiletype.text)
  }
  
  //X and Y places in the tilegrid as integers.
  def xPlace = x / 64
  def yPlace = y / 64
}


