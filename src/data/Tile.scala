package data
import scalafx.application.JFXApp
import scalafx.geometry.Pos
import scalafx.scene.image.Image
import scalafx.scene.shape.Rectangle
import scalafx.scene.paint.Color
import scalafx.scene.canvas.GraphicsContext

abstract class Tile(
    val x: Int, val y: Int, val width: Int, val height: Int,
    val buildable: Boolean, val color: Color)

class DirtTile(x: Int, y:Int, width: Int, height: Int) extends Tile(
    x, y, width, height, false, Color.LightGoldrenrodYellow)

class GrassTile(x: Int, y:Int, width: Int, height: Int) extends Tile(
    x, y, width, height, true, Color.Green) 

