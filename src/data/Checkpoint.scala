package data

class Checkpoint (val tile: Tile, val xdir: Int, val ydir: Int) {
  
  def getTile: Tile = tile
  
  def xDir: Int = xdir
  
  def yDir: Int = ydir
  
  
}