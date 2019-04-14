package data

import scala.collection.mutable.Buffer

class Wave(spawnTime: Double, enemyType: EnemyType, grid: TileGrid)  {
  
  var timeSinceLastSpawn: Double = 0
  val enemyList = Buffer[Enemy]()
  
  def update(delta: Double): Unit = {
    timeSinceLastSpawn += delta 
    if (timeSinceLastSpawn > spawnTime) {
      this.spawn()
      timeSinceLastSpawn = 0
    }
    for (e <- enemyList) {
      e.update(delta)
      e.draw()
    }
  }
  
  def spawn(): Unit = {
    enemyList += new Enemy(Easy, grid)
  }
  
}