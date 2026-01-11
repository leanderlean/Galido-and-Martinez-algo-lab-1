error id: file:///C:/Users/User/OneDrive/Desktop/Algo-lab_1/lab1/src/net/javaguides/Percolation.java
file:///C:/Users/User/OneDrive/Desktop/Algo-lab_1/lab1/src/net/javaguides/Percolation.java
### com.thoughtworks.qdox.parser.ParseException: syntax error @[38,5]

error in qdox parser
file content:
```java
offset: 1253
uri: file:///C:/Users/User/OneDrive/Desktop/Algo-lab_1/lab1/src/net/javaguides/Percolation.java
text:
```scala
// package net.javaguides;

// public class Percolation {
//     private boolean[][] percolatedPath;

//     public boolean[][] getPercolatedPath() {
//         return percolatedPath;
//     }

//     public boolean percolates(boolean[][] grid, int n) {
//         percolatedPath = null;
//         for (int col = 0; col < n; col++) {
//             if (grid[0][col]) { 
//                 boolean[][] visited = new boolean[n][n];
//                 if (dfs(grid, visited, 0, col, n)) {
//                     percolatedPath = visited;
//                     return true;
//                 }
//             }
//         }
//         return false;
//     }

//     private boolean dfs(boolean[][] grid, boolean[][] visited, int row, int col, int n) {
//         if (row < 0 || row >= n || col < 0 || col >= n || !grid[row][col] || visited[row][col]) {
//             return false;
//         }

//         visited[row][col] = true;

//         if (row == n - 1) return true;

//         return dfs(grid, visited, row + 1, col, n) || 
//                dfs(grid, visited, row - 1, col, n) ||
//                dfs(grid, visited, row, col + 1, n) ||
//                dfs(grid, visited, row, col - 1, n);
//     }
// }@@
```

```



#### Error stacktrace:

```
com.thoughtworks.qdox.parser.impl.Parser.yyerror(Parser.java:2025)
	com.thoughtworks.qdox.parser.impl.Parser.yyparse(Parser.java:2147)
	com.thoughtworks.qdox.parser.impl.Parser.parse(Parser.java:2006)
	com.thoughtworks.qdox.library.SourceLibrary.parse(SourceLibrary.java:232)
	com.thoughtworks.qdox.library.SourceLibrary.parse(SourceLibrary.java:190)
	com.thoughtworks.qdox.library.SourceLibrary.addSource(SourceLibrary.java:94)
	com.thoughtworks.qdox.library.SourceLibrary.addSource(SourceLibrary.java:89)
	com.thoughtworks.qdox.library.SortedClassLibraryBuilder.addSource(SortedClassLibraryBuilder.java:162)
	com.thoughtworks.qdox.JavaProjectBuilder.addSource(JavaProjectBuilder.java:174)
	scala.meta.internal.mtags.JavaMtags.indexRoot(JavaMtags.scala:49)
	scala.meta.internal.metals.SemanticdbDefinition$.foreachWithReturnMtags(SemanticdbDefinition.scala:99)
	scala.meta.internal.metals.Indexer.indexSourceFile(Indexer.scala:546)
	scala.meta.internal.metals.Indexer.$anonfun$reindexWorkspaceSources$3(Indexer.scala:677)
	scala.meta.internal.metals.Indexer.$anonfun$reindexWorkspaceSources$3$adapted(Indexer.scala:674)
	scala.collection.IterableOnceOps.foreach(IterableOnce.scala:630)
	scala.collection.IterableOnceOps.foreach$(IterableOnce.scala:628)
	scala.collection.AbstractIterator.foreach(Iterator.scala:1313)
	scala.meta.internal.metals.Indexer.reindexWorkspaceSources(Indexer.scala:674)
	scala.meta.internal.metals.MetalsLspService.$anonfun$onChange$2(MetalsLspService.scala:912)
	scala.runtime.java8.JFunction0$mcV$sp.apply(JFunction0$mcV$sp.scala:18)
	scala.concurrent.Future$.$anonfun$apply$1(Future.scala:691)
	scala.concurrent.impl.Promise$Transformation.run(Promise.scala:500)
	java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1144)
	java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:642)
	java.base/java.lang.Thread.run(Thread.java:1570)
```
#### Short summary: 

QDox parse error in file:///C:/Users/User/OneDrive/Desktop/Algo-lab_1/lab1/src/net/javaguides/Percolation.java