package data

import scalafx.scene.canvas.GraphicsContext

class TileGrid (val gc: GraphicsContext) {
  //two dim array of tiles
  val map = Array.ofDim[Tile](20, 15)

  def drawDesired(newMap: Array[Array[Int]]) = {
    for (i <- 0 until map.length) {
      for (j <- 0 until map(i).length) {
        newMap(j)(i) match {
          case 0 => new GrassTile(i * 64, j * 64, 64, 64)
          case 1 => new DirtTile(i * 64, j * 64, 64, 64)
        }
      }
    }
  }
  
  def setTile(tile: Tile) = {
    
    ???
  }
  
  def getTile(x: Int, y: Int): Tile = {
    map(x)(y)
  }
  
}