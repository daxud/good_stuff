package data

import scalafx.scene.image.Image
import helpers.Helpers
import scala.collection.mutable.Buffer
import scalafx.scene.shape.Circle

class TowerCannon(tile: Tile, height: Int, width: Int, dmg: Int, e: Buffer[Enemy]) {
  val x = tile.x.toDouble
  val y = tile.y.toDouble
  var timeSinceLastShot: Double = 0
  var firingSpeed: Double = 3
  val enemies = e
  
  //List of all the projectiles of THIS cannon.
  var projectiles = Buffer[Projectile]()
  def getProjectiles: Buffer[Projectile] = projectiles
  this.shoot() //Shoot one bullet right away.

  def draw(): Unit = {
    val t = Helpers.loadText("src/res/Cannon tower.png")
    Helpers.drawQuadTextRot(t, x, y, width, height, 0.0)
  }
  
  def shoot() = {
    timeSinceLastShot = 0
    for (a <- (0 until 360 by 10).toVector) {
      projectiles += new Projectile(x + 32, y + 32, 3, 80, 10, a)
    }
  }
  
  def update(delta: Double): Unit = {
    timeSinceLastShot += delta
    if (timeSinceLastShot > firingSpeed) {
      shoot()
    }
    //If the bullets get too far they disappear.
    projectiles = projectiles.filter(p => math.hypot(math.abs(x - p.x), math.abs(y - p.y)) < 300)
    projectiles.foreach { p =>
      p.update(delta)
      p.draw()
    }
    this.draw()
  }
}