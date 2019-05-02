package data

import scalafx.scene.image.Image
import helpers.Helpers
import scala.collection.mutable.Buffer
import scalafx.scene.shape.Circle

class TowerCannon(tile: Tile, height: Int, width: Int, dmg: Int) {
  val x = tile.x.toDouble
  val y = tile.y.toDouble
  var timeSinceLastShot: Double = 0
  var firingSpeed: Double = 1
  
  //List of all the projectiles of THIS cannon.
  val projectiles = Buffer[Projectile]()
  this.shoot() //Shoot one bullet right away.

  def draw(): Unit = {
    val t = Helpers.loadText("src/res/Cannon tower.png")
    Helpers.drawQuadTextRot(t, x, y, width, height, 0.0)
  }
  
  def shoot() = {
    timeSinceLastShot = 0
    projectiles += new Projectile(x + 32, y + 32, 80, 10, 10)
  }
  
  def update(delta: Double): Unit = {
    timeSinceLastShot += delta
    if (timeSinceLastShot > firingSpeed) {
      shoot()
    }
    projectiles.foreach { p =>
      p.update(delta)
      p.draw() 
    }
    this.draw()
  }
}