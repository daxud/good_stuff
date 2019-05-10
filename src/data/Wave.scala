package data

import scala.collection.mutable.Buffer

class Wave(enemyType: EnemyType, spawnTime: Double, enemiesPerWave: Int, grid: TileGrid) {

  var timeSinceLastSpawn: Double = 0
  var enemyList = Buffer[Enemy]()
  this.spawn()
  var numberOfCurrentEnemy: Int = 1
  var waveCompleted = false
  var enemiesPassed = 0

  def isCompleted: Boolean = waveCompleted
  
  def checkPassed() = {
    enemyList.foreach(e => if (!e.alive && e.outOfBounds) enemiesPassed += 1)
  }

  def update(delta: Double): Unit = {
    this.checkPassed()
    if (enemyList.size < enemiesPerWave) {
      timeSinceLastSpawn += delta

      if (timeSinceLastSpawn > spawnTime && numberOfCurrentEnemy < enemiesPerWave) {
        this.spawn()
        numberOfCurrentEnemy += 1
        timeSinceLastSpawn = 0
      }
    }
    enemyList = enemyList.filter(_.alive) //Lets filter away the dead ones.
    enemyList.foreach(e => {
      e.update(delta)
      e.draw()
    }
    )
    //If all the enemies of the wave has been spawned and the enemylist is empty(all dead)
    //the wave is then completed.
    if (numberOfCurrentEnemy == enemiesPerWave && enemyList.isEmpty) waveCompleted = true;
    enemyList.foreach(e => println(e.health))    
  }

  def spawn(): Unit = {
    enemyList += new Enemy(enemyType, grid)
  }

}