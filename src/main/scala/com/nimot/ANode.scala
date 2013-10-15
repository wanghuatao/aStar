package com.nimot


/**
 * This class is representing a single node of underlying
 * graph, characterised by:
 * 
 *  position (x,y)
 *  any data
 */

class ANode[A](val x: Int, val y: Int, data: A) {

 /*
 * Cost of the  past path, which is the known distance 
 * from the starting node to the current node
 */
  var g: Float = _

 /*
 *  Cost of the future path, which is a heuristic
 *  estimate of the distance from this node to the goal
 */
  var h: Float = _

  /*
  * Cost of current node is a sum of  g and h, which is being used 
  * to determine the order in which the search visits nodes in the tree
  */
  var f: Float = _
  
  
  var row: Int = _

  var col: Int = _

  // Privious node in the alreday determined path
  var parentNode:Option[ANode[A]] = None

  var traversable: Boolean = true
  override def toString = data.toString + " %s".format(traversable )
}
