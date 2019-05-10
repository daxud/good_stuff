package helpers

import java.io.FileInputStream

import gui.Drawing
import scalafx.scene.SnapshotParameters
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.image.Image
import scalafx.scene.image.ImageView
import scalafx.scene.paint.Color

object Helpers {

  //Graphics contexts of the gui.
  def gc: GraphicsContext = Drawing.gc
  def wgc: GraphicsContext = Drawing.wgc

  //Draws the desired texture on the desired spot.
  def drawQuadText(img: Image, x: Double, y: Double, width: Int, height: Int) = {
    gc.drawImage(img, x, y, width, height)
  }

  //NOT IN USE CURRENTLY
  def rotateAndDraw(i: Image, degrees: Double, x: Double, y: Double) = {
    val imgView = new ImageView(i)
    imgView.setRotate(degrees)
    val parameters = new SnapshotParameters
    parameters.fill = Color.rgb(100, 100, 100, 0.5)
    gc.drawImage(imgView.snapshot(parameters, null).asInstanceOf[Image], x, y)
  }

  def isColliding(x1: Double, y1: Double, w1: Double, h1: Double,
                  x2: Double, y2: Double, w2: Double, h2: Double): Boolean = {
    (x1 + w1 > x2) && (x1 < x2 + w2) && (y1 + h1 > y2) && (y1 < y2 + h2)
  }

  def drawTile(x: Double, y: Double, c: Color) = {
    gc.fill = c
    gc.fillRect(x, y, 64, 64)
  }

  def drawCircle(x: Double, y: Double, r: Int, c: Color) = {
    gc.fill = c
    gc.globalAlpha = 200.0
    gc.fillOval(x, y, r, r)
  }

  def drawHealthBar(x: Double, y: Double, maxHealth: Int, currentHealth: Int) = {
    val ratio = currentHealth.toDouble / maxHealth
    val width = 32
    val height = 8
    val rectX = x + 8
    val rectY = y - 16

    gc.fill = Color.Chartreuse
    gc.fillRect(rectX, rectY, width * ratio, height)
    gc.fill = Color.OrangeRed
    gc.fillRect(rectX + (width * ratio), rectY, width * (1.0 - ratio), height)
  }

  //Loads a texture from a path and returns it as a image.
  def loadText(path: String): Image = {
    val stream = new FileInputStream(path)
    val text = new Image(stream)
    text.smooth
    text
  }
}