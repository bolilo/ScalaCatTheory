package main.com.cattheory.typeclasses.types

import main.com.cattheory.Maybe

object Types {

  trait Functor[F[_]] {
    def functorMap[A, B](f: A => B): F[A] => F[B]
  }

  implicit def toFunctor[X, T[X]](m: T[X])(implicit ev: Functor[T]) = new {

    def functorMap[Y](f: X => Y): T[Y] = {
      def fun = ev.functorMap(f)
      fun(m)
    }
  }

  //This special case is required because Maybe has 2 subclasses and there is no
  //implicit opbject for Functor[Just] 

  implicit def toFunctor[X](m: Maybe[X])(implicit ev: Functor[Maybe]) = new {

    def functorMap[Y](f: X => Y): Maybe[Y] = {
      def fun = ev.functorMap(f) 
      fun(m)
    }
  }

  trait ApplicativeLike[T[_]] extends Functor[T] {
    def pure[A](a: A): T[A]
    def <*>[A, B](tf: T[A => B])(ta: T[A]): T[B]
  }
}