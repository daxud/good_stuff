package data

import scalafx.scene.image.Image
import helpers.Helpers
import scalafx.scene.paint.Color

class Projectile(var x: Double, var y: Double, val speed: Double, val dmg: Double, r: Int, a: Int) {

  def draw(): Unit = {
    Helpers.drawCircle(x, y, r, Color.Black)
  }
  
  //Depending on the angle update the projectile.
  def update(delta: Double): Unit = {
    if (a >= 0 && a <= 90) {
      x += delta * speed * a
      y -= delta * speed * (90 - a)
    } else if (a >= 90 && a <= 180) {
      x += delta * speed * (180 - a)
      y -= delta * speed * (90 - a)
    } else if (a >= 180 && a <= 270) {
      x += delta * speed * (180 - a)
      y += delta * speed * (270 - a)
    } else if (a >= 270 && a <= 360) {
      x -= delta * speed * (360 - a)
      y += delta * speed * (270 - a)
    }
    this.draw()
  }

}