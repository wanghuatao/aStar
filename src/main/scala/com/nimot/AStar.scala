package com.nimot

/**
 * A* is a type of heuristical search algorithm.
 * Heuristic means we need to make an estimate at each node
 * of how far we are from the goal. 
 * 
 * The algorithm below is processing a list of nodes:ANode
 * having a square grid structure.
 *
 */
class AStar[A](val nodes: List[ANode[A]])
{
  type N = ANode[A]

  def findPath(firstNode: N, destinationNode: N): List[N] = {
    var openNodes = List[N]()
    var closedNodes = List[N]()

    var currentNode: N = firstNode

    var isAvailable = true
    var connectedNodes: List[N] = List[N]()
    var travelCost: Float = 1.0f

    currentNode.g = 0
    currentNode.h = diagonalHeuristic(currentNode, destinationNode, travelCost)
    currentNode.f = currentNode.g + currentNode.h


    while (currentNode != destinationNode && isAvailable) {
      connectedNodes = findConnectedNodes(currentNode)

      for (testNode <- connectedNodes) {

        if (testNode != currentNode && testNode.traversable) {
          var g = currentNode.g + travelCost
          var h = diagonalHeuristic(testNode, destinationNode, travelCost)
          var f = g + h;

          if (isOpen(testNode, openNodes) || isClosed(testNode, closedNodes)) {
            if (testNode.f > f) {
              testNode.f = f
              testNode.g = g
              testNode.h = h
              testNode.parentNode = Some(currentNode)
            }
          } else {
            testNode.f = f
            testNode.g = g
            testNode.h = h
            testNode.parentNode = Some(currentNode)
            openNodes = testNode :: openNodes
          }
        }
      }
      closedNodes = currentNode :: closedNodes

      if (openNodes.length == 0) {
        isAvailable = false
      } else {
        openNodes = openNodes.sortBy(e => e.f)
        currentNode = openNodes.head
        openNodes = openNodes.tail
      }
    }
    if (isAvailable) buildPath(destinationNode) else Nil
  }



  /****************************************************************
   *      private
   ****************************************************************/

 
  private def buildPath(destinationNode: N): List[N] = {
    destinationNode.parentNode match {
      case Some(x) => destinationNode :: buildPath(x)
      case None => destinationNode :: Nil
    }
  }


  private def isOpen(node: N, openNodes: List[N]): Boolean = {
    openNodes.exists(n => node == n)
  }


  private def isClosed(node: N, closedNodes: List[N]): Boolean = {
    closedNodes.exists(n => node == n)
  }
  

  /**
   * Euclidian heuristic calculates the costs [cost] using 
   * a measure given by the Pythagorean formula [destinationNode].
   */
  private def euclidianHeuristic(node: N, destinationNode: N, cost: Float = 1.0f): Float = {
    val dx = node.x - destinationNode.x
    val dy = node.y - destinationNode.y
    scala.math.sqrt(dx * dx + dy * dy).toFloat * cost
  }

  /**
   * Manhattan heuristic calculates the costs [cost] moving
   * horizontally and vertically to reach the target [destinationNode] 
   * from the current node [node].
   * 
   */
  private def manhattanHeuristic(node: N, destinationNode: N, cost: Float = 1.0f): Float = {
    math.abs(node.x - destinationNode.x) * cost + math.abs(node.y + destinationNode.y) * cost
  }

  /**
   * Diagonal heuristic calculates the costs [cost] being able
   * to move diagonally  to reach the target [destinationNode] 
   * from the current node [node].
   * 
   */
  private def diagonalHeuristic(node: N, destinationNode: N, cost: Float = 1.0f,
                                diagonalCost: Float = 1.0f): Float = {
    val dx: Int = math.abs(node.x - destinationNode.x)
    val dy: Int = math.abs(node.y - destinationNode.y)

    val diag = math.min(dx, dy)
    val straight = dx + dy

    diagonalCost * diag + cost * (straight - 2 * diag)
  }

  
  private def findConnectedNodes(node: N): List[N] = {
    nodes.filter{
      n =>
        n.row <= node.row + 1 &&
          n.row >= node.row - 1 &&
          n.col <= node.col + 1 &&
          n.col >= node.col - 1
    }
  }
}
