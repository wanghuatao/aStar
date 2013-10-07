aStar
=====

A* - heuristic search algorithm






val map = Array.range(0, 4).map{
      i => Array.range(0, 4).map{
        j =>
          val n = new ANode(i, j, "%s - %s".format(i, j)) // add new nodes
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
  
val path = aStar.findPath(map(1)(0), map(1)(3))
  
  
