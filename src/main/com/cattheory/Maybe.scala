package main.com.cattheory

trait Maybe[+T] 

case class Just[T](x:T) extends Maybe[T] 

case object HNothing extends Maybe[Nothing]