package com.nimot

/**
 * Created by IntelliJ IDEA.
 * User: jtomin
 * Date: 01.11.11
 * Time: 00:26
 * To change this template use File | Settings | File Templates.
 */

object Main {
  def main(str: Array[String]) = {
    /*
    var nodes: List[ANode[String]] = List[ANode[String]]()

    for (i <- 4; j <- 4) {
      val n = new ANode(i, j, "%s - %s".format(i, j))
      n.row = i
      n.col = j
      if (i == 1 && j == 1) n.traversable = false
      if (i == 2 && j == 1) n.traversable = false
      nodes =:: n
    }

    val aStar = new AStar(graph.toList)
    val path = aStar.findPath(graph(1)(0), graph(1)(3))
    path foreach {
      n => println(n.data)
    }*/
    val map = Array.range(0, 4).map{
      i => Array.range(0, 4).map{
        j =>
          val n = new ANode(i, j, "%s - %s".format(i, j))
          n.row = i
          n.col = j
          if (i == 1 && j == 1) n.traversable = false
          if (i == 0 && j == 1) n.traversable = false
          if (i == 2 && j == 1) n.traversable = false
          if (i == 3 && j == 1) n.traversable = false
          n
      }
    }
    val nodes = map flatMap (n => n)
    val aStar = new AStar(nodes.toList)
    println("find path")
    val path = aStar.findPath(map(1)(0), map(1)(3))
    println("done")
    path foreach {
      n => println(n)
    }
  }


}