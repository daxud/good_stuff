package data

import helpers._
import gui.Drawing

class Enemy(enemytype: EnemyType, grid: TileGrid) {
  var x = enemytype.startTile.x.toDouble
  var y = enemytype.startTile.y.toDouble
  var health: Int = enemytype.health
  val speed: Int = enemytype.speed
  var dirMultp = (0, 0)
  var prevTile: Tile = grid.getTile(0, 0)
  
  //The tile that the enemy is currently standing.
  def currentTile: Tile = grid.getTile((x / 64).toInt, (y / 64).toInt)

  def draw(): Unit = Helpers.drawQuadText(enemytype.text, x, y, enemytype.width, enemytype.height)

  //Finds the next direction.
  def findNextDir = {
    val t = this.currentTile
    //All the surrounding tiles.
    val u = grid.getTile(t.xPlace, t.yPlace - 1)
    val d = grid.getTile(t.xPlace, t.yPlace + 1)
    val r = grid.getTile(t.xPlace + 1, t.yPlace)
    val l = grid.getTile(t.xPlace - 1, t.yPlace)

    if (u.tiletype == t.tiletype && u.yPlace != prevTile.yPlace) {
      dirMultp = (0, -10)
      prevTile = t
    } else if (d.tiletype == t.tiletype && d != prevTile) {
      dirMultp = (0, 10)
      prevTile = t
    } else if (r.tiletype == t.tiletype && r != prevTile) {
      dirMultp = (10, 0)
      prevTile = t
    } else if (l.tiletype == t.tiletype && t != prevTile) {
      dirMultp = (-10, 0)
      prevTile = t
    }
  }
  
  def findNextC = {
    val t = this.currentTile
    var nextT: Option[Tile] = None
    var checkPoint: Option[Checkpoint] = None
    var found: Boolean = false
    var stepper = 1
    
    while (!found) {
      if (this.currentTile !=
        grid.getTile(t.xPlace + dirMultp._1 * stepper, t.yPlace + dirMultp._2 * stepper)) {
        found = true
        stepper -= 1
        nextT = Some(grid.getTile(t.xPlace + dirMultp._1 * stepper, t.yPlace + dirMultp._2 * stepper))
      }
      stepper += 1
    }
    checkPoint = Some(new Checkpoint(nextT.get, dirMultp._1, dirMultp._2))
    checkPoint
  }

  //Updates enemy state.
  def update(delta: Double) = {
    this.findNextDir
    x += delta * speed * dirMultp._1
    y += delta * speed * dirMultp._2
  }
}