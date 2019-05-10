package data
import scala.collection.mutable.Buffer

class WaveManager(enemyType: EnemyType, timeBetweenEnemies: Double, enemiesPerWave: Int, grid: TileGrid) {
  var timeSinceLastWave = 0
  var waveNumber = 0
  var currentWave: Wave = null
  this.newWave()

  def getCurrentWave: Wave = this.currentWave

  def update(delta: Double)() = {
    if (!currentWave.isCompleted) {
      currentWave.update(delta)
    }
  }

  def newWave() = {
    if (waveNumber == 0) {
    currentWave = new Wave(enemyType, timeBetweenEnemies, enemiesPerWave, grid)
    waveNumber += 1
    } else {
      currentWave = new Wave(enemyType, timeBetweenEnemies, enemiesPerWave * this.waveNumber, grid)
    }
  }
}