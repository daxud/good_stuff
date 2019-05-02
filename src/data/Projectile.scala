package data

import scalafx.scene.image.Image
import helpers.Helpers
import scalafx.scene.paint.Color

class Projectile(var x: Double, var y: Double, val speed: Double, val dmg: Double, r: Int) {
  
  def draw(): Unit = {
    Helpers.drawCircle(x, y, r, Color.Black)
  }
  
  def update(delta: Double): Unit = {
    x += delta * speed
    this.draw()
  }
  
}