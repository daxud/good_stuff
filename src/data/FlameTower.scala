package data

import scala.collection.mutable.Buffer
import helpers.Helpers
import scalafx.scene.paint.Color

class FlameTower(tile: Tile, height: Int, width: Int, dmg: Int, e: Buffer[Enemy]) extends Tower {
  val x = tile.x.toDouble
  val y = tile.y.toDouble
  var timeSinceLastShot: Double = 0
  var firingSpeed: Double = 4
  val rangeInTiles = 5 //if the range is i.e one tile it covers just the tile that the tower is in.
  val flameX = x - ((rangeInTiles - 1) * 32)
  val flameY = y - ((rangeInTiles - 1) * 32)
  var enemies = e

  //whether the tower is currently sending flames
  var flameVisible = false

  def updateEnemyList(newList: Buffer[Enemy]) = {
    enemies = newList
  }

  //Uses the flame, that is draws the circle around the weapon.
  def useFlame() = {
    flameVisible = true
    Helpers.drawCircle(flameX, flameY, rangeInTiles * 64, Color.rgb(255, 0, 0, 0.5))
  }

  //Draws the tower but not the flame.
  def draw(): Unit = {
    val t = Helpers.loadText("src/res/flamecirclewhite.png")
    Helpers.drawQuadText(t, x, y, width, height)
  }

  def shoot() = {
    timeSinceLastShot = 0
    flameVisible = true
    this.useFlame()
    //when the tower shoots go through the enemylist and do dmg to each if they in the flame range.
    enemies.foreach(e => if (Helpers.isColliding(flameX, flameY, rangeInTiles * 64, rangeInTiles * 64, e.x, e.y, 64, 64)) e.doDmg(50))
  }

  def update(delta: Double): Unit = {
    timeSinceLastShot += delta
    //This part of the code makes the flame stay visible for a while (0.5seconds)
    if (timeSinceLastShot < 0.5) {
      flameVisible = true
      this.draw()
      this.useFlame()
    } else {
      flameVisible = false
      this.draw()
    }
    //Shoots again and again according to firingSpeed.
    if (timeSinceLastShot > firingSpeed) {
      flameVisible = true
      this.shoot()
    }
  }
  this.shoot()
}