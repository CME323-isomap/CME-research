/**
 * Helper functions
 */

// Square values
def sqr(x: Double): Double =
  x * x

// Find the mode of the list
def mde(x: List[Double]): Double = {
  x.groupBy(x => x).mapValues(_.size).maxBy(_._2)._1
}

// Converts a list of tuples to just a list of the second value of each tuple
def tup2list(x: List[(Double, Double)]): List[Double] = {
    val n = x.length
    var i = 0
    var k = List[Double]()
    for (i <- 0 to n - 1) {
       k = k ++ List(x(i)._2)
    }
    k
}

// Read in data
val train = sc.textFile("sep_data.txt").
                 map(l => l.split(",").map(_.toDouble))
val tbd = sc.textFile("class.txt").
               map(l => l.split(",").map(_.toDouble))

// Compute cross product of training data dn those to be classified
val paired = tbd.cartesian(train)

// map to (classpoint id, (dist, class)) for each cross product
val mapped_paired = paired.map(x => (x._1(2).toString, List((sqr(x._1(0) - x._2(0)) + sqr(x._1(1) - x._2(1)), x._2(2)))))
// Reduce to (classpoint id, List((dist, class)))
val grouped = mapped_paired.reduceByKey(_ ++ _)
// Sort our key values in descending order of dist, take first k = 5
val sorted_group = grouped.map(x => (x._1, (x._2.sortWith(_._1 < _._1)).take(5)))
// Find the most common class and return our final data as (classpoint id, class)
val modes = sorted_group.map(y => (y._1, mde(tup2list(y._2))))

// Save as text file
modes.saveAsTextFile("blah")