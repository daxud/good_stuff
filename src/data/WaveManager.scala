package data

class WaveManager(enemyType: EnemyType, timeBetweenEnemies: Double, enemiesPerWave: Int, grid: TileGrid) {
  var timeSinceLastWave = 0
  var waveNumber = 0
  var currentWave: Wave = null
  this.newWave()

  def update(delta: Double)() = {
    if (!currentWave.isCompleted) {
      currentWave.update(delta)
    } else {
      this.newWave()
    }
  }

  def newWave() = {
    currentWave = new Wave(enemyType, timeBetweenEnemies, enemiesPerWave, grid)
    waveNumber += 1
  }

}