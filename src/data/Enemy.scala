package data

import scalafx.scene.image._
import scala.util.Random
import scalafx.scene.canvas.GraphicsContext
import javax.imageio.ImageIO
import scala.reflect.io.File
import java.awt.image.BufferedImage
import java.io.FileInputStream
import scalafx.event.EventType

abstract class Enemy (
    var x: Int, var y: Int,    
    val width: Int, val height: Int,    
    var health: Int, val speed: Double,
    val gc: GraphicsContext
    ) {    
  def draw: Unit
  def scan = {
    ???//skannaa edessä olevan tilen getTile metodilla. if (buildable) => turn
  }
  def move(): Unit = {
    val event = new EventType("Move")
    ???
  }
}

class EasyEnemy (
    x: Int, y: Int, width: Int, height: Int, health: Int,
    speed: Double, gc: GraphicsContext)
    extends Enemy (x, y, width, height, health, speed, gc)
{
  val stream = new FileInputStream("C:\\users\\aksel\\pictures\\enemy.png")
  val image = new Image(stream)
  def draw: Unit = gc.drawImage(image, x, y, width, height)
}

class HardEnemy (
    x: Int, y: Int, width: Int, height: Int, health: Int,
    speed: Double, gc: GraphicsContext)
    extends Enemy (x, y, width, height, health, speed, gc)
{
  val stream = new FileInputStream("C:\\users\\aksel\\pictures\\sonni.jpg")
  val image = new Image(stream)
  def draw: Unit = gc.drawImage(image, x, y, width, height)
  
  
  
  
  
  
}
