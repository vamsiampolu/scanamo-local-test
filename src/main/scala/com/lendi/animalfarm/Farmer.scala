package com.lendi.animalfarm

case class Farm(animals: List[String])
case class Farmer(name: String, age: Long, farm: Farm)
