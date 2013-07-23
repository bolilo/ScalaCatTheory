package main.com.cattheory

import main.com.cattheory.typeclasses.FunctorLikeMaybe
import main.com.cattheory.typeclasses.FunctorLikeList

package object typeclasses {

  //http:stackoverflow.com/questions/14633231/scala-self-type-annotation-vs-with-mix-in
  
  //implicit object FunctorMaybe extends FunctorLikeMaybe

  //implicit object FunctorList extends FunctorLikeList
 
  implicit object ApplicativeMaybe extends ApplicativeLikeMaybe with FunctorLikeMaybe
 
  implicit object ApplicativeList extends AplicativeLikeList with FunctorLikeList
  
}