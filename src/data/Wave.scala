package data

import scala.collection.mutable.Buffer

class Wave(enemyType: EnemyType, spawnTime: Double, enemiesPerWave: Int, grid: TileGrid) {

  var timeSinceLastSpawn: Double = 0
  var enemyList = Buffer[Enemy]()
  this.spawn()
  var waveCompleted = false

  def isCompleted: Boolean = waveCompleted

  def update(delta: Double, a: Buffer[Projectile]): Unit = {
    val projLocations = a.map(p => grid.getTile(p.x.toInt / 64, p.y.toInt / 64))
    enemyList.map(e => if (projLocations.contains(e.currentTile)) e.health = 0)
    if (enemyList.size < enemiesPerWave) {
      timeSinceLastSpawn += delta
      if (timeSinceLastSpawn > spawnTime) {
        this.spawn()
        timeSinceLastSpawn = 0
      }
    }
    enemyList.filter(_.alive).foreach { e =>
      e.update(delta); e.draw()
    }
    if (enemyList.forall(!_.alive)) waveCompleted = true
  }

  def spawn(): Unit = {
    enemyList += new Enemy(enemyType, grid)
  }

}