package data

import helpers._
import gui.Drawing
import scala.collection.mutable.Buffer

class Enemy(enemytype: EnemyType, grid: TileGrid) {
  var x = enemytype.startTile.x.toDouble
  var y = enemytype.startTile.y.toDouble
  var health: Int = enemytype.health
  val speed: Int = enemytype.speed

  //Variables that have somethin to do with the moving of the enemy.
  var dirMultp = (0, 0)
  var currentCheckpoint: Int = 0
  val checkpoints = Buffer[Checkpoint]()
  this.populateCheckpointList()

  //The tile that the enemy is currently standing.
  def currentTile: Tile = grid.getTile((x / 64).toInt, (y / 64).toInt)

  //The enemy is alive if has some health and if its inside the map parameters.
  def alive: Boolean = {
    val c = this.currentTile
    c.xPlace < 19 && c.xPlace > 0 && c.yPlace < 15 && c.yPlace > 0 && health > 0
  }

  //Draws the enemy to the screen.
  def draw(): Unit = Helpers.drawQuadText(enemytype.text, x, y, enemytype.width, enemytype.height)

  //Finds the next direction.
  def findNextDir(t: Tile) = {
    //All the surrounding tiles.
    if (t.xPlace > 0 && t.yPlace > 0 && t.xPlace < 18 && t.yPlace < 13) {
      val u = grid.getTile(t.xPlace, t.yPlace - 1)
      val d = grid.getTile(t.xPlace, t.yPlace + 1)
      val r = grid.getTile(t.xPlace + 1, t.yPlace)
      val l = grid.getTile(t.xPlace - 1, t.yPlace)

      //Enemy cant turn 180 degrees around so current value of dirMultp cant be opposite.
      if (u.tiletype == t.tiletype && dirMultp != (0, 1)) {
        dirMultp = (0, -1)
      } else if (d.tiletype == t.tiletype && dirMultp != (0, -1)) {
        dirMultp = (0, 1)
      } else if (r.tiletype == t.tiletype && dirMultp != (-1, 0)) {
        dirMultp = (1, 0)
      } else if (l.tiletype == t.tiletype && dirMultp != (1, 0)) {
        dirMultp = (-1, 0)
      }
    } else {
      dirMultp = (1, 1)
    }
  }

  //Find the next checkpoint aka corner on the road.
  def findNextC(t: Tile): Checkpoint = {
    //Lets make some vars. nextT is the tile thats located on the corner of the road. With that tile we create the new checkPoint.
    var nextT: Tile = null
    var checkPoint: Checkpoint = null

    var found: Boolean = false
    var stepper = 1

    while (!found) {
      //Scan the road forward until we find the end of the road(first condition), or until we find a different type of tile(second condition).
      if (t.xPlace + dirMultp._1 * stepper == 20 || t.tiletype !=
        grid.getTile(t.xPlace + (dirMultp._1 * stepper), t.yPlace + (dirMultp._2 * stepper)).tiletype) {
        found = true
        stepper -= 1
        nextT = grid.getTile(t.xPlace + (dirMultp._1 * stepper), t.yPlace + (dirMultp._2 * stepper))
      }
      stepper += 1
    }
    checkPoint = new Checkpoint(nextT, dirMultp._1, dirMultp._2)
    checkPoint
  }

  //Fills the checkpoints list with all the maps checkpoints(corners).
  def populateCheckpointList(): Unit = {
    //Lets set the first checkpoint dependant on the startTile to the checkpoints array.
    this.findNextDir(enemytype.startTile)
    checkpoints += this.findNextC(enemytype.startTile)

    var stepper = 0
    var continue = true

    //And the rest with a while loop.
    while (continue) {
      this.findNextDir(checkpoints(stepper).getTile)
      if (dirMultp._1 == 1 && dirMultp._2 == 1) {
        continue = false
      } else {
        checkpoints += this.findNextC(checkpoints(stepper).getTile)
      }
      stepper += 1
    }
  }

  //Checks whether the current checkpoint, ahead of us, is reached.
  def checkpointReached = {
    var reached = false

    val current = checkpoints(currentCheckpoint).getTile

    if (x > current.x - 3 && x < current.x + 3 &&
      y > current.y - 3 && y < current.y + 3) {
      reached = true
      x = current.x
      y = current.y
    }
    reached
  }

  //Updates enemy state.
  def update(delta: Double) = {
    if (this.checkpointReached) {
      currentCheckpoint += 1
    } else {
      x += delta * checkpoints(currentCheckpoint).xdir * speed
      y += delta * checkpoints(currentCheckpoint).ydir * speed
    }
  }
}