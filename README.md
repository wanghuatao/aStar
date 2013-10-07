aStar
=====

A* - heuristic search algorithm


    
val nodes = map flatMap (n => n)
  
val aStar = new AStar(nodes.toList) 
  
val path = aStar.findPath(map(1)(0), map(1)(3))
  
  
