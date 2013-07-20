package main.com.cattheory

import main.com.cattheory.typeclasses.FunctorLikeMaybe
import main.com.cattheory.typeclasses.FunctorLikeList

package object typeclasses {

  implicit object FunctorMaybe extends FunctorLikeMaybe

  implicit object FunctorList extends FunctorLikeList
  
}