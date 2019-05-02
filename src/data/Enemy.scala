package data

import helpers._
import gui.Drawing
import scala.collection.mutable.Buffer

class Enemy(enemytype: EnemyType, grid: TileGrid) {
  val startTile = grid.getTile(1,1)
  var x = startTile.x.toDouble
  var y = startTile.y.toDouble
  var health: Int = enemytype.health
  val speed: Int = enemytype.speed

  //Variables that have somethin to do with the moving of the enemy.
  var dirMultp = (0, 0)
  var currentCheckpoint: Int = 0
  val checkpoints = Buffer[Checkpoint]()
  //Lets populate the array of checkpoints before we get any further.
  this.populateCheckpointList()

  //The tile that the enemy is currently standing.
  def currentTile: Tile = grid.getTile((x / 64).toInt, (y / 64).toInt)

  //The enemy is alive if has some health and if its inside the map parameters.(currentTile != Null tile)
  def alive: Boolean = {
    this.currentTile.tiletype != Null && health > 0
  }

  //Draws the enemy to the screen.
  def draw(): Unit = Helpers.drawQuadTextRot(enemytype.text, x, y, enemytype.width, enemytype.height, 90.0)

  //Finds the next direction.
  def findNextDir(t: Tile) = {
    //All the surrounding tiles.
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
  }

  //Find the next checkpoint aka corner on the road.
  def findNextC(t: Tile): Checkpoint = {
    //Lets make some vars. nextT is the tile thats located on the corner of the road. With that tile we create the new checkPoint.
    var nextT: Tile = null
    var checkPoint: Checkpoint = null

    var found: Boolean = false
    var stepper = 1

    def forwardTile: Tile = grid.getTile(t.xPlace + (dirMultp._1 * stepper), t.yPlace + (dirMultp._2 * stepper))

    while (!found) {
      //Scans the road forward until finds a the end of the map (tiletype=Null) or a different tiletype.
      if (forwardTile.tiletype == Null) {
        found = true
        nextT = forwardTile
      } else if (forwardTile.tiletype != t.tiletype) {
        found = true
        stepper -= 1
        nextT = forwardTile
      }
      stepper += 1
    }
    checkPoint = new Checkpoint(nextT, dirMultp._1, dirMultp._2)
    checkPoint
  }

  //Fills the checkpoints list with all the maps checkpoints(corners).
  def populateCheckpointList(): Unit = {
    //Lets set the first checkpoint dependant on the startTile to the checkpoints array.
    this.findNextDir(startTile)
    checkpoints += this.findNextC(startTile)

    var stepper = 0
    var continue = true

    //And the rest with a while loop.
    while (continue) {
      this.findNextDir(checkpoints(stepper).getTile)

      if (checkpoints.last.getTile.tiletype == Null) {
        continue = false
      } else {
        checkpoints += this.findNextC(checkpoints(stepper).getTile)
      }
      stepper += 1
    }
  }

  //Checks whether the current checkpoint, ahead of us, is reached. If currentCheckpoint is type of Null enemy dies.
  def checkpointReached: Boolean = {
    var reached = false

    val current = checkpoints(currentCheckpoint).getTile

    if (x > current.x - 3 && x < current.x + 3 && y > current.y - 3 && y < current.y + 3) {
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