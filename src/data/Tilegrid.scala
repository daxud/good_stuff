package data

import scala.collection.mutable.Buffer

//Takes as parameter a two-dim array that represents the map.
class TileGrid(arr: Array[Array[Int]]) {

  //Map of tiles
  val map: Array[Array[Tile]] = createMap

  //Checkpoints
  val checkPoints: Buffer[Checkpoint] = Buffer()

  //Fills the map with tiles.
  def createMap: Array[Array[Tile]] = {
    val a = Array.ofDim[Tile](arr.length, arr(0).length)
    for (y <- 0 until arr.length) {
      for (x <- 0 until arr(y).length) {
        arr(y)(x) match {
          case 0 => a(y)(x) = new Tile(x * 64, y * 64, Grass)
          case 1 => a(y)(x) = new Tile(x * 64, y * 64, Dirt)
        }
      }
    }
    a
  }

  def draw() = {
    map.foreach(_.foreach(_.draw()))
  }

  //Sets a certain tile.
  def setTile(x: Int, y: Int, t: TileType): Unit = {
    map(y)(x) = new Tile(x * 64, y * 64, t)
  }

  //Gets a certain tile.
  def getTile(xPlace: Int, yPlace: Int): Tile = {
    if (xPlace < arr(0).length && yPlace < arr.length && xPlace > -1 && yPlace > -1) {
      map(yPlace)(xPlace)
    } else {
      new Tile(0, 0, Null)
    }
  }
}