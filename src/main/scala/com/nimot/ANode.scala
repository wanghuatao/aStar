package com.nimot


/**
 * Created by IntelliJ IDEA.
 * User: jtomin
 * Date: 31.10.11
 * Time: 19:30
 * To change this template use File | Settings | File Templates.
 */

class ANode[A](val x: Int, val y: Int, data: A) {

  var f: Float = _

  var g: Float = _

  var h: Float = _

  var row: Int = _

  var col: Int = _

  var parentNode:Option[ANode[A]] = None

  var traversable: Boolean = true
  override def toString = data.toString + " %s".format(traversable )
}