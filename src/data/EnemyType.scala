package data
import data._
import scalafx.scene.image.Image
import helpers.Helpers
import gui.Drawing

trait EnemyType {
  def text: Image
  def speed: Int
  def health: Int
  def width: Int = 64
  def height: Int = 64
}

object Easy extends EnemyType {
  val t = Helpers.loadText("src/res/Sonni.png")
  def text = t
  def speed: Int = 120
  def health: Int = 100
}