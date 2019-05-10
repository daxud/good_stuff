package data

import scalafx.scene.image.Image
import helpers.Helpers
import scala.collection.mutable.Buffer
import scalafx.scene.shape.Circle

class CannonTower(tile: Tile, height: Int, width: Int, dmg: Int, e: Buffer[Enemy]) extends Tower {
  val x = tile.x.toDouble
  val y = tile.y.toDouble
  var timeSinceLastShot: Double = 0
  var firingSpeed: Double = 3
  val range = 10000.0
  var enemies: Buffer[Enemy] = e
  //The enemy that the tower is currently targeting.
  var targeted = false
  var target: Enemy = null
  this.chooseTarget()
  var angle: Double = this.calculateAngle

  //List of all the projectiles of THIS cannon.
  var projectiles = Buffer[Projectile]()
  def getProjectiles: Buffer[Projectile] = projectiles
  this.shoot() //Shoot one bullet right away.

  def findDistance(e: Enemy): Double = {
    val xDistance = math.abs(e.x - x)
    val yDistance = math.abs(e.y - y)
    math.hypot(xDistance, yDistance)
  }

  def isInRange(e: Enemy): Boolean = {
    val xDistance = math.abs(e.x - x)
    val yDistance = math.abs(e.y - y)
    (yDistance < range) && (xDistance < range)
  }

  //Sets the target variable to the desired target(closest)
  def chooseTarget(): Unit = {
    var closest: Enemy = null
    var minDistance: Double = 10000
    for (e <- enemies) {
      if (this.isInRange(e) && this.findDistance(e) < minDistance) {
        minDistance = this.findDistance(e)
        closest = e
      }
    }
    if (closest != null) targeted = true;
    target = closest
  }

  def updateEnemyList(newList: Buffer[Enemy]): Unit = {
    enemies = newList
  }

  //NOT IN USE CURRENTLY
  def calculateAngle(): Double = {
    val a: Double = Math.atan2(target.y - y, target.x - x)
    Math.toDegrees(a).toDouble - 90
  }

  def draw(): Unit = {
    val t = Helpers.loadText("src/res/whitecannon.png")
    Helpers.drawQuadText(t, x, y, 64, 64)
  }

  def shoot() = {
    timeSinceLastShot = 0
    projectiles += new Projectile(x + 32, y + 32, 1300, 50, target)
  }

  def update(delta: Double): Unit = {
    if (!targeted) this.chooseTarget
    timeSinceLastShot += delta
    if (timeSinceLastShot > firingSpeed) {
      shoot()
    }
    this.chooseTarget()
    //if (target == null || target.alive == false) targeted = false;
    //If the bullets get too far they disappear, and if the bullets hit the target they also disappear.
    projectiles.foreach { p =>
      p.update(delta)
      p.draw()
    }
    this.draw()
  }
}