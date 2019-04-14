package helpers

import data._
import gui._
import scalafx.scene.image.Image
import java.io.FileInputStream


object Helpers {
  
  //Graphics context of the gui.
  val gc = Drawing.getGc
  
  //The clock.
  val clock = new Clock
 
  //Draws the desired texture on the desired spot.
  def drawQuadText(img: Image, x: Double, y: Double, width: Int, height: Int) = {
     gc.drawImage(img, x, y, width, height)
  }
  
  //Loads a texture from a path and returns it as a image.
  def loadText(path: String): Image = {
    val stream = new FileInputStream(path)
    val text = new Image(stream)
    text
  }  
}