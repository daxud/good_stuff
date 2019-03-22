package helpers

import data._
import gui._
import scalafx.scene.image.Image
import java.io.FileInputStream


object Helpers {
  
  val gc = Drawing.getGc
 
  
  def drawQuadText(img: Image, x: Int, y: Int, width: Int, height: Int) = {
     gc.drawImage(img, x, y, width, height)
  }
  
  def loadText(path: String): Image = {
    val stream = new FileInputStream(path)
    val text = new Image(stream)
    text
  }
  
  
}