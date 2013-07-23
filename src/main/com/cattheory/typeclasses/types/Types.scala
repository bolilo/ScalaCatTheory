package main.com.cattheory.typeclasses.types

import main.com.cattheory.Maybe

object Types {

  trait Functor[F[_]] {
    def functorMap[A, B](f: A => B)(context: F[A]): F[B]
  }

  implicit def toFunctor[X, T[X]](m: T[X])(implicit ev: Functor[T]) = new {
    def functorMap[Y](f: X => Y): T[Y] = ev.functorMap(f)(m)
  }

  //This special case is required because Maybe has 2 subclasses and there is no
  //implicit object for Functor[Just] 

  implicit def toFunctor[X](m: Maybe[X])(implicit ev: Functor[Maybe]) = new {
    def functorMap[Y](f: X => Y): Maybe[Y] = ev.functorMap(f)(m)
  }

  trait ApplicativeLike[T[_]] extends Functor[T] {
    def pure[A](a: A): T[A]
    def <*>[A, B](tf: T[A => B])(context: T[A]): T[B]
  }

  implicit def toApplicative[X, Y, T[X]](fun: T[X => Y])(implicit ev: ApplicativeLike[T]) = new {
    def <*> = ev.<*>(fun)_
  }

  implicit def toApplicative[X, Y](fun: Maybe[X => Y])(implicit ev: ApplicativeLike[Maybe]) = new {
    def <*> = ev.<*>(fun)_
  }

  private def pure[X, T[_]](v: X)(implicit ev: ApplicativeLike[T]) = ev.pure(v)

}