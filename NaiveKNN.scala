// Read in training data
val data = sc.textFile("sep_data.txt")
val points = data.map(l => l.split(","))

// Read in data to be classified
val dfclass = sc.textFile("class.txt").map(l =>l.split(","))
val bc = sc.broadcast(dfclass)

for (i <- 1 to n)


// wRITE DATA EXAMPLE
points.saveAsTextFile("blah.txt")


// NEW IMP
val train = sc.textFile("sep_data.txt").
                 map(l => l.split(",").map(_.toDouble))
val tbd = sc.textFile("class.txt").
               map(l =>l.split(",").map(_.toDouble))
val paired = tbd.cartesian(train)


