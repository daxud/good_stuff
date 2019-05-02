package helpers

import data._
import gui._
import scalafx.scene.image.Image
import java.io.FileInputStream
import scalafx.scene.paint.Color
import scalafx.scene.image.ImageView
import scalafx.scene.SnapshotParameters

object Helpers {

  //Graphics context of the gui.
  val gc = Drawing.getGc

  //The clock.
  val clock = new Clock

  //Draws the desired texture on the desired spot.
  def drawQuadTextRot(img: Image, x: Double, y: Double, width: Int, height: Int, r: Double) = { 
    gc.drawImage(img, x, y, width, height)
  }

  def drawCircle(x: Double, y: Double, r: Int, c: Color) = {
    val old = gc.fill
    gc.fill = c
    gc.fillOval(x, y, r, r)
    gc.fill = old
  }

  //Loads a texture from a path and returns it as a image.
  def loadText(path: String): Image = {
    val stream = new FileInputStream(path)
    val text = new Image(stream)
    text
  }
}