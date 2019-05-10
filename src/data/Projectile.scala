package data

import scalafx.scene.image.Image
import helpers.Helpers
import scalafx.scene.paint.Color

class Projectile(var x: Double, var y: Double, val speed: Double, val dmg: Int, t: Enemy) {

  //Diameter of the bullet.
  val d: Int = 10

  //Velocities
  var xVelocity = 0.0
  var yVelocity = 0.0

  //Target of the bullet.
  val target: Enemy = t

  //Is the projectile alive
  var alive: Boolean = true

  def calculateDirection() = {
    val totalAllowedMovement = 1.0
    var xDistanceFromTarget = Math.abs(target.x - x + 32)
    var yDistanceFromTarget = Math.abs(target.y - y + 32)
    var totalDistanceFromTarget = xDistanceFromTarget + yDistanceFromTarget
    var xPercentOfMovement = xDistanceFromTarget / totalDistanceFromTarget
    xVelocity = xPercentOfMovement
    yVelocity = totalAllowedMovement - xPercentOfMovement

    if (target.x < x) xVelocity = xVelocity * -1
    if (target.y < y) yVelocity = yVelocity * -1
  }

  this.calculateDirection()

  def draw(): Unit = {
    if (alive) {
      Helpers.drawCircle(x, y, d, Color.Black)
    }
  }

  def update(delta: Double): Unit = {
    if (alive) {
      y += yVelocity * speed * delta
      x += xVelocity * speed * delta
      //Lets check here is the bullet is hitting the target. If it is the bullet "dies" and dmgs the enemy.
      if (Helpers.isColliding(x, y, d, d, target.x, target.y, 64, 64) && this.alive) {
        alive = false
        target.doDmg(dmg)
      }
      this.draw()
    }
  }
}